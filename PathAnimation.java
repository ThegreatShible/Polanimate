package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by sol on 16/02/2016.
 */
public class PathAnimation extends Parent {

    //la parallelTransition principale
    public  ParallelTransition parallelTransition =new ParallelTransition();
    private boolean isRecording,isFinished,pathMode;
    private ShapeInfo currentShapeInfo;
    private Shape currentPath;
    public Drawable group;
    private Duration duration=new Duration(0);
    //structure qui permet de rassembler le path ,shape ,et list de ShapeInformation
    private ObservableList<PathShape> pathList= FXCollections.observableArrayList();
    final private boolean isImported;

    private final double x;
    private final double y;

    //constructeur normal
    public PathAnimation(double x,double y,Slider slider) {
        this.x = x;
        this.y =y;
        Rectangle rectangle= RectangleBuilder.create()
                .width(x)
                .height(y)
                .translateX(0)
                .translateY(0)
                .fill(Color.TRANSPARENT)
                .build();
        this.getChildren().add(rectangle);


        slider.valueProperty().addListener((e,o,n)->{
            duration = Duration.seconds((Double)n);
        });
        isRecording = false;
        isFinished =false;
        pathMode = false;
        isImported =false;
    }
    //constructeur au cas ou l'on charge d'un fichier

    public PathAnimation(ObservableList<PathShape> pathList,double x ,double y) {
        this.pathList = pathList;
        this.x = x;
        this.y = y;
        Rectangle rectangle= RectangleBuilder.create()
                .width(x)
                .height(y)
                .translateX(0)
                .translateY(0)
                .fill(Color.TRANSPARENT)
                .build();

        this.getChildren().add(rectangle);
        for(PathShape pathShape : pathList){
            this.getChildren().add(pathShape.getPathTransition().getPath());
            this.getChildren().add(pathShape.getPathTransition().getNode());
            if(pathShape.getPathInformation() instanceof  PathInformation) {
                PathInformation pathInformation = (PathInformation)pathShape.getPathInformation();
                if(pathShape.getShapeInformations().size() >=2){
                    Path path = (Path)pathShape.getPathTransition().getNode();
                    ((Path) pathShape.getPathTransition().getNode()).setTranslateX(pathInformation.getPath().get(0).getX()-path.impl_getPivotX());
                    ((Path)pathShape.getPathTransition().getNode()).setTranslateY(pathInformation.getPath().get(0).getY()-path.impl_getPivotY());
                }else {
                    ((Shape) pathShape.getPathTransition().getNode()).setTranslateX(pathInformation.getPath().get(0).getX());
                    ((Shape) pathShape.getPathTransition().getNode()).setTranslateY(pathInformation.getPath().get(0).getY());
                }
            }
            Shape shape = (Shape)pathShape.getPathTransition().getNode();
            shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
            shape.setOnMouseExited(shapeOnMouseExitedHandler);
            parallelTransition.getChildren().add(pathShape.getPathTransition());
            pathShape.getPathTransition().setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        }
        isRecording = false;
        isFinished =false;
        pathMode = false;
        isImported = true;
    }

    public ObservableList<PathShape> getPathList() {
        return pathList;
    }

    public boolean isPathMode() {
        return pathMode;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Shape getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Shape currentPath) {
        this.currentPath = currentPath;
    }

    public boolean addShape(ShapeInfo shapeInfo) {
        if (shapeInfo != null) {
            if (isRecording) {
                currentShapeInfo = shapeInfo;
                this.getChildren().add(currentShapeInfo.getShape());
                Shape shape = shapeInfo.getShape();
                if (shape instanceof Path) {
                    Path path = (Path) shape;
                    path.setTranslateX(x / 2 - path.impl_getPivotX());
                    path.setTranslateY(y / 2 - path.impl_getPivotY());
                } else {
                    shapeInfo.getShape().setTranslateX(x / 2);
                    shapeInfo.getShape().setTranslateY(y / 2);
                }
                currentShapeInfo.getShape().setOnMouseEntered(shapeOnMouseEnteredEventHandler);
                currentShapeInfo.getShape().setOnMouseExited(shapeOnMouseExitedHandler);
                return true;
                //currentList=shapeInfo.getShapeInformations();
            }else
                return false;
        }else
            return false;
    }


