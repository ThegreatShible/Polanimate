package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

import java.io.Serializable;

/**
 * Created by sol on 27/02/2016.
 */
public class CircleInformation extends ShapeInformation implements Serializable{


    public CircleInformation(double rayon){
        this.rayon=rayon;
        scaleX=1;
        scaleY=1;
    }
    public CircleInformation(double rayon, double scaleX, double scaleY, double translateX, double translateY, double rotate) {
        this.rayon=rayon;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.rotate = rotate;
    }

    @Override

    public Shape getShape() {
        Circle circle = MyShape.creatCircle(rayon);
        circle.setStrokeWidth(rayon/10);
        circle.setStrokeType(StrokeType.OUTSIDE);
        circle.setTranslateX(translateX);
        circle.setTranslateY(translateY);
        circle.setRotate(rotate);
        circle.setScaleY(scaleY);
        circle.setScaleX(scaleX);

        return circle;
    }

    @Override
    public String toString() {
        return "cercle de rayon "+rayon;
    }
}
