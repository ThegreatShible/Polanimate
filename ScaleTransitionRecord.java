package sample;

import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import java.io.Serializable;

/**
 * Created by sol on 26/02/2016.
 */
public class ScaleTransitionRecord implements Serializable{

    private double toX;
    private double toY;
    private double duration;

    public static ScaleTransitionRecord transformToRecord(ScaleTransition scaleTransition){
        ScaleTransitionRecord scaleTransitionRecord = new ScaleTransitionRecord(scaleTransition.getToX(),scaleTransition.getToY(),scaleTransition.getDuration().toSeconds());
        return scaleTransitionRecord;
    }

    public static ScaleTransition transformFromRecord(ScaleTransitionRecord scaleTransitionRecord){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(scaleTransitionRecord.getDuration()));
        scaleTransition.setToX(scaleTransitionRecord.getToX());
        scaleTransition.setToY(scaleTransitionRecord.getToY());

        return scaleTransition;
    }

    public ScaleTransitionRecord(double toX, double toY, double duration) {
        this.toX = toX;
        this.toY = toY;
        this.duration = duration;
    }

    public double getToX() {
        return toX;
    }

    public void setToX(double toX) {
        this.toX = toX;
    }

    public double getToY() {
        return toY;
    }

    public void setToY(double toY) {
        this.toY = toY;
    }

    public double getDuration() {
        return duration;
    }
}