    //ajoute le chemin qui se trouve dans shapeInfo avec un durée duration
    public void addPath(ShapeInfo shapeInfo,Duration duration){
        if(isRecording) {
            this.getChildren().add(shapeInfo.getShape());
            PathTransition pathTransition = new PathTransition(duration, shapeInfo.getShape(), currentShapeInfo.getShape());
            pathTransition.setInterpolator(Interpolator.LINEAR);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            parallelTransition.getChildren().add(pathTransition);
            PathShape pathShape = new PathShape(pathTransition,currentShapeInfo.getShapeInformations(),shapeInfo.getShapeInformations().get(0));
            pathShape.setRotate(currentShapeInfo.getShape().getRotate());
            pathShape.setScaleX(currentShapeInfo.getShape().getScaleX());
            pathShape.setScaleY(currentShapeInfo.getShape().getScaleY());
            pathShape.setPathColor(shapeInfo.getColor());
            pathShape.setShapeColor(currentShapeInfo.getColor());
            pathList.add(pathShape);

            pathMode = false;
        }
    }

    //fonction qui permet de dessiner le chemain
    public void  startDrawing(){
        group = new DrawPath(currentShapeInfo.getShape(),x,y);
        this.getChildren().add((Group)group);
        pathMode = true;

    }
    public void startRecoding(){
        isRecording = true;
        isFinished = false;
    }

    public void stopRecording(){
        isRecording = false;
        isFinished =true;
    }

    //j'ai pas encore fini
    public void submit(){
        if(isRecording && pathMode) {
            if (!duration.equals(Duration.ZERO)) {
                if (group instanceof DrawPath) {
                    if (((Path) group.getShapeInfo().getShape()).getElements().size() > 1) {
                        // Path path = (Path)group.getShapeInfo().getShape();
                        currentPath = this.group.getShapeInfo().getShape();
                        this.getChildren().remove(currentShapeInfo.getShape());
                        addPath(group.getShapeInfo(), duration);
                        this.getChildren().add(currentShapeInfo.getShape());
                        this.getChildren().remove(group);
                    }
                } else {
                    currentPath = this.group.getShapeInfo().getShape();
                    this.getChildren().remove(currentShapeInfo.getShape());
                    addPath(group.getShapeInfo(), duration);
                    this.getChildren().add(currentShapeInfo.getShape());
                    this.getPathList().remove(group);
                }
            }
        }
    }
    public void setSlider(Slider slider){
        if(isImported) {
            slider.valueProperty().addListener((e, o, n) -> {
                duration = Duration.seconds((Double) n);
            });
        }
    }

    public void save(String path){
        if(!pathList.isEmpty()) {
            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))));
                oos.writeObject(PathRecord.transformToRecord(this));
                oos.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "aucune animation est détéctée");
            alert.setTitle("Erreur de sauvegarde");
            alert.show();
        }
    }

    public void play(){
        if(!isRecording && isFinished) {


            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);

            PlayAnimation root = new PlayAnimation(parallelTransition, x, y);
            root.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());



            Scene scene = new Scene(root, x, y);

            for (Animation animation : parallelTransition.getChildren()) {
                PathTransition pathTransition = (PathTransition) animation;
                this.getChildren().removeAll(pathTransition.getNode(), pathTransition.getPath());
                root.getChildren().addAll(pathTransition.getPath(), pathTransition.getNode());
                Shape shape = (Shape)pathTransition.getNode();
                shape.setOnMouseEntered(null);
                shape.setOnMouseExited(null);
            }

            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> {
                parallelTransition.stop();
                for (Animation animation : parallelTransition.getChildren()) {
                    PathTransition pathTransition = (PathTransition) animation;
                    root.getChildren().removeAll(pathTransition.getPath(), pathTransition.getNode());
                    this.getChildren().addAll(pathTransition.getNode(), pathTransition.getPath());
                    Shape shape = (Shape) pathTransition.getNode();
                    shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
                    shape.setOnMouseExited(shapeOnMouseExitedHandler);

                }
            });
          //  parallelTransition.playFromStart();


            System.out.println(parallelTransition.getTotalDuration());
        }
    }

    EventHandler<MouseEvent> shapeOnMouseEnteredEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((Shape)event.getSource()).setStrokeWidth(((Shape)event.getSource()).getStrokeWidth());
                    System.out.println("mouse Entered");
                }
            };

    EventHandler<MouseEvent> shapeOnMouseExitedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Shape shape = ((Shape)event.getSource());
            shape.setStrokeWidth(shape.getStrokeWidth()/1.5);
        }
    };

}
