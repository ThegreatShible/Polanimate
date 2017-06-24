package sample;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

/**
 * Created by sol on 24/02/2016.
 */
public class DrawPath extends Group implements Drawable{
    final Path path =  new Path();
    final Shape shape;
    private final Rectangle rectangle;
    PathInformation pathInformation=new PathInformation();
    public DrawPath(Shape shape ,double x, double y){
        this.shape= shape;
        Rectangle rectangle = new Rectangle(x,y);
        this.rectangle =rectangle;
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.7);
        this.getChildren().add(rectangle);
        path.setStrokeWidth(1);
        this.getChildren().add(path);
        this.setOnMousePressed(e->{
            path.getElements().clear();
            path.getElements().add(new MoveTo(e.getX(),e.getY()));
            pathInformation.moveTo(e.getX(),e.getY());
        });
        this.setOnMouseDragged(e->{
            if(rectangle.contains(e.getX(),e.getY())) {
                path.getElements().add(new LineTo(e.getX(), e.getY()));
                pathInformation.lineTo(e.getX(), e.getY());
            }
        });
        this.setOnMouseReleased(e->{
            path.getStrokeDashArray().addAll(10d, 10d);
            path.setStroke(shape.getFill());
            double tranX = shape.getTranslateX();
            double tranY = shape.getTranslateY();
            if(shape instanceof Path){
                Path path1 = (Path)shape;
                shape.setTranslateX((pathInformation.getPath().get(0).getX())-path1.impl_getPivotX());
                shape.setTranslateY((pathInformation.getPath().get(0).getY())-path1.impl_getPivotY());
                if((shape.getBoundsInParent().getMinX()< 0)  || (shape.getBoundsInParent().getMaxX()>x)   || (shape.getBoundsInParent().getMinY()<0)  || (shape.getBoundsInParent().getMaxX() > y)){
                    shape.setTranslateX(tranX);
                    shape.setTranslateY(tranY);
                }
            }else {
                shape.setTranslateX((pathInformation.getPath().get(0).getX()));
                shape.setTranslateY((pathInformation.getPath().get(0).getY()));
            }


        });
        try {
            Image image = new Image(getClass().getResource("tool.png").toExternalForm());
            this.setCursor(new ImageCursor(image));
        }catch (Exception e){
            e.printStackTrace();
            this.setCursor(Cursor.CROSSHAIR);
        }

    }

    @Override
    public ShapeInfo getShapeInfo() {
        ArrayList<ShapeInformation> list = new ArrayList();
        list.add(pathInformation);
        MyColor myColor;
        if(path.getFill() instanceof Color){
            Color color = (Color)path.getFill();
            myColor = new MyColor(color.getRed(),color.getGreen(),color.getBlue(),path.getOpacity());
        }else
            myColor = new MyColor(0,0,0,1);

        return new ShapeInfo(path,list,myColor);
    }
}
