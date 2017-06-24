package sample;


import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by sol on 11/03/2016.
 */
public class DrawShape extends Parent implements Drawable {
    ShapeInformation shapeInformation;
    Shape shape ;

    public DrawShape(double x ,double y){
        Rectangle rectangle = new Rectangle(x,y);
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.7);
        this.getChildren().add(rectangle);
    }


    public void AddShape(ShapeInfo shapeInfo){
        if(shape != null){
            this.getChildren().remove(shape);
            shape = shapeInfo.getShape();
            shapeInformation = shapeInfo.getShapeInformations().get(0);
            this.getChildren().add(shape);
            shape.translateXProperty().addListener((e,o,n)->{
                shapeInformation.translateX=(Double)n;
            });
            shape.translateYProperty().addListener((e,o,n)->{
                shapeInformation.translateY=(Double)n;
            });
            shape.scaleXProperty().addListener((e,o,n)->{
                shapeInformation.scaleX=(Double)n;
            });
            shape.scaleYProperty().addListener((e,o,n)->{
                shapeInformation.scaleY = (Double)n;
            });
            shape.rotateProperty().addListener((e,o,n)->{
                shapeInformation.rotate =(Double )n;
            });
        }
    }
    @Override
    public ShapeInfo getShapeInfo() {
        ArrayList<ShapeInformation> shapeInformations = new ArrayList();
        shapeInformations.add(shapeInformation);
        MyColor myColor;
        if(shape.getFill() instanceof Color){
            Color color = (Color)shape.getFill();
            myColor = new MyColor(color.getRed(),color.getGreen(),color.getBlue(),shape.getOpacity());
        }else
            myColor = new MyColor(0,0,0,1);
        ShapeInfo shapeInfo = new ShapeInfo(shape,shapeInformations,myColor);

        return shapeInfo;
    }
}
