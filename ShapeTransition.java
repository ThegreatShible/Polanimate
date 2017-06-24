package sample;

import javafx.animation.SequentialTransition;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by sol on 16/02/2016.
 */
public class ShapeTransition {
    private SequentialTransition sequentialTransition ;
    private Shape shape;
    private ArrayList<ShapeInformation> shapeInformations;
    private boolean modified;
    private double translateX;
    private double translateY;
    private double angle;
    private double scaleX;
    private double scaleY;
    private MyColor color;

    public ShapeTransition(SequentialTransition sequentialTransition,ArrayList<ShapeInformation> list) {
        this.sequentialTransition = sequentialTransition;
        this.shapeInformations = list;
        shape=((Shape)sequentialTransition.getNode());
        shape.translateXProperty().addListener(e->{
            modified = true;
        });
        shape.translateYProperty().addListener(e->{
            modified = true;
        });
        shape.scaleXProperty().addListener(e->{
            modified = true;
        });
        shape.scaleYProperty().addListener(e->{
            modified = true;
        });
        shape.rotateProperty().addListener(e->{
            modified = true;
        });
        modified=false;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateY(double y) {
        this.translateY = y;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateX(double x) {
        this.translateX= x;
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

    public SequentialTransition getSequentialTransition() {
        return sequentialTransition;
    }

    public void setSequentialTransition(SequentialTransition sequentialTransition) {
        this.sequentialTransition = sequentialTransition;
    }

    public Shape getShape() {
        return shape;
    }


    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public ArrayList<ShapeInformation> getShapeInformations() {
        return shapeInformations;
    }

    public void setShapeInformations(ArrayList<ShapeInformation> shapeInformations) {
        this.shapeInformations = shapeInformations;
    }

    public MyColor getColor() {
        return color;
    }

    public void setColor(MyColor color) {
        this.color = color;
    }
}
