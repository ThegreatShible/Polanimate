package sample;

import javafx.geometry.Point2D;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by sol on 01/03/2016.
 */
public class PathInformation extends ShapeInformation {
   private ArrayList<MyPoint> path = new ArrayList(10);

    @Override
    public Shape getShape() {
        Path pathShape = new Path();
        for(MyPoint myPoint : path){
            if(path.indexOf(myPoint)==0){
                pathShape.getElements().add(new MoveTo(myPoint.getX(),myPoint.getY()));
            }
            else
                pathShape.getElements().add(new LineTo(myPoint.getX(),myPoint.getY()));
        }
        return pathShape;
    }

    public void moveTo(double x,double y){
        System.out.println(path.size());
        path.add(0,new MyPoint(x,y));
    }

    public void lineTo(double x,double y){
        path.add(new MyPoint(x,y));
    }

    public ArrayList<MyPoint> getPath() {
        return path;
    }
}
