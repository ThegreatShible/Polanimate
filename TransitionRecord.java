package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import sample.AbstractRecord;
import sample.ShapeTransition;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sol on 21/02/2016.
 */
public class TransitionRecord extends AbstractRecord implements Serializable{

    //cette fonction transforme transitionAnimation en transitionRecord
    public static TransitionRecord transformToRecord(TransitionAnimation transitionAnimation){

        //list de shapeTransitionRecord pour la transitionRecord
        ArrayList<ShapeTransitionRecord> list =  new ArrayList<>();
        for(ShapeTransition shapeTransition : transitionAnimation.getShapeList()){
            list.add(ShapeTransitionRecord.transformToRecord(shapeTransition));

        }

        return new TransitionRecord(list);
    }

    public static ObservableList<ShapeTransition> transformFromRecord(TransitionRecord transitionRecord){
        ObservableList<ShapeTransition> list = FXCollections.observableArrayList();
        for(ShapeTransitionRecord shapeTransitionRecord :transitionRecord.getShapeList()){
            ShapeTransition shapeTransition = ShapeTransitionRecord.transformFromRecord(shapeTransitionRecord);
            list.add(shapeTransition);
        }
        return list;
    }

    private ArrayList<ShapeTransitionRecord> shapeList;
    public  TransitionRecord(ArrayList<ShapeTransitionRecord> list){

        shapeList = list;
    }

    public ArrayList<ShapeTransitionRecord> getShapeList() {
        return shapeList;
    }

    @Override
    public boolean isTransition() {
        return true;
    }

}
