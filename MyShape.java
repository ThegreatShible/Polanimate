package sample;

import com.sun.glass.ui.Cursor;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

/**
 * Created by sol on 26/02/2016.
 */
public class MyShape {
    public static Polygon creatPolygon(double rayon , int nbCotes)
    {
        double[] tableau;

        tableau = createPolyPoints(rayon, nbCotes);
        Polygon polygon = new Polygon(tableau);
        return polygon;
    }
    private static double[] createPolyPoints(double rayon,int nbCotes){
        double[] tableau = new double[nbCotes*2];
        //double special pour le carré
        double a;
        if(nbCotes ==4) {
            a = Math.PI / nbCotes;
            for (int i = 0; i < nbCotes * 2; i += 2) {
                tableau[i] = rayon * Math.cos(i * Math.PI / nbCotes + a);
                tableau[i + 1] = rayon * Math.sin(i * Math.PI / nbCotes + a);
            }
        }

        else if(nbCotes ==3)
            for (int i = 0; i < nbCotes * 2; i += 2) {
                tableau[i] = rayon * (Math.cos(i * Math.PI / nbCotes +Math.PI/6));
                tableau[i + 1] = rayon * (Math.sin(i * Math.PI / nbCotes +Math.PI/6));
            }
        else {

            a = 0;
            for (int i = 0; i < nbCotes * 2; i += 2) {
                tableau[i] = rayon * Math.cos(i * Math.PI / nbCotes + a);
                tableau[i + 1] = rayon * Math.sin(i * Math.PI / nbCotes + a);
            }
        }

                return tableau;

    }
    public static Circle creatCircle(double rayon){
        return new Circle(rayon);
    }

    public static void selectShape(Shape shape){

        shape.setStrokeType(StrokeType.OUTSIDE);
        shape.setOnMouseEntered(e->{
            shape.setCursor(javafx.scene.Cursor.OPEN_HAND);
            shape.setStrokeWidth(shape.getStrokeWidth() *1.5);

        });

        shape.setOnMousePressed(e->{


            shape.setCursor(javafx.scene.Cursor.CLOSED_HAND);
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((Shape)(e.getSource())).getTranslateX();
            orgTranslateY = ((Shape)(e.getSource())).getTranslateY();
        });
        shape.setOnMouseReleased(e->{
            shape.setCursor(javafx.scene.Cursor.OPEN_HAND);

        });
        shape.setOnMouseExited(e->{
            shape.setStrokeWidth(shape.getStrokeWidth()/1.5);
            shape.getStrokeDashArray().clear();
        });
        shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        }





    static EventHandler<MouseEvent> shapeOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    if(newTranslateX>=0 && newTranslateY>=0 && newTranslateX<=850-((Shape)(t.getSource())).getLayoutBounds().getMaxX() && newTranslateY<=563 - ((Shape)(t.getSource())).getLayoutBounds().getMaxY()) {
                        ((Shape) (t.getSource())).setTranslateX(newTranslateX);
                        ((Shape) (t.getSource())).setTranslateY(newTranslateY);
                    }
                }
            };

    static EventHandler<MouseEvent> shapeOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Shape)(t.getSource())).getTranslateY();
                }
            };
    static double orgSceneX,orgSceneY,orgTranslateX,orgTranslateY;

}
