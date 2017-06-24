package sample;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

import java.io.Serializable;

/**
 * Created by sol on 26/02/2016.
 */
public class ParallelTransitionRecord implements Serializable{

    public static ParallelTransitionRecord transformToRecord(ParallelTransition parallelTransition) {
        ParallelTransitionRecord parallelTransitionRecord = new ParallelTransitionRecord();

        //ajout des transition
        TranslateTransitionRecord translateTransitionRecord = TranslateTransitionRecord.transformToRecord((TranslateTransition) parallelTransition.getChildren().get(0));
        parallelTransitionRecord.setTranslateTransitionRecord(translateTransitionRecord);

        RotateTransitionRecord rotateTransitionRecord = RotateTransitionRecord.transformToRecord((RotateTransition)parallelTransition.getChildren().get(1));
        parallelTransitionRecord.setRotateTransitionRecord(rotateTransitionRecord);

        ScaleTransitionRecord scaleTransitionRecord = ScaleTransitionRecord.transformToRecord((ScaleTransition)parallelTransition.getChildren().get(2));
        parallelTransitionRecord.setScaleTransitionRecord(scaleTransitionRecord);


        return  parallelTransitionRecord;
    }

    public static ParallelTransition transformFromRecord(ParallelTransitionRecord parallelTransitionRecord){
        ParallelTransition parallelTransition = new ParallelTransition();

        TranslateTransition translateTransition = TranslateTransitionRecord.transformFromRecord(parallelTransitionRecord.getTranslateTransitionRecord());
        parallelTransition.getChildren().add(translateTransition);

        RotateTransition rotateTransition = RotateTransitionRecord.transformFromRecord(parallelTransitionRecord.getRotateTransitionRecord());
        parallelTransition.getChildren().add(rotateTransition);

        ScaleTransition scaleTransition = ScaleTransitionRecord.transformFromRecord(parallelTransitionRecord.getScaleTransitionRecord());
        parallelTransition.getChildren().add(scaleTransition);

        return parallelTransition;
    }
    private TranslateTransitionRecord translateTransitionRecord;
    private RotateTransitionRecord rotateTransitionRecord;
    private ScaleTransitionRecord scaleTransitionRecord;


    public TranslateTransitionRecord getTranslateTransitionRecord() {
        return translateTransitionRecord;
    }

    public void setTranslateTransitionRecord(TranslateTransitionRecord translateTransitionRecord) {
        this.translateTransitionRecord = translateTransitionRecord;
    }

    public RotateTransitionRecord getRotateTransitionRecord() {
        return rotateTransitionRecord;
    }

    public void setRotateTransitionRecord(RotateTransitionRecord rotateTransitionRecord) {
        this.rotateTransitionRecord = rotateTransitionRecord;
    }

    public ScaleTransitionRecord getScaleTransitionRecord() {
        return scaleTransitionRecord;
    }

    public void setScaleTransitionRecord(ScaleTransitionRecord scaleTransitionRecord) {
        this.scaleTransitionRecord = scaleTransitionRecord;
    }

}