package sample;

import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

import java.io.*;


public  abstract class AbstractRecord implements Serializable{
    private static Parent transformFromRecord(AbstractRecord abstractRecord,double x,double y){

        ///A REMODIFIER CE N'EST PAS ENCORE FINI
       // Parent parent;
        if(abstractRecord instanceof TransitionRecord){
            ObservableList<ShapeTransition> list;
            TransitionRecord transitionRecord =(TransitionRecord)abstractRecord;
            list =TransitionRecord.transformFromRecord(transitionRecord);
            return new TransitionAnimation(list,x,y);

        }else{
            PathRecord pathRecord = (PathRecord)abstractRecord;
            ObservableList<PathShape> list = PathRecord.transfomFromRecord(pathRecord);
            PathAnimation pathAnimation = new PathAnimation(list,x,y);
            return pathAnimation;
        }


    }

    public static Parent loadAnimation(String path,double x,double y)throws Exception{

        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(path))));
        AbstractRecord abstractRecord =(AbstractRecord)objectInputStream.readObject();

            return transformFromRecord(abstractRecord,x,y);

    }

    private boolean isTransition;
    public abstract boolean isTransition();


}
