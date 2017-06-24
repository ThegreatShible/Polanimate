package sample;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.io.Serializable;

/**
 * Created by sol on 26/02/2016.
 */
public class TranslateTransitionRecord implements Serializable {

    public static TranslateTransitionRecord transformToRecord(TranslateTransition translateTransition){
        TranslateTransitionRecord translateTransitionRecord =
                new TranslateTransitionRecord(translateTransition.getToX(),translateTransition.getToY(),translateTransition.getDuration().toSeconds());
        return  translateTransitionRecord;
    }

    public static TranslateTransition transformFromRecord(TranslateTransitionRecord translateTransitionRecord){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(translateTransitionRecord.getDuration()));
        translateTransition.setToX(translateTransitionRecord.getToX());
        translateTransition.setToY(translateTransitionRecord.getToY());

        return translateTransition;
    }
    private double toX;
    private double toY;
    private double duration;

    public TranslateTransitionRecord(double toX, double toY, double duration) {
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
