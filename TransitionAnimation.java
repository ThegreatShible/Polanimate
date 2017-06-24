package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
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

public class TransitionAnimation extends Parent
{
    //shape selectionnné
    private Shape selectedShape;

    //path entourant le rectangle


    //boolean qui retourne vrai si l'animation a été joué un foi au moin
    private boolean played= false;

    //parallelTransition principale
    public ParallelTransition parallelTransition  = new ParallelTransition();

    //liste de ShapeTransition qui contient les shapes,leurs sequantialTransition et un boolean pour dire si elle sont modifiés
    private ObservableList<ShapeTransition> shapeList = FXCollections.observableArrayList();

    //Liste des formes ajoutés avant le debut de l'enregitrement
    private ObservableList<ShapeInfo> addedShapes = FXCollections.observableArrayList();

    //variable representant le temps actuel
    private Duration currentTime=Duration.ZERO;

    //booleans pour savoir l'état de l'application
    private boolean isRecording , isFinished;

    //boolean pour savoir si cet animation est nouvelle ou importée de fichier
    private final boolean isImported;

    //slider pour la duration
    private  Slider slider;

    //bordure du groupe
    private Path path;

    //slider pour le scale
    Slider sliderScale;
    //slider pour le rotate


    Slider sliderRotate;
    //pour le drag'N drob

    JFXButton bouton1;
    JFXButton bouton2;

    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private double oldTranslateX;
    private double oldTranslateY;

    //resolution du groupe
    private final double x;
    private final double y;

    private final Rectangle rectangle;
    private final Path border;

    //constructeur au cas ou c'est une nouvelle animation
    public TransitionAnimation(Slider slider,double x,double y) {
        isRecording = false;
        isFinished = false;
        this.slider = slider;
        this.x = x ;
        this.y  = y;
        Rectangle rectangle = RectangleBuilder.create()
                .height(y)
                .width(x)
                .fill(Color.TRANSPARENT)
                .build();
        this.getChildren().add(rectangle);
        rectangle.setOnMouseClicked(e->{
            System.out.println((e.getSceneX()) + "     " + (e.getSceneY()));
            System.out.println(rectangle.getBoundsInParent().getMinX());

        });
        this.rectangle = rectangle;
        Path path = new Path();
        MoveTo moveTo = new MoveTo(0, 0);
        LineTo lineTo = new LineTo(x, 0);
        LineTo lineTo1 = new LineTo(x, y);
        LineTo lineTo2 = new LineTo(0, y);
        ClosePath closePath = new ClosePath();
        path.getElements().addAll(moveTo, lineTo, lineTo1, lineTo2, closePath);
        this.getChildren().add(path);
        this.border = path;



        slider.valueProperty().addListener((e,oldValue,newValue)->{
            setCurrentTime(Duration.seconds((Double)newValue));


        });
        isImported=false;
    }


    //constructeur au cas ou l'animation est apporté d'un fichier
    public TransitionAnimation(ObservableList<ShapeTransition> shapeLists,double x,double y){
        parallelTransition = new ParallelTransition();
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
        this.rectangle = rectangle;
        Path path = new Path();
        MoveTo moveTo = new MoveTo(0, 0);
        LineTo lineTo = new LineTo(x, 0);
        LineTo lineTo1 = new LineTo(x, y);
        LineTo lineTo2 = new LineTo(0, y);
        ClosePath closePath = new ClosePath();
        path.getElements().addAll(moveTo, lineTo, lineTo1, lineTo2, closePath);
        this.getChildren().add(path);
        this.border = path;

        for(ShapeTransition shapeTransition :shapeLists){
            parallelTransition.getChildren().add(shapeTransition.getSequentialTransition());
            this.getChildren().add(shapeTransition.getShape());
            Shape shape = shapeTransition.getShape();
            shape.setTranslateX(shapeTransition.getTranslateX());
            shape.setTranslateY(shapeTransition.getTranslateY());
            shape.setScaleX(shapeTransition.getScaleX());
            shape.setScaleY(shapeTransition.getScaleY());
            shape.setRotate(shapeTransition.getAngle());
            shape.setOnMouseExited(shapeOnMouseExitedHandler);
            shape.setOnMousePressed(shapeOnMousePressedEventHandler);
            shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
            shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
            shape.setOnMouseReleased(shapeOnMouseReleasedHandler);
            shape.setOnMouseClicked(shapeOnMouseClickedHandler);
        }
        this.shapeList = shapeLists;
        isImported=true;
        isFinished = true;
        isRecording =false;
    }

