package sample;

import javafx.animation.PathTransition;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by sol on 01/03/2016.
 */
public class PathShape {

    // la pathTransition
    private PathTransition pathTransition;

    //a list des shapeInformation pour creer la forme complexe
    private ArrayList<ShapeInformation> shapeInformations;

    //informations sur le chemin
    private ShapeInformation pathInformation;

    //les caracteristiques de la forme;
    private double rotate;
    private double scaleX;
    private double scaleY;
    private MyColor pathColor;
    private MyColor shapeColor;


    public PathShape(PathTransition pathTransition, ArrayList<ShapeInformation> shapeInformations,ShapeInformation pathInformation) {
        this.pathTransition = pathTransition;
        this.shapeInformations = shapeInformations;
        this.pathInformation = pathInformation;
    }

    public PathTransition getPathTransition(){
        return pathTransition;
    }

    public ArrayList<ShapeInformation> getShapeInformations() {
        return shapeInformations;
    }

    public ShapeInformation getPathInformation() {
        return pathInformation;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
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

    public MyColor getPathColor() {
        return pathColor;
    }

    public void setPathColor(MyColor pathColor) {
        this.pathColor = pathColor;
    }

    public MyColor getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(MyColor shapeColor) {
        this.shapeColor = shapeColor;
    }
}
