package sample;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.io.Serializable;

/**
 * Created by sol on 27/02/2016.
 */
public class PolygonInformation extends ShapeInformation implements Serializable{
    private int nbCotes;

    public PolygonInformation(double rayon, int nbCotes, double scaleX, double scaleY, double translateX, double translateY, double roatate) {

        this.rayon=rayon;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.translateX = translateX;
        this.translateY = translateY;
        this.rotate = rotate;
        this.nbCotes = nbCotes;

    }

    public PolygonInformation(double rayon , int nbCotes) {
        this.rayon = rayon;
        this.nbCotes = nbCotes;
        scaleX=1;
        scaleY =1;
    }

    @Override


    public Shape getShape() {
        Polygon polygon =MyShape.creatPolygon(rayon,nbCotes);
        polygon.setTranslateX(translateX);
        polygon.setTranslateY(translateY);
        polygon.setRotate(rotate);
        polygon.setScaleX(scaleX);
        polygon.setScaleY(scaleY);

        return  polygon;
    }
}