    public ObservableList<ShapeTransition> getShapeList() {
        return shapeList;
    }

    private void setCurrentTime(Duration currentTime) {
        this.currentTime = currentTime;
    }

    //ajouter une forme a la position (x,y)
    public boolean addShape(ShapeInfo shapeInfo) {
        if (shapeInfo != null) {
            if (!isRecording) {
                addedShapes.add(shapeInfo);
                this.getChildren().add(shapeInfo.getShape());
                Shape shape = shapeInfo.getShape();
                selectedShape = shape;
                if (shape instanceof Path) {
                    Path path = (Path) shape;
                    path.setTranslateX(x / 2 - path.impl_getPivotX());
                    path.setTranslateY(y / 2 - path.impl_getPivotY());
                    System.out.println("c'est la "+x / 2);
                } else {
                    shapeInfo.getShape().setTranslateX(x / 2);
                    shapeInfo.getShape().setTranslateY(y / 2);
                    System.out.println("this is "+shapeInfo.getShape().getTranslateX());
                }

                shape.setOnMousePressed(shapeOnMousePressedEventHandler);
                shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
                shape.setOnMouseExited(shapeOnMouseExitedHandler);
                shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
                shape.setOnMouseReleased(shapeOnMouseReleasedHandler);
                shape.setOnMouseClicked(shapeOnMouseClickedHandler);
                return  true;
            }else
                return false;

        }else
            return  false;
    }

    //valide la position des fomrmes dans currentTime
    public void submit(){
        if(isRecording){

            for(ShapeTransition shapeTransition : shapeList){
                if(shapeTransition.isModified()){
                    modifySeqTransition(shapeTransition);
                    shapeTransition.setModified(false);
                }
            }

            for(ShapeInfo shapeinfo :addedShapes){

                addShapeTranstion(shapeinfo);

            }
            addedShapes.clear();
        }
    }

    //fonction qui modifie la sequentialTransition
    private void modifySeqTransition(ShapeTransition shapeTransition){

        //si le currentTime =0 on n'ajoute aucune transition on modifie shapeTranstion
        if(currentTime == Duration.ZERO){

            //on recupere la forme
            Shape shape = shapeTransition.getShape();

            //on modifie les parametres de shapeTransition
            shapeTransition.setTranslateX(shape.getTranslateX());
            shapeTransition.setTranslateY(shape.getTranslateY());
            shapeTransition.setAngle(shape.getRotate());
            shapeTransition.setScaleX(shape.getScaleX());
            shapeTransition.setScaleY(shape.getScaleY());

            //pour le cas spécial du submit 0 apres animation
            SequentialTransition sequentialTransition = shapeTransition.getSequentialTransition();
            if(sequentialTransition.getChildren().size() >=1){
                ParallelTransition parallelTransition = (ParallelTransition)sequentialTransition.getChildren().get(0);


                TranslateTransition translateTransition = (TranslateTransition)parallelTransition.getChildren().get(0);
                translateTransition.setFromX(shapeTransition.getTranslateX());
                translateTransition.setFromY(shapeTransition.getTranslateY());

                RotateTransition rotateTransition = (RotateTransition) parallelTransition.getChildren().get(1);
                rotateTransition.setFromAngle(shapeTransition.getAngle());

                ScaleTransition scaleTransition  = (ScaleTransition) parallelTransition.getChildren().get(2);
                scaleTransition.setFromX(shapeTransition.getScaleX());
                scaleTransition.setFromY(shapeTransition.getScaleY());
                System.out.println("ajout a 0");
            }

        }
        else {
            System.out.println("ajout a " + currentTime);
            //renvoie la durée de la parallelTransition ajouté
            Duration duration = getDuration(shapeTransition);

            //la sequentialTransition du shape
            SequentialTransition sequentialTransition = shapeTransition.getSequentialTransition();

            //la parralellTransition a ajouter
            ParallelTransition parallelTransition = new ParallelTransition();


            //TranslateTransition
            TranslateTransition translateTransition = new TranslateTransition(duration);
            translateTransition.setToX(shapeTransition.getShape().getTranslateX());
            translateTransition.setToY(shapeTransition.getShape().getTranslateY());

            parallelTransition.getChildren().add(translateTransition);


            //RotateTransition
            RotateTransition rotateTransition = new RotateTransition(duration);
            rotateTransition.setToAngle(shapeTransition.getShape().getRotate());

            parallelTransition.getChildren().add(rotateTransition);


            //ScaleTransition
            ScaleTransition scaleTransition = new ScaleTransition(duration);
            scaleTransition.setToX(shapeTransition.getShape().getScaleX());
            scaleTransition.setToY(shapeTransition.getShape().getScaleY());

            parallelTransition.getChildren().add(scaleTransition);

            //inclue la parallelTransition dans la sequentialTransition
            include(parallelTransition, shapeTransition);
        }
    }

