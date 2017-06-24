package sample;

import com.sun.javafx.geom.Point2D;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

/**
 * Created by sol on 07/03/2016.
 */
public class ShapeCreation extends Parent {

    //shapeInfo a retourner
    private ArrayList<ShapeInformation> list=new ArrayList<>();
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    //shape complexe crée
    private Shape firstShape;

    //shape ajouté
    public  Shape currentShape;

    //shapeInformation du shape complexe
    private ShapeInformation currentShapeInformation;

    //boolean pour dire que les shapes sont liées et qu'on peut rajouter un autre
    private boolean linked=true;

    //nombre de shape combinés en shape complexe
    private int nbShapes=0;

    //resolution du groupe
    private final double width,height;

    private double moy =0;
    private double i = 0;


    public ShapeCreation(double x,double y){
        Rectangle rectangle =new Rectangle(x,y);
        rectangle.setOnMouseClicked(e->{
        });
        width = x;
        height =y;
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.GRAY);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        this.getChildren().add(rectangle);

    }


    //ajoute une forme a la shapeCreation
    public void addShape(ShapeInformation shapeInformation,Color color){
        if(linked) {

            Shape shape = shapeInformation.getShape();

            shape.setFill(color);

            linked =false;
            currentShapeInformation = shapeInformation;
            currentShape = shape;
            shape.setOnMousePressed(shapeOnMousePressedEventHandler);
            shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler) ;
            shape.setOnMouseEntered(shapeOnMouseEnteredEventHandler);
            shape.setOnMouseExited(shapeOnMouseExitedHandler);
            shape.setOnMouseReleased(shapeOnMouseReleasedHandler);



            //modifier currentShapeInformation
            //ajouter un event au shape
            shape.setOnMouseClicked(e->{
                if(e.getClickCount()==2 && e.getButton() ==MouseButton.PRIMARY){
                    try {
                        valider();
                    }catch (Exception n){

                    }
                }
            });

            //n'est pas fini
            this.getChildren().add(shape);
            shape.setTranslateX(width/2);
            shape.setTranslateY(height/2);

        }

    }


    //exporte le resultat
    public ShapeInfo export(){
        if(nbShapes ==0) {
            if (currentShape != null) {
                valider();
                ArrayList<ShapeInformation> shapeInformations = new ArrayList<>();
                shapeInformations.add(currentShapeInformation);
                MyColor myColor;

                if(currentShape.getFill() instanceof Color){
                    Color color = (Color)currentShape.getFill();

                    myColor = new MyColor(color.getRed(),color.getGreen(),color.getBlue(),0.7);

                }else
                    myColor = new MyColor(0,0,0,1);


                ShapeInfo shapeInfo = new ShapeInfo(firstShape, shapeInformations,myColor);
                firstShape.setOpacity(0.7);
                firstShape.setStrokeWidth(moy/i);
                firstShape.setStroke(((Color)firstShape.getFill()).brighter());
                this.getChildren().remove(shapeInfo.getShape());
                return shapeInfo;

            }
            else
                return null;
        }
        else {
            MyColor myColor;
            if(firstShape.getFill() instanceof Color){

                Color color = (Color)firstShape.getFill();
                myColor = new MyColor(color.getRed(),color.getGreen(),color.getBlue(),firstShape.getOpacity());
            }else
                myColor = new MyColor(0,0,0,1);
            ShapeInfo shapeInfo = new ShapeInfo(firstShape, list,myColor);
            firstShape.setOpacity(0.7);
            firstShape.setStrokeWidth(moy/i);
            firstShape.setStroke(((Color)firstShape.getFill()).brighter());
            return shapeInfo;
        }
    }


    EventHandler<MouseEvent> shapeOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    Shape shape = (Shape) t.getSource();
                    Point2D point2D = new Point2D(5, 5);


                    if (newTranslateX + ((Shape) (t.getSource())).impl_getPivotX() - ((Shape) (t.getSource())).getBoundsInLocal().getWidth()/2 >=0 && newTranslateY + ((Shape) (t.getSource())).impl_getPivotY() - ((Shape) (t.getSource())).getBoundsInLocal().getHeight()/2 >=0&&newTranslateY + ((Shape) (t.getSource())).impl_getPivotY() >=0 && newTranslateX <= width - ((Shape) (t.getSource())).getLayoutBounds().getMaxX() && newTranslateY <= height - ((Shape) (t.getSource())).getLayoutBounds().getMaxY()) {
                        ((Shape) (t.getSource())).setTranslateX(newTranslateX);
                        ((Shape) (t.getSource())).setTranslateY(newTranslateY);
                    }
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



    //valide le shape
    public void valider(){

            if(nbShapes ==0){

                currentShapeInformation.translateX=currentShape.getTranslateX();
                currentShapeInformation.translateY = currentShape.getTranslateY();
                currentShapeInformation.scaleX = currentShape.getScaleX();
                currentShapeInformation.scaleY = currentShape.getScaleY();
                currentShapeInformation.rotate= currentShape.getRotate();
                Color color = (Color) currentShape.getFill();
                this.getChildren().remove(currentShape);
                moy = currentShape.getStrokeWidth();
                i = 1;
                firstShape =  Shape.union(currentShape,currentShape);

                //currentShape =null;
                firstShape.setFill(color);
                this.getChildren().add(firstShape);
               /* firstShape.removeEventHandler(MouseEvent.MOUSE_PRESSED,shapeOnMousePressedEventHandler);
                firstShape.removeEventHandler(MouseDragEvent.MOUSE_DRAGGED,shapeOnMouseDraggedEventHandler);*/

                linked=true;
                nbShapes++;
                list.add(currentShapeInformation);
            }else{
                Shape shape1 = Shape.intersect(firstShape,currentShape);
                if(shape1.getBoundsInLocal().getWidth() !=-1) {
                    currentShapeInformation.translateX=currentShape.getTranslateX();
                    currentShapeInformation.translateY = currentShape.getTranslateY();
                    currentShapeInformation.scaleX = currentShape.getScaleX();
                    currentShapeInformation.scaleY = currentShape.getScaleY();
                    currentShapeInformation.rotate= currentShape.getRotate();
                    Color color = (Color)firstShape.getFill();
                    moy +=currentShape.getStrokeWidth();
                    moy += currentShape.getStrokeWidth();
                    i+=2;
                    this.getChildren().removeAll(currentShape,firstShape);
                    firstShape = Shape.union(firstShape, currentShape);
                    firstShape.setFill(color);
                    this.getChildren().add(firstShape);
                    linked=true;
                    nbShapes++;
                    list.add(currentShapeInformation);
                }
            }
        }

    //retourne firstShape qui est le shape complexe
    public Shape getShape(){
        if(nbShapes ==0)
            return currentShape;
        return firstShape;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }
}
