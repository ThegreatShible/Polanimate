package sample;

import com.jfoenix.controls.JFXSlider;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by sol on 09/04/2016.

 */
public class PlayAnimation extends Group {

    private final double OPACITY_VALUE = 1;
    private  final ParallelTransition parallelTransition ;
    private final SequentialTransition sequentialTransition;
    private boolean show ;
    private boolean entered;
    private final  PlayBar playBar= new PlayBar()  ;
    private boolean play ;

    private final double height ;
    private final double width ;
    private final double playBarHeight = 70;


    public PlayAnimation(ParallelTransition parallelTransition,double Width,double Height){
        super();
        this.parallelTransition = parallelTransition;
        parallelTransition.setOnFinished(e->{
            play = false;
            playBar.playPause.setText("play");

        });


        width = Width;
        height = Height;
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setTranslateY(0);
        this.getChildren().add(rectangle);
        rectangle.setFill(Color.TRANSPARENT);


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), playBar);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(OPACITY_VALUE);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(4));

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), playBar);
        fadeTransition1.setFromValue(OPACITY_VALUE);
        fadeTransition1.setToValue(0);

         sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(fadeTransition, pauseTransition, fadeTransition1);
        sequentialTransition.setOnFinished(e->{
            this.getChildren().remove(playBar);
            show = false;
            this.setCursor(Cursor.NONE);

        });




        this.setOnMouseEntered(e->{
            if(!entered) {
                if (!show) {
                    playBar.setTranslateY(height-70);
                    playBar.setOpacity(0);
                    this.getChildren().add(playBar);
                    show = true;
                    sequentialTransition.play();
                    this.setCursor(Cursor.DEFAULT);
                } else {
                    sequentialTransition.playFrom(Duration.seconds(0.5));
                }
            }


        });

        this.setOnMouseMoved(e->{
            if(!entered) {
                if (!show) {
                    playBar.setTranslateY(height-70);
                    playBar.setOpacity(0);
                    this.getChildren().add(playBar);
                    show = true;
                    sequentialTransition.play();
                    this.setCursor(Cursor.DEFAULT);

                } else {
                    sequentialTransition.playFrom(Duration.seconds(0.5));
                }
            }
        });







        playBar.slider.setMax( parallelTransition.getTotalDuration().toSeconds());
        playBar.slider.setMin(0);


        playBar.slider.valueProperty().addListener((e,o,n)->{
            parallelTransition.jumpTo(Duration.seconds(playBar.slider.getValue()));
        });
        playBar.slider.setOnMousePressed(e->{
            parallelTransition.play();
            parallelTransition.pause();

        });
        playBar.slider.setPrefWidth(width-10);
        playBar.slider.setOnMouseReleased(e->{
            if(play)
                parallelTransition.play();
        });
        parallelTransition.currentTimeProperty().addListener((e,o,n)->{
            playBar.slider.setValue(n.toSeconds());

        });

        playBar.playPause.setOnMouseClicked(e->{
            if(play){
                parallelTransition.pause();
                play = false;
                playBar.playPause.setText("play");
            }else{
                parallelTransition.play();
                play = true;
                playBar.playPause.setText("pause");
            }
        });

        playBar.setOnMouseEntered(e->{
            entered = true;
            sequentialTransition.playFrom(Duration.seconds(0.5));
            sequentialTransition.pause();
            playBar.setOpacity(0.7);

        });
        playBar.setOnMouseExited(e->{
            entered = false;
            sequentialTransition.play();
            playBar.setOpacity(OPACITY_VALUE);
        });
    }

    public class PlayBar extends  Parent {

        JFXSlider slider ;
        Button playPause;
        public PlayBar(){


            Rectangle rectangle = new Rectangle(width,70);

            rectangle.setFill(Color.BLACK);
            this.getChildren().add(rectangle);



            slider = new JFXSlider();

            slider.setPrefWidth(width);
            slider.setPrefHeight(10);
            slider.setTranslateX(0);
            slider.getStyleClass().add("jfx-slider-style3");
            this.getChildren().add(slider);



            playPause = new Button("play");

            playPause.setPrefHeight(50);
            playPause.setPrefWidth(50);
            playPause.setTranslateY(20);
            this.getChildren().add(playPause);




        }


    }

}