    //inclure parallelTransition dans sequentialTransition selon currentTime
    private void include(ParallelTransition parallelTransition,ShapeTransition shapeTransition){

        SequentialTransition sequentialTransition = shapeTransition.getSequentialTransition();
        Duration duration = new Duration(0);
        int i =0;
        boolean stop = false;

        if(!sequentialTransition.getChildren().isEmpty()){
            //parcourt des parallelTransition en ajoutant les duration
            while((i<sequentialTransition.getChildren().size())&&!stop){
                ParallelTransition parallelTransition1 = ((ParallelTransition)sequentialTransition.getChildren().get(i));

                //recuperer la duration de la parallelTransition en recuperant la duration d'une des transition fille
                Duration duree = ((TranslateTransition)parallelTransition1.getChildren().get(0)).getDuration();


                //A REVOIRE

                duration = duration.add(duree);


                if(!duration.lessThan(currentTime))
                    stop = true;
                else
                    i++;

            }

            //si duration existe deja on la remplace

            if(duration.equals(currentTime)){

                sequentialTransition.getChildren().set(i,parallelTransition);}

            //sinon si la durée est egale a la taille de la liste
            else if(i==sequentialTransition.getChildren().size()) {
                sequentialTransition.getChildren().add(parallelTransition);

            }
            //pour le cas spécial de la valeur ajoutée doit etre dans la premiere parallelTransition
            else if(i ==0){


                ParallelTransition parallelTransition1 = (ParallelTransition) sequentialTransition.getChildren().get(0);
                Duration duration1 =((TranslateTransition)parallelTransition1.getChildren().get(0)).getDuration().subtract(currentTime);
                ParallelTransition parallelTransition2 = new ParallelTransition();

                TranslateTransition translateTransition = new TranslateTransition(duration1);
                translateTransition.setToX(((TranslateTransition)parallelTransition1.getChildren().get(0)).getToX());
                translateTransition.setToY(((TranslateTransition)parallelTransition1.getChildren().get(0)).getToY());
                parallelTransition2.getChildren().add(translateTransition);

                RotateTransition rotateTransition = new RotateTransition(duration1);
                rotateTransition.setToAngle(((RotateTransition)parallelTransition1.getChildren().get(1)).getToAngle());
                parallelTransition2.getChildren().add(rotateTransition);

                ScaleTransition scaleTransition = new ScaleTransition(duration1);
                scaleTransition.setToX(((ScaleTransition)parallelTransition1.getChildren().get(2)).getToX());
                scaleTransition.setToY(((ScaleTransition)parallelTransition1.getChildren().get(2)).getToY());
                parallelTransition2.getChildren().add(scaleTransition);

                sequentialTransition.getChildren().add(1,parallelTransition2);

                Shape shape = shapeTransition.getShape();
                ((TranslateTransition)parallelTransition1.getChildren().get(0)).setToX(shape.getTranslateX());
                ((TranslateTransition)parallelTransition1.getChildren().get(0)).setToY(shape.getTranslateY());
                ((TranslateTransition)parallelTransition1.getChildren().get(0)).setDuration(currentTime);

                ((RotateTransition) parallelTransition1.getChildren().get(1)).setToAngle(shape.getRotate());
                ((RotateTransition) parallelTransition1.getChildren().get(1)).setDuration(currentTime);

                ((ScaleTransition) parallelTransition1.getChildren().get(2)).setToX(shape.getScaleX());
                ((ScaleTransition) parallelTransition1.getChildren().get(2)).setToY(shape.getScaleY());
                ((ScaleTransition) parallelTransition1.getChildren().get(2)).setDuration(currentTime);


            }
            //sinon si la durée est entre deux valeurs
            else{

                sequentialTransition.getChildren().add(i,parallelTransition);
                i++;
                ParallelTransition parallelTransition1 = ((ParallelTransition)sequentialTransition.getChildren().get(i));
                duration =duration.subtract(currentTime);

                //modifier scaleTransition
                TranslateTransition translateTransition =(TranslateTransition)parallelTransition1.getChildren().get(0);
                translateTransition.setDuration(duration);

                //modifier rotateTransition
                RotateTransition rotateTransition = (RotateTransition)parallelTransition1.getChildren().get(1);
                rotateTransition.setDuration(duration);

                //modifier scaleTransition
                ScaleTransition scaleTransition = (ScaleTransition)parallelTransition1.getChildren().get(2);
                scaleTransition.setDuration(duration);

            }

        }else{
            sequentialTransition.getChildren().add(parallelTransition);
            TranslateTransition translateTransition = (TranslateTransition)parallelTransition.getChildren().get(0);
            translateTransition.setFromX(shapeTransition.getTranslateX());
            translateTransition.setFromY(shapeTransition.getTranslateY());

            RotateTransition rotateTransition = (RotateTransition) parallelTransition.getChildren().get(1);
            rotateTransition.setFromAngle(shapeTransition.getAngle());

            ScaleTransition scaleTransition  = (ScaleTransition) parallelTransition.getChildren().get(2);
            scaleTransition.setFromX(shapeTransition.getScaleX());
            scaleTransition.setFromY(shapeTransition.getScaleY());


        }


    }

