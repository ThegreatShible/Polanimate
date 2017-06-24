package sample;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by sol on 12/03/2016.
 */
public class MyColor implements Serializable{

    private double blue;
    private double green;
    private double red;
    private double opacity=1;

    public MyColor(double red , double green ,double blue,double opacity){
        this.red = red;
        this.green = green;
        this.blue =blue;
        if(opacity !=0) {
            this.opacity = opacity;
        }
    }

    public double getBlue() {
        return blue;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }

    public double getGreen() {
        return green;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public double getRed() {
        return red;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public Color getRealColor(){

        return Color.color(this.getRed(),this.getGreen(),this.getBlue());
    }

    public double getOpacity() {
        return opacity;
    }

    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }
}
