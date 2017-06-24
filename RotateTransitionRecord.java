package sample;

import javafx.animation.RotateTransition;
import javafx.util.Duration;

import java.io.Serializable;

/**
 * Created by sol on 26/02/2016.
 */
public class RotateTransitionRecord implements Serializable{
    private double toAngle;
    private double duration;

    public static RotateTransitionRecord transformToRecord(RotateTransition rotateTransition){
        RotateTransitionRecord rotateTransitionRecord = new RotateTransitionRecord(rotateTransition.getToAngle(),rotateTransition.getDuration().toSeconds());
        return  rotateTransitionRecord;
    }

    public static RotateTransition transformFromRecord(RotateTransitionRecord rotateTransitionRecord){

        RotateTransition rotateTransition= new RotateTransition(Duration.seconds(rotateTransitionRecord.getDuration()));
        rotateTransition.setToAngle(rotateTransitionRecord.getToAngle());

        return rotateTransition;
    }

    public RotateTransitionRecord(double toAngle, double duration) {
        this.toAngle = toAngle;
        this.duration = duration;
    }

    public double getToAngle() {
        return toAngle;
    }

    public void setToAngle(double toAngle) {
        this.toAngle = toAngle;
    }

    public double getDuration() {
        return duration;
    }
}
