package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sol on 01/03/2016.
 */
public class PathRecord extends AbstractRecord implements Serializable {

    public static PathRecord transformToRecord(PathAnimation pathAnimation){
        PathRecord pathRecord= new PathRecord();
        for(PathShape pathShape : pathAnimation.getPathList()){
            PathShapeRecord pathShapeRecord = PathShapeRecord.transformToRecord(pathShape);
            pathRecord.pathList.add(pathShapeRecord);
        }
        return pathRecord;
    }
    public static ObservableList<PathShape> transfomFromRecord(PathRecord pathRecord){
        ObservableList<PathShape> list = FXCollections.observableArrayList();
        for(PathShapeRecord pathShapeRecord : pathRecord.pathList){
            list.add(PathShapeRecord.transfomFromRecord(pathShapeRecord));
        }
        return  list;
    }


    private ArrayList<PathShapeRecord> pathList=new ArrayList<>();




    @Override
    public boolean isTransition() {
        return false;
    }

    public ArrayList<PathShapeRecord> getPathList() {
        return pathList;
    }
}
