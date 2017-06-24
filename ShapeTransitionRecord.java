package sample;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sol on 26/02/2016.
 */
public class ShapeTransitionRecord implements Serializable {
    public static ShapeTransitionRecord transformToRecord(ShapeTransition shapeTransition){

        ShapeTransitionRecord shapeTransitionRecord = new ShapeTransitionRecord();
        shapeTransitionRecord.setSequentialTransitionRecord(SequentialTransitionRecord.transformTo(shapeTransition.getSequentialTransition()));
        shapeTransitionRecord.setTranslateX(shapeTransition.getTranslateX());
        shapeTransitionRecord.setTranslateY(shapeTransition.getTranslateY());
        shapeTransitionRecord.setAngle(shapeTransition.getAngle());
        shapeTransitionRecord.setScaleX(shapeTransition.getScaleX());
        shapeTransitionRecord.setScaleY(shapeTransition.getScaleY());
        shapeTransitionRecord.setShapeInformations(shapeTransition.getShapeInformations());

        shapeTransitionRecord.setColor(shapeTransition.getColor());

        return shapeTransitionRecord;
    }

    public static ShapeTransition transformFromRecord(ShapeTransitionRecord shapeTransitionRecord){

        //shape crée par fusion de la liste
        Shape shape = fusion(shapeTransitionRecord.getShapeInformations());
        shape.setFill(shapeTransitionRecord.getColor().getRealColor());

        //shape.setFill(Color.RED);
//        shape.setOpacity(shapeTransitionRecord.getColor().getOpacity());



        //sequentialTransition de la shapeTransition
        SequentialTransition sequentialTransition = SequentialTransitionRecord.transformFromRecord(shapeTransitionRecord.getSequentialTransitionRecord());
        sequentialTransition.setNode(shape);


        //création de la shapeTranisition
        ShapeTransition shapeTransition = new ShapeTransition(sequentialTransition,shapeTransitionRecord.getShapeInformations());
        shapeTransition.setColor(shapeTransitionRecord.getColor());
        //les autres parametres de  shapeTransition
        shapeTransition.setTranslateX(shapeTransitionRecord.getTranslateX());
        shapeTransition.setTranslateY(shapeTransitionRecord.getTranslateY());
        shapeTransition.setAngle(shapeTransitionRecord.getAngle());
        shapeTransition.setScaleX(shapeTransitionRecord.getScaleX());
        shapeTransition.setScaleY(shapeTransitionRecord.getScaleY());
        shapeTransition.setModified(false);


        //pour le cas spécial du submit a 0 après animation
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
        }

        return shapeTransition;

    }

    public  static Shape fusion(ArrayList<ShapeInformation> list){
        if(list != null) {
            int i =0;
            double stroke =0;
            Shape shape = list.get(0).getShape();
            stroke = stroke +shape.getStrokeWidth();
            i++;
            if(list.size() ==1){
                shape = Shape.union(shape,shape);
            }else {
                for (ShapeInformation shapeInformation : list) {
                    if (list.indexOf(shapeInformation) != 0) {
                        double width = shape.getStrokeWidth();
                        shape = Shape.union(shape, shapeInformation.getShape());
                        stroke = stroke + shape.getStrokeWidth();
                        i++;
                    }
                }
            }
            shape.setStroke(((Color)shape.getFill()).brighter());
            shape.setStrokeType(StrokeType.OUTSIDE);
            shape.setStrokeWidth(stroke/i);
            return shape;
        }
        else {
            //a modifier
            return new Circle(200);

        }
    }

    private SequentialTransitionRecord sequentialTransitionRecord ;
    //private ShapeRecord shape;
    private ArrayList<ShapeInformation> shapeInformations;
    private double translateX;
    private double translateY;
    private double angle;
    private double scaleX;
    private double scaleY;
    private MyColor color;

    public SequentialTransitionRecord getSequentialTransitionRecord() {
        return sequentialTransitionRecord;
    }

    public void setSequentialTransitionRecord(SequentialTransitionRecord sequentialTransitionRecord) {
        this.sequentialTransitionRecord = sequentialTransitionRecord;
    }

   /* public ShapeRecord getShape() {
        return shape;
    }

    public void setShape(ShapeRecord shape) {
        this.shape = shape;
    }*/

    public ArrayList<ShapeInformation> getShapeInformations() {
        return shapeInformations;
    }

    public void setShapeInformations(ArrayList<ShapeInformation> shapeInformations) {
        this.shapeInformations = shapeInformations;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public MyColor getColor() {
        return color;
    }

    public void setColor(MyColor color) {
        this.color = color;
    }

}