    //renvoie la durée pour une nouvelle parallelTransition selon la sequentialTransition et currentTime
    private Duration getDuration(ShapeTransition shapeTransition ) {

        //la sequentialTransition du shape
        SequentialTransition sequentialTransition = shapeTransition.getSequentialTransition();

        boolean stop = false;
        int i = 0;
        if (sequentialTransition.getChildren().isEmpty()) {
            return currentTime;
        }
        else {
            Duration duration = Duration.ZERO;
            ParallelTransition parallelTransition;
            while ((i < sequentialTransition.getChildren().size()) && !stop) {
                parallelTransition = ((ParallelTransition) sequentialTransition.getChildren().get(i));

                //recuperer la durée d'un des fils de parallelTranstition
                Duration duree = ((TranslateTransition) parallelTransition.getChildren().get(0)).getDuration();

                duration =duration.add(duree);
                if (!duration.lessThan(currentTime))
                    stop = true;
                else
                    i++;

            }

            //si l'insertion se fait a la fin
            if (i == sequentialTransition.getChildren().size())
                return currentTime.subtract(duration);

                //sinon si i==0
            else if (i == 0)
                return currentTime;

            else {
                ParallelTransition parallelTransition1 = (ParallelTransition) sequentialTransition.getChildren().get(i);
                TranslateTransition translateTransition = (TranslateTransition) parallelTransition1.getChildren().get(0);
                Duration duree = translateTransition.getDuration();

                return currentTime.subtract(duration.subtract(duree));
            }
        }
    }

