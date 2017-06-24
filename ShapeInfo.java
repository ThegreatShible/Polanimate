package sample;

import javafx.scene.shape.Shape;
import sample.ShapeInformation;

import java.util.ArrayList;

/**
 * Created by sol on 26/02/2016.
 */
public class ShapeInfo {

    private Shape shape;
    private ArrayList<ShapeInformation> shapeInformations;
    private MyColor color;

    public ShapeInfo(Shape shape, ArrayList<ShapeInformation> shapeInformations,MyColor color) {
        this.shape = shape;
        this.shapeInformations = shapeInformations;
        this.color  = color;
    }

    public Shape getShape() {
        return shape;
    }

    public ArrayList<ShapeInformation> getShapeInformations() {
        return shapeInformations;
    }

    public MyColor getColor() {
        return color;
    }

    public  Shape creatShape(){

        if(shapeInformations.size() ==1)
            return shapeInformations.get(0).getShape();
        else{
            Shape shape  = shapeInformations.get(0).getShape();
            for(int i=1;i<shapeInformations.size();i++){
                shape = Shape.union(shape,shapeInformations.get(i).getShape());
            }
            return shape;
        }
    }

}
