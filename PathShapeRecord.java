package sample;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sol on 01/03/2016.
 */
public class PathShapeRecord implements Serializable {

    public static PathShapeRecord transformToRecord(PathShape pathShape){
        double duration = pathShape.getPathTransition().getDuration().toSeconds();
        PathShapeRecord pathShapeRecord  = new PathShapeRecord(pathShape.getShapeInformations(),pathShape.getPathInformation(),duration);
        pathShapeRecord.setRotate(pathShape.getRotate());
        pathShapeRecord.setScaleX(pathShape.getScaleX());
        pathShapeRecord.setScaleY(pathShape.getScaleY());
        pathShapeRecord.setPathColor(pathShape.getPathColor());
        pathShapeRecord.setShapeColor(pathShape.getShapeColor());
        return pathShapeRecord;
    }


    public static PathShape transfomFromRecord(PathShapeRecord pathShapeRecord){

        //pour le shape
        Shape shape = fusion(pathShapeRecord.getShapeInformations());
        MyColor shapeColor =pathShapeRecord.getShapeColor();
        shape.setFill(shapeColor.getRealColor());
        shape.setOpacity(shapeColor.getOpacity());

        //pour le path
        Shape path = pathShapeRecord.getPathInformation().getShape();
        MyColor pathColor = pathShapeRecord.getPathColor();
        path.setStroke(shapeColor.getRealColor());
        path.setOpacity(pathColor.getOpacity());
        path.getStrokeDashArray().addAll(10d, 10d);

        PathTransition pathTransition = new PathTransition(Duration.seconds(pathShapeRecord.duration),path,shape);

        PathShape pathShape = new PathShape(pathTransition,pathShapeRecord.getShapeInformations(),pathShapeRecord.getPathInformation());
        pathShape.setRotate(pathShapeRecord.getRotate());
        pathShape.setScaleX(pathShapeRecord.getScaleX());
        pathShape.setScaleY(pathShapeRecord.getScaleY());
        pathShape.setPathColor(pathColor);
        pathShape.setShapeColor(shapeColor);


        pathTransition.getNode().setRotate(pathShape.getRotate());
        pathTransition.getNode().setScaleX(pathShape.getScaleX());
        pathTransition.getNode().setScaleY(pathShape.getScaleY());

        return  pathShape;
    }
   /* private static Shape fusion(ArrayList<ShapeInformation> list){
        Shape shape = list.get(0).getShape();
        for(ShapeInformation shapeInformation : list){
            if(list.indexOf(shapeInformation) !=0){
                shape  = Shape.union(shape ,shapeInformation.getShape());
            }
        }
        return shape;*/

    public  static Shape fusion(ArrayList<ShapeInformation> list){
        if(list != null) {
            int i =0;
            double stroke =0;
            Shape shape = list.get(0).getShape();
            stroke = stroke +shape.getStrokeWidth();
            i++;

            for (ShapeInformation shapeInformation : list) {
                if (list.indexOf(shapeInformation) != 0) {
                    double width = shape.getStrokeWidth();
                    shape = Shape.union(shape, shapeInformation.getShape());
                    stroke = stroke +shape.getStrokeWidth();
                    i++;
                }
            }
            shape.setStroke(((Color)shape.getFill()).brighter());
            shape.setStrokeType(StrokeType.OUTSIDE);
            shape.setStrokeWidth(stroke/i);

            return shape;
        }
        else {
            //a modifier
            return new Circle(200);

        }
    }
    //}

    private ArrayList<ShapeInformation> shapeInformations;
    private ShapeInformation pathInformation;
    private double duration;

    private double rotate ;
    private double scaleX;
    private double scaleY;
    private MyColor pathColor;
    private MyColor shapeColor;

    public PathShapeRecord( ArrayList<ShapeInformation> shapeInformations,ShapeInformation shapeInformation,double duration) {
        this.shapeInformations = shapeInformations;
        this.pathInformation = shapeInformation;
        this.duration = duration;
    }

    public ShapeInformation getPathInformation() {
        return pathInformation;
    }

    public double getDuration() {
        return duration;
    }

    public ArrayList<ShapeInformation> getShapeInformations() {
        return shapeInformations;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public MyColor getPathColor() {
        return pathColor;
    }

    public void setPathColor(MyColor pathColor) {
        this.pathColor = pathColor;
    }

    public MyColor getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(MyColor shapeColor) {
        this.shapeColor = shapeColor;
    }
}