    //fonction qui ajoute une shapeTransition au temps 0
    private void addShapeTranstion(ShapeInfo shapeInfo){

        //une sequentialTransition vide
        SequentialTransition sequentialTransition = new SequentialTransition(shapeInfo.getShape());

        //une shapeTransition qui contient la sequentialTransition
        ShapeTransition shapeTransition = new ShapeTransition(sequentialTransition,shapeInfo.getShapeInformations());

        //initialisation des paramatres de la forme au debut
        shapeTransition.setTranslateX(shapeInfo.getShape().getTranslateX());
        shapeTransition.setTranslateY(shapeInfo.getShape().getTranslateY());
        shapeTransition.setAngle(shapeInfo.getShape().getRotate());
        shapeTransition.setScaleY(shapeInfo.getShape().getScaleY());
        shapeTransition.setScaleX(shapeInfo.getShape().getScaleX());
        shapeTransition.setColor(shapeInfo.getColor());


        shapeList.add(shapeTransition);
        this.parallelTransition.getChildren().add(sequentialTransition);



    }

    //fonction appelé quand on commence l'enregistrement ;ATTENTION :cette fonction renvoie une exception si il n'y a pas de formes
    //dans l'animation
    public void startRecording()throws NoShapeException{


        if (!isImported && !played) {

            if (addedShapes.isEmpty())
                throw new NoShapeException();
            if(parallelTransition.getChildren().isEmpty()) {
                slider.setValue(0);
                isRecording = true;
                isFinished = false;
                submit();
                System.out.println("submit");
            }
        }
        isRecording = true;
        isFinished = false;

    }

    //fonction appellé lorsqu'on fini l'enregistrement
    public void StopRecording(){
        if(!shapeList.isEmpty()){
            played=true;
        }
        isRecording=false;
        isFinished =true;

    }



