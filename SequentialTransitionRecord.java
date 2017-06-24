package sample;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sol on 26/02/2016.
 */
public class SequentialTransitionRecord implements Serializable{

    public static SequentialTransitionRecord transformTo(SequentialTransition sequentialTransition){
        SequentialTransitionRecord sequentialTransitionRecord = new SequentialTransitionRecord();
        for(Animation animation : sequentialTransition.getChildren()){
            ParallelTransition parallelTransition = (ParallelTransition)animation;
            ParallelTransitionRecord parallelTransitionRecord = ParallelTransitionRecord.transformToRecord(parallelTransition);
            sequentialTransitionRecord.getChildren().add(parallelTransitionRecord);
        }
        return sequentialTransitionRecord;
    }

    public static SequentialTransition transformFromRecord(SequentialTransitionRecord sequentialTransitionRecord){
        SequentialTransition sequentialTransition = new SequentialTransition();
        for(ParallelTransitionRecord parallelTransitionRecord :sequentialTransitionRecord.getChildren()) {
            ParallelTransition parallelTransition = ParallelTransitionRecord.transformFromRecord(parallelTransitionRecord);
            sequentialTransition.getChildren().add(parallelTransition);
        }

        return sequentialTransition;

    }

    private ArrayList<ParallelTransitionRecord> children=new ArrayList<>();

    public ArrayList<ParallelTransitionRecord> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ParallelTransitionRecord> children) {
        this.children = children;
    }
}
