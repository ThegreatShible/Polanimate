package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import sample.MyShape;

import java.io.Serializable;

/**
 * Created by sol on 26/02/2016.
 */
public abstract class ShapeInformation implements Serializable {



    protected double rayon;
    protected double scaleX;
    protected double scaleY;
    protected double translateX;
    protected double translateY;
    protected double rotate;

    /*public ShapeInformation(double rayon, double scaleX, double scaleY, double translateX, double translateY, double rotate) {
        this.rayon=rayon;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.rotate = rotate;
    }*/
    public abstract Shape getShape();
}