    //play if finished and not recording
    public void play(){
        Platform.runLater(()->{
            if(!isRecording && isFinished) {
                played=true;
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setAlwaysOnTop(true);
                stage.initModality(Modality.APPLICATION_MODAL);
                ParallelTransition parallelTransition = new ParallelTransition();

                for(Animation animation :this.parallelTransition.getChildren()){
                    SequentialTransition sequentialTransition = (SequentialTransition)animation;
                    if(!sequentialTransition.getChildren().isEmpty()){
                        parallelTransition.getChildren().add(sequentialTransition);
                    }
                }
                parallelTransition.setInterpolator(Interpolator.LINEAR);
                Group root = new PlayAnimation(parallelTransition,x,y) ;
                root.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());

                for(ShapeTransition shapeTransition : shapeList){
                    this.getChildren().remove(shapeTransition.getShape());
                    root.getChildren().add(shapeTransition.getShape());
                    Shape shape = shapeTransition.getShape();
                    shape.setOnMouseEntered(null);
                    shape.setOnMouseExited(null);
                    shape.setOnMouseDragged(null);
                    shape.setOnMousePressed(null);
                    shape.setOnMouseReleased(null);
                    shape.setOnMouseClicked(null);
                }

                Scene scene = new Scene(root, x, y);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(e -> {
                    parallelTransition.stop();
                    this.parallelTransition.stop();
                    for (ShapeTransition shapeTransition : shapeList) {
                        Shape shape = shapeTransition.getShape();
                        shape.setOnMousePressed(shapeOnMousePressedEventHandler);
                        shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
                        shape.setOnMouseExited(shapeOnMouseExitedHandler);
                        shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
                        shape.setOnMouseReleased(shapeOnMouseReleasedHandler);
                        shape.setOnMouseClicked(shapeOnMouseClickedHandler);
                        root.getChildren().remove(shape);
                        this.getChildren().add(shape);

                        shape.setTranslateX(shapeTransition.getTranslateX());
                        shape.setTranslateY(shapeTransition.getTranslateY());
                        shape.setRotate(shapeTransition.getAngle());
                        shape.setScaleX(shapeTransition.getScaleX());
                        shape.setScaleY(shapeTransition.getScaleY());
                        slider.setValue(0);
                        isRecording = false;
                    }
                });
                //  parallelTransition.playFromStart();
            }
        });
    }
    public void save(String  path) {
        if(!shapeList.isEmpty()) {
            TransitionRecord transitionRecord = TransitionRecord.transformToRecord(this);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))));
                oos.writeObject(transitionRecord);
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
    public void setSlider(Slider slider){
        if(isImported){
            this.slider = slider;
            slider.valueProperty().addListener((e,o,n)->{
                setCurrentTime(Duration.seconds((Double)n));
            });
        }
    }

    EventHandler<MouseEvent> shapeOnMouseClickedHandler =new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("MouseEvent");
            if((sliderRotate != null)&&(sliderScale != null)) {
                System.out.println("ils sont a null");
                Shape shape = (Shape) event.getSource();
                selectedShape = shape;
                sliderScale.setValue(shape.getScaleX());
                sliderRotate.setValue(shape.getRotate());
            }

        }
    };

    EventHandler<MouseEvent> shapeOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    Shape shape = (Shape) t.getSource();
                    //Point2D point2D = new Point2D(5, 5);





                    if(shape.getBoundsInParent().getMinX() <=0 &&(offsetX < oldTranslateX) ){
                        shape.setTranslateX(shape.getTranslateX()+(-1*shape.getBoundsInParent().getMinX()));
                    }else if(shape.getBoundsInParent().getMinY() <= 0 &&(offsetY < oldTranslateY)){
                        shape.setTranslateY(shape.getTranslateY()+(-1*shape.getBoundsInParent().getMinY()));
                    }else if(shape.getBoundsInParent().getMaxX() >= x &&(offsetX > oldTranslateX)){
                        shape.setTranslateX(shape.getTranslateX() -(shape.getBoundsInParent().getMaxX()-x));
                    }else if(shape.getBoundsInParent().getMaxY() >= y &&(offsetY > oldTranslateY)){
                        shape.setTranslateY(shape.getTranslateY() -(shape.getBoundsInParent().getMaxY()-y));
                    }else{
                        shape.setTranslateX(newTranslateX);
                        shape.setTranslateY(newTranslateY);
                        oldTranslateX = offsetX;
                        oldTranslateY = offsetY;
                    }
                    Shape shape1 = (Shape) t.getSource();
                    double l = (shape.getTranslateX() +(shape.impl_getPivotX())-x/2) / 5.7;
                    double ll=(shape.getTranslateY() + shape.impl_getPivotY() -y/2) / 5.7;
                    String str = Double.toString(l);
                    String string = Double.toString(ll);
                    bouton1.setText(" X : " + str.substring(0,5));
                    bouton2.setText(" Y : " + string.substring(0,5));

                }
            };

    EventHandler<MouseEvent> shapeOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Shape)(t.getSource())).getTranslateY();
                    ((Shape)t.getSource()).setCursor(Cursor.CLOSED_HAND);
                    Shape shape = (Shape) t.getSource();
                    double l = (shape.getTranslateX() +(shape.impl_getPivotX())-x/2) / 5.7;
                    double ll=(shape.getTranslateY() + shape.impl_getPivotY() -y/2) / 5.7;
                    String str = Double.toString(l);
                    String string = Double.toString(ll);
                    bouton1.setText(" X : " + str.substring(0,5));
                    bouton2.setText(" Y : " + string.substring(0,5));

                }
            };
    EventHandler<MouseEvent> shapeOnMouseEnteredEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((Shape)event.getSource()).setCursor(Cursor.OPEN_HAND);
                    ((Shape)event.getSource()).setStrokeWidth(((Shape)event.getSource()).getStrokeWidth()*1.5);
                }
            };

    EventHandler<MouseEvent> shapeOnMouseExitedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Shape shape = ((Shape)event.getSource());
            shape.setStrokeWidth(shape.getStrokeWidth()/1.5);
        }
    };
    EventHandler<MouseEvent> shapeOnMouseReleasedHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            ((Shape)event.getSource()).setCursor(Cursor.OPEN_HAND);
        }
    };

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public boolean isPlayed() {
        return played;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Path getBorder() {
        return border;
    }

    public void setSliderScale(Slider sliderScale) {
        this.sliderScale = sliderScale;
    }

    public void setSliderRotate(Slider sliderRotate) {
        this.sliderRotate = sliderRotate;
    }

    public void setBouton1(JFXButton bouton1) {
        this.bouton1 = bouton1;
    }

    public void setBouton2(JFXButton bouton2) {
        this.bouton2 = bouton2;
    }
}
