package sample;

import com.jfoenix.controls.*;
import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class Controller implements Initializable {
    static double vd = 0;
    static Color cr = null;
    static int bool = 1;
    Parent parent;
    public ShapeInfo shapeInfo1;
    static boolean isDone = false;
    static boolean isAnswer = false;
    static boolean done = false;
    static boolean done2=false;
    static boolean answer= false;
    static boolean transition=false;
    static boolean chemin = false;
    @FXML
    private Label sessionLabel=new Label();
    @FXML private JFXButton ins;
    @FXML BorderPane borderPane = new BorderPane();
    @FXML private Button getPlay;
    @FXML private Pane pnstk = new Pane();
    @FXML private ColorPicker crw;
    @FXML private Pane graph1 = new Pane();
    @FXML private ImageView id;

    Timeline timeline = new Timeline() ;
    File userDirectoryString =null;

    @FXML private  JFXButton Insann;
    @FXML private JFXPopup popup;

    @FXML private JFXButton notify;
    @FXML private JFXSnackbar snackbar;

    static Personne personne=null;
@FXML ImageView im1;
    @FXML ImageView im2;



    JFXButton rot=new JFXButton();
    JFXButton sc=new JFXButton();
    @FXML
    private Slider kwd;
    @FXML
    public HBox hbox = new HBox();
    @FXML
    public HBox smain = new HBox();
    @FXML
    ToolBar tool;
    @FXML
    Button UserId = new Button();
    @FXML
    Label Lb=new Label();

    @FXML
    public HBox hBox2 = new HBox();

    PathAnimation pathAnimation;
    static double nbr=0;
    @FXML
    private StackedAreaChart graph;
    @FXML private JFXTextField user;
    @FXML private JFXTextField name;
    @FXML private JFXTextField pname;
    @FXML private JFXTextField sname;
    @FXML private JFXPasswordField psw;

    @FXML private JFXPasswordField password;

    @FXML
    private Slider slider,slider1;
    @FXML
    private Button Play;
    @FXML
    private Button rec = new Button();
    @FXML
    private Button Save;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button Sub;
    @FXML
    private JFXButton txt,txt1;
    @FXML
    private Label lbl;
    @FXML
    private ChoiceBox choice;
    @FXML
    private Button getSave;
    @FXML
    private Button load;
    @FXML
    private Button load2;
    @FXML
    private Button getGetSave;
    @FXML
    private JFXButton Strtop;




    private static final String TEXT =
            "  WITCH.  Fillet of a fenny snake,\n" +
                    "In the caldron boil and bake;\n" +
                    "Eye of newt, and toe of frog,\n" +
                    "Wool of bat, and tongue of dog,\n" +
                    "Adder's fork, and blind-worm's sting,\n" +
                    "Lizard's leg, and owlet's wing,—\n" +
                    "For a charm of powerful trouble,\n" +
                    "Like a hell-broth boil and bubble.\n\n" +
                    "  ALL.  Double, double toil and trouble;\n" +
                    "Fire burn, and caldron bubble. ";




    @FXML
    private Button circle,hexagone,triangle;

    final ToggleGroup group = new ToggleGroup();

    @FXML
    private JFXRadioButton Cpro,Cetu;

    TransitionAnimation Trans;

    static ShapeInfo shapeInfo;
    static int Switch = 1;
    @FXML
    private Button star;
    @FXML
    private JFXSlider sli, sld;

    @FXML
    private Button reca;
    static int cpt = 1;
    static boolean swi = false;
    ShapeCreation SC = new ShapeCreation(600, 450);
    @FXML
    private Pane Groupe = new Pane();
    @FXML
    private Button inserer;
    @FXML
    private Button ag = new Button();
    @FXML
    private Button export;
    @FXML
    private Button getRec;
    @FXML
    private Button stoprec;

    @FXML
    private Button UserInfo;
    @FXML
    private StackPane Span = new StackPane();

    JFXRippler rippler = new JFXRippler();

    JFXButton x=new JFXButton("X :");
    JFXButton y=new JFXButton("Y :");

    JFXSlider Lslider=new JFXSlider(1,2,1);
    JFXSlider Lslider1=new JFXSlider(-360,360,0);

    static String Chx;
    @FXML
    Pane pnbottom = new Pane();
    @FXML
    Pane pnbottom2 = new Pane();

    static int too = 1;
    @FXML
    Controller formup;


    @FXML
    private void pnss(ActionEvent event)
            throws IOException, NoShapeException {

        Stage stage;
        Parent root;


        if (event.getSource() == btn3) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("formup.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("My modal window");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btn3.getScene().getWindow());
            stage.showAndWait();

        } else {
            vd = slider.getValue();
            cr = crw.getValue();
            stage = (Stage) btn2.getScene().getWindow();
            stage.close();
        }
        {

           // System.out.println("Hello");
           // System.out.println("ClickedX" + pnstk.getHeight());
           // System.out.println("ClickedY" + pnstk.getWidth());
            //  Rectangle st=new Rectangle((pnstk.getHeight())/2-,((pnstk.getWidth())/2),10,10);
            Rectangle sst = new Rectangle(vd, vd);
            sst.setOpacity(10);
            sst.setFill(cr);
            sst.setX((pnstk.getWidth() - sst.getWidth()) / 2);
            sst.setY((pnstk.getHeight() - sst.getHeight()) / 2);


            pnstk.getChildren().addAll(sst);

           // System.out.println("x =" + pnstk.getHeight() / 2 + " y=" + ((pnstk.getWidth()) / 2));
            bool = 0;
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event)
            throws IOException {

        Stage stage;
        Parent root;

        if (event.getSource() == btn1) {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("formup.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("My modal window");
            //   stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btn1.getScene().getWindow());
            stage.showAndWait();
        } else {
            vd = slider.getValue();
            cr = crw.getValue();
            stage = (Stage) btn2.getScene().getWindow();
            stage.close();
        }

        {

            Circle sst = new Circle(vd);
            sst.setFill(cr);
            sst.setOpacity(20);
            double a = pnstk.getWidth();
            double x = -sst.getCenterX();
            double b = pnstk.getHeight() - sst.getCenterY();
            /*sst.setCenterX((a) / 2);
            sst.setCenterY((b) / 2);
         */


            //Trans.addShape(sst,a/2,b/2);

           /* tool.getItems().addAll(createWarningButton(warningColor, colorStringProperty));*/


        }
    }

    @FXML
    public void poly() {
        // Parent parent = null;
        //Trans.play();
        Trans.play();
    }

    @FXML
    public void Submit() {
        Trans.submit();
    }

    @FXML
    public void Submit2() {
        pathAnimation.submit();
        Strtop.setDisable(false);
        inserer.setDisable(false);
        isAnswer=true;
    }
    /*@FXML;
    public void pause(){Trans.pause();}*/

    //fonction button endo
    @FXML
    public void Cls() {
        Stage stage;
        stage = (Stage) btn2.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Cls1() {
        Stage stage;
        stage = (Stage) Insann.getScene().getWindow();
        stage.close();
    }

   static Circle blinking_ball =new Circle(10,Color.RED);

    static String type="Professeur";

    private static final double init = 100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tool.getItems().addAll(createWarningButton(warningColor, colorStringProperty));

        sli = new JFXSlider(0, 100, 0);
        sli.prefWidth(500);
        sli.setMinWidth(500);
        sli.getStyleClass().add("jfx-slider-style");
        sli.minWidth(320);
        sli.setBlockIncrement(1);
        sli.setMajorTickUnit(2.5);
        sli.setMinorTickCount(1);
        sli.setShowTickLabels(false);
        sli.setShowTickMarks(true);
        sli.setPadding(new Insets(15, 0, 0, 0));
        hbox.getChildren().add(sli);


        sld = new JFXSlider(0, 100, 0);
        sld.prefWidth(500);
        sld.setMinWidth(500);
        sld.getStyleClass().add("jfx-slider-style");
        sld.minWidth(320);
        sld.setBlockIncrement(1);
        sld.setMajorTickUnit(2.5);
        sld.setMinorTickCount(1);
        sld.setShowTickLabels(false);
        sld.setShowTickMarks(true);
        sld.setPadding(new Insets(15, 0, 0, 0));
        hBox2.getChildren().add(sld);

        //Rectangle circle=new Rectangle(20,20);
        sli.valueProperty().addListener((e, oldValue, newValue) -> {
          //  System.out.println(newValue);

        });


        Trans = new TransitionAnimation(sli,pnstk.getPrefWidth(), pnstk.getPrefHeight());
        sli.setDisable(true);
        pathAnimation = new PathAnimation(graph1.getPrefWidth(), graph1.getPrefHeight(), sld);
        sld.setDisable(true);
        Trans.setSliderScale(Lslider);
        Trans.setSliderRotate(Lslider1);
        Trans.setBouton1(x);
        Trans.setBouton2(y);



        Lslider.valueProperty().addListener((e,o,n)->{
            if(Trans.getSelectedShape() != null) {
                Shape shape= Trans.getSelectedShape();
                shape.setScaleX((Double) n);
                shape.setScaleY((Double) n);
                if ((Shape.intersect(shape, Trans.getBorder()).getBoundsInLocal().getHeight() != -1) && ((Shape.intersect(shape, Trans.getBorder()).getBoundsInLocal().getWidth() != -1))) {
                 //   System.out.println("entre dans la boucle");
                    shape.setScaleX((Double) o);
                    shape.setScaleY((Double) o);
                    try {
                        Lslider.setValue((Double) o);
                    } catch (Exception ex) {
                        shape.setScaleY((1));
                        shape.setScaleX((1));
                        Lslider.setValue(1);
                    }
                }

            }
        });


        Lslider1.valueProperty().addListener((e,o,n)->{

            Shape shape = Trans.getSelectedShape();
            if(shape != null) {
                shape.setRotate((Double) n);
              //  System.out.println(shape.getRotate());

                if (Shape.intersect(shape, Trans.getBorder()).getBoundsInLocal().getHeight() != -1 && Shape.intersect(shape, Trans.getBorder()).getBoundsInLocal().getWidth() != -1) {
                    shape.setRotate((Double) o );
                    Lslider1.setValue((Double) o);
                   // System.out.println("lalalalal");
                }
            }
        });


        pnstk.getChildren().add(Trans);
        Groupe.getChildren().add(SC);
        graph1.getChildren().add(pathAnimation);
        SC.setTranslateX(400);
        SC.setTranslateY(7);

      /* Trans.setOnMouseClicked(e->{
           circle.setTranslateX(e.getX());
           circle.setTranslateY(e.getY());
       });*/


        borderPane.setPrefHeight(720);//drwerSlide
        borderPane.setPrefWidth(1200);

        blinking_ball.setOpacity(1);

        timeline.setCycleCount( Animation.INDEFINITE ) ;
        if (Strtop !=null)
        {  Strtop.setGraphic(blinking_ball);}
        // The following EventHandler specifies what will be done
        // after the animation specified by the KeyFrame is finished.
        // The arrow token -> identifies a Lambda expression.

        EventHandler<ActionEvent> on_finished = ( ActionEvent event ) ->
        {
            if ( blinking_ball.getFill() == Color.RED )
            {
                blinking_ball.setFill( Color.TRANSPARENT ) ;
            }
            else
            {
                blinking_ball.setFill( Color.RED ) ;
            }

        } ;

        // Next we specify a KeyFrame whose execution takes 1 second.
        // The EventHandler will be executed after that.
        // No actual modification of values is specified by this KeyFrame.
        // This can thus be considered a misuse of a KeyFrame.

        KeyFrame keyframe = new KeyFrame( Duration.millis( 300 ), on_finished ) ;

        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add( keyframe ) ;

if (Cpro !=null) {
    Cpro.setSelected(true);
    Cpro.setToggleGroup(group);
    Cetu.setToggleGroup(group);
    group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        @Override
        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

          RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
            type=chk.getText();
           // System.out.println(type);

        }
    });


}
      if (personne!=null){

         String string = "";
          if (personne.getType().equals("ens")) {
              string ="Enseignant" ;

          }else{
              string = "Etudiant";

            //  System.out.println("laal");
          }
          userDirectoryString = new File("./Anim/"+string+"/"+personne.getUser()+"_doc");
          nbr=userDirectoryString.listFiles().length;

    }


    }

    public void maj() {
        slider.setValue(init);
        txt.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
        txt.setText(new Double(init).toString());
        txt.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());




    }

    public void maj1() {
        slider1.setValue(init);
        txt1.textProperty().bindBidirectional(slider1.valueProperty(), NumberFormat.getNumberInstance());
        txt1.setText(new Double(init).toString());
        txt1.textProperty().bindBidirectional(slider1.valueProperty(), NumberFormat.getNumberInstance());
        if(SC.getCurrentShape() != null){
            slider1.valueProperty().addListener((e,o,n)->{
                SC.getCurrentShape().setRotate((Double)n);
            });
        }


    }

    public void LSlide() {

        x.setTextFill(Color.WHITE);
        y.setTextFill(Color.WHITE);

        Label labelLeft = new Label("Left");
        Image imageOk = new Image(getClass().getResourceAsStream("arrows.png"));
         rot = new JFXButton("", new ImageView(imageOk));

        Image im = new Image(getClass().getResourceAsStream("scale.png"));
        sc = new JFXButton("",new ImageView(im));
Lslider.setShowTickLabels(true);
        Lslider1.setShowTickMarks(true);
        Lslider1.setShowTickLabels(true);
        Lslider.setShowTickMarks(true);
        BorderSlideBar leftFlapBar = new BorderSlideBar(250, ag, Pos.BASELINE_LEFT, labelLeft,x,y,sc,Lslider,rot,Lslider1);
        leftFlapBar.toBack();
        borderPane.setLeft(leftFlapBar);

    }

    public void RSlide() {
        Label labelRight = new Label("Right");
        JFXButton name=new JFXButton();
        JFXButton pname=new JFXButton();
        JFXButton nani = new JFXButton();
        JFXButton tp = new JFXButton();

        name.setTextAlignment(TextAlignment.JUSTIFY);
        pname.setTextAlignment(TextAlignment.LEFT);
        nani.setTextAlignment(TextAlignment.LEFT);

        name.setText("Nom : "+personne.getNom());
        pname.setText("Prénom : "+personne.getPrenom());

      if(userDirectoryString !=null)  nbr=userDirectoryString.listFiles().length;
        nani.setText("N° d'animation : "+Double.toString(nbr));
            System.out.print(nbr);
        name.setTextFill(Color.WHITE);
        pname.setTextFill(Color.WHITE);
        nani.setTextFill(Color.WHITE);
        tp.setTextFill(Color.WHITE);

        name.setPrefWidth(170);
        pname.setPrefWidth(170);
        nani.setPrefWidth(170);
        tp.setPrefWidth(170);

if (personne.getType().equals("ens"))
{
    tp.setText("Enseignant");
}else {
    tp.setText("Etudiant");
}
        BorderSlideBar rightFlapBar = new BorderSlideBar(190, UserId, Pos.BASELINE_RIGHT, labelRight,name,pname,tp,nani);
        rightFlapBar.toBack();
        borderPane.setRight(rightFlapBar);


    }

    private StringProperty createWarningColorStringProperty(final ObjectProperty<Color> warningColor) {
        final StringProperty colorStringProperty = new SimpleStringProperty();
        setColorStringFromColor(colorStringProperty, warningColor);
        warningColor.addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observableValue, Color oldColor, Color newColor) {
                setColorStringFromColor(colorStringProperty, warningColor);
            }
        });

        return colorStringProperty;
    }

    private Button createWarningButton(final ObjectProperty<Color> warningColor, StringProperty colorStringProperty) {
        final Button warningButton = new Button("Record");
        warningButton.styleProperty().bind(
                new SimpleStringProperty("-fx-base: ")
                        .concat(colorStringProperty)
                        .concat(";")
                        .concat("-fx-font-size: 15px;")
        );
        Timeline flash = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(warningColor, Color.WHITE, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.25), new KeyValue(warningColor, Color.WHITE, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(warningColor, Color.RED, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1.25), new KeyValue(warningColor, Color.RED, Interpolator.LINEAR)));
        flash.setCycleCount(Animation.INDEFINITE);
        flash.setAutoReverse(true);



        warningButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if (swi == false) {
                    flash.playFromStart();
                    swi = true;
                    warningButton.setText("Stop");
                } else {
                    flash.stop();
                    swi = false;
                    warningButton.setText("Record");
                    warningButton.setTextFill(Color.BLACK);
                    warningColor.setValue(Color.WHITE);
                }


            }
        });

        return warningButton;
    }

    private void setColorStringFromColor(StringProperty colorStringProperty, ObjectProperty<Color> color) {
        colorStringProperty.set(
                "rgba("
                        + ((int) (color.get().getRed() * 255)) + ","
                        + ((int) (color.get().getGreen() * 255)) + ","
                        + ((int) (color.get().getBlue() * 255)) + ","
                        + color.get().getOpacity() +
                        ")"
        );
    }


    @FXML
    public void handleButtonAction2(ActionEvent event)
            throws IOException {


        Parent root;
        Stage stage;
        if (event.getSource() == inserer) {
            /*stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("Fenetre.fxml"));
            //   stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(inserer.getScene().getWindow());
            stage.showAndWait();
            */
            Strtop.setDisable(false);
            shapeInfo = showDialogue();
           // System.out.println("event inserer");

            if (shapeInfo != null) {
                Trans.addShape(shapeInfo);
               /* Lslider.valueProperty().addListener((e,o,n)->{
                   // shapeInfo.getShape().setScaleX((Double)n);
                   // shapeInfo.getShape().setScaleY((Double)n);
                    //if(!shapeInfo.getShape())

                    if ((shapeInfo.getShape().getTranslateX() + shapeInfo.getShape().impl_getPivotX() - ((shapeInfo.getShape().getBoundsInLocal().getWidth() * (Double) n / 2)) >= 0)
                            && (shapeInfo.getShape().getTranslateY() + shapeInfo.getShape().impl_getPivotY() - shapeInfo.getShape().getBoundsInLocal().getHeight() * (Double) n / 2 >= 0
                    ) &&( shapeInfo.getShape().getTranslateY() + shapeInfo.getShape().impl_getPivotY() >= 0 )
                            && (shapeInfo.getShape().getTranslateX() <=( Trans.getRectangle().getWidth()*(Double)n - (shapeInfo.getShape().getLayoutBounds().getWidth() )))
                            && (shapeInfo.getShape().getTranslateY() <= (Trans.getRectangle().getHeight()*(Double)n - (shapeInfo.getShape()).getLayoutBounds().getHeight()))) {

                        System.out.println("entre dans la boucle");
                        shapeInfo.getShape().setScaleX((Double) n);
                        shapeInfo.getShape().setScaleY((Double) n);
                        Lslider.setValue((Double) n);
                    } else Lslider.setValue((Double) o);
                      //  Lslider.setValue((Double)o);

                });
              Lslider1.valueProperty().addListener((e,o,n)->{
                    shapeInfo.getShape().setRotate(Lslider1.getValue()*180);
                  if(shapeInfo.getShape().getLayoutBounds().intersects(Trans.getRectangle().getLayoutBounds()))
                      Lslider1.setValue((Double)n);
                });
              //  System.out.println("valeur null");*/
                isDone = true;

            }


        }


        //Rectangle rc = new Rectangle(vd,vd);
        //rc.setFill(cr);
        //rc.setOpacity(20);


        //double a=Groupe.getWidth()- vd ;
        //double x=- rc.getCenterX();
        //double b=Groupe.getHeight() - vd;
            /*sst.setCenterX((a) / 2);
            sst.setCenterY((b) / 2);
         */


        ;





           /* if (event.getSource()==export) {

                stage = (Stage) export.getScene().getWindow();

                stage.close();
                Trans.addShape(shapeInfo, 200, 200);
            }*/


        //tool.getItems().addAll(createWarningButton(warningColor, colorStringProperty));
    }


    @FXML
    public void Start() {

        if (bool==1) {
            try {
                Trans.startRecording();
                sli.setDisable(false);
                Sub.setDisable(false);
                Play.setDisable(true);
                getSave.setDisable(true);
                logoutButton.setDisable(true);
                inserer.setDisable(true);

            } catch (NoShapeException e) {
                e.printStackTrace();
            }

            bool=0;
            timeline.play();
            Strtop.setGraphic(blinking_ball);

        }
        else if (bool==0){

            Trans.StopRecording();
            timeline.stop();
            bool=1;
            Strtop.setGraphic(blinking_ball);
            sli.setDisable(true);
            Sub.setDisable(true);
            getSave.setDisable(false);
            inserer.setDisable(false);
            logoutButton.setDisable(false);
            Play.setDisable(false);

            blinking_ball.setFill(Color.RED);
        }


    }


    @FXML
    public ShapeInfo exporter() {
        return SC.export();

    }


    public ShapeInfo showDialogue() throws IOException {
        Parent root;
        shapeInfo=null;
        Stage stage = new Stage();
        //FXMLLoader loader = new FXMLLoader.load(getClass().getResource("Fenetre.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fenetre.fxml"));

        Parent stage2 = loader.load();

        Controller controller = (Controller) loader.getController();

        stage.setScene(new Scene(stage2));
        stage.setTitle("inserer une forme");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(inserer.getScene().getWindow());
        Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(applicationIcon);

        stage.setResizable(false);
        controller.export.setTooltip(new Tooltip("exporter la forme au graphe"));
        controller.slider.setTooltip(new Tooltip("rayon de la forme"));
        controller.slider1.setTooltip(new Tooltip("rotation de la forme en degré"));
        controller.rec.setTooltip(new Tooltip("ajouter un rectangle"));
        controller.circle.setTooltip(new Tooltip("ajouter un cercle"));
        controller.hexagone.setTooltip(new Tooltip("ajouter un hexagone"));
        controller.triangle.setTooltip(new Tooltip("ajouter un triangle"));
        stage.showAndWait();

        return shapeInfo;

    }


    @FXML
    public void rec(ActionEvent event) throws IOException {

        cr = crw.getValue();
        vd = slider.getValue();
        crw.valueProperty().addListener((e, o, n) -> {
            Shape shape = SC.getShape();
           // System.out.println(shape);
            if (shape != null)
                shape.setFill(n);
        });

        SC.addShape(new PolygonInformation(vd, 4), cr);

    }

    @FXML
    public void setExport(ActionEvent event) {
        Stage stage;
        shapeInfo = SC.export();
        //System.out.println(shapeInfo1.getShape());
        stage = (Stage) export.getScene().getWindow();
        stage.close();
    }


    /*public void UserInfo(){

        rightDrawerPane.getStyleClass().add("light-blue-900");
        rightDrawerPane.getChildren().add(new JFXButton("Right Content"));
        rightDrawer.setDirection(JFXDrawer.DrawerDirection.RIGHT);
        rightDrawer.setDefaultDrawerSize(250);
        rightDrawer.setSidePane(rightDrawerPane);
        rightDrawer.setOverLayVisible(true);
        rightDrawer.setResizableOnDrag(true);
        Span.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
        rightDrawer.setId("RIGHT");
        if (too==1)   {drawersStack.toggle(rightDrawer);too++;}
        else {
            if (too%2==0){zind();too++;}
            else {zindx();too++;}}

    }*/

   /* public void zindx(){
        drawersStack.toggle(rightDrawer);
        drawersStack.toFront();
    }*/

    /*public void zind(){
        drawersStack.toggle(rightDrawer);
        drawersStack.toBack();
    }*/

    public void affichClass() {
        ArrayList<String> name = new ArrayList<>();
    }

   /* public void FileChooser() throws Exception {
        Stage stage = new Stage();

        Pattern pattern =Pattern.compile(".+\\\\Anim\\\\Enseignant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern1 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern2 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\.+");



        if (personne !=null)
        {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ESI", "*.esi")
            );
            if (personne.getType().equals("ens"))
            {

                File userDirectoryString = new File("./Anim/Enseignant"+"/"+personne.getUser()+"_doc");

                fileChooser.setInitialDirectory(userDirectoryString);
                File url = fileChooser.showOpenDialog(stage);
                if(url != null) {
                    if (pattern.matcher(url.getAbsolutePath()).matches() || pattern2.matcher(url.getAbsolutePath()).matches()) {
                        System.out.println(url.getAbsoluteFile());

                        parent = AbstractRecord.loadAnimation(url.getAbsolutePath(), 850, 561);


                        if (parent instanceof PathAnimation) {
                            graph1.getChildren().remove(pathAnimation);
                            pathAnimation = (PathAnimation) parent;

                            graph1.getChildren().add(pathAnimation);

                            //  Slider sld1 = new Slider(0,10,1);
                            ((PathAnimation) parent).setSlider(sld);
             /*   getPlay.setOnAction(event -> {
                    ((PathAnimation) parent).play();
                });


                        }
                        if ((parent instanceof TransitionAnimation)) {

                            pnstk.getChildren().remove(Trans);
                            Trans = (TransitionAnimation) parent;

                            pnstk.getChildren().add(Trans);
                            // Slider sli1 = new Slider(0,10,1);
                            ((TransitionAnimation) parent).setSlider(sli);
                            ;

                            // Play.setOnAction(event -> {((TransitionAnimation)parent).play();} );
                        }
                    }
                }

                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}
            }
            else {
                File userDirectoryString = new File("./Anim/Etudiant"+"/"+personne.getUser()+"_doc");
                fileChooser.setInitialDirectory(userDirectoryString);

                File url = fileChooser.showOpenDialog(stage);
                if(url != null) {
                    if (pattern1.matcher(url.getAbsolutePath()).matches()) {
                        System.out.println(url.getAbsoluteFile());

                        parent = AbstractRecord.loadAnimation(url.getAbsolutePath(), 850, 561);


                        if (parent instanceof PathAnimation) {

                            pathAnimation = (PathAnimation) parent;
                            graph1.getChildren().add(pathAnimation);

                            //  Slider sld1 = new Slider(0,10,1);
                            ((PathAnimation) parent).setSlider(sld);
             /*   getPlay.setOnAction(event -> {
                    ((PathAnimation) parent).play();
                });


                        }
                        if ((parent instanceof TransitionAnimation)) {

                            pnstk.getChildren().remove(Trans);
                            Trans = (TransitionAnimation) parent;

                            pnstk.getChildren().add(Trans);
                            // Slider sli1 = new Slider(0,10,1);
                            ((TransitionAnimation) parent).setSlider(sli);
                            ;

                            // Play.setOnAction(event -> {((TransitionAnimation)parent).play();} );
                        }
                    }
                }
                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}

            }


        }
    }*/

    @FXML
    private Button logoutButton;


    @FXML
    public void Start2() {
        if (bool==1) {
            pathAnimation.startRecoding();
            getGetSave.setDisable(true);
            logoutButton.setDisable(true);
            inserer.setDisable(false);

            bool=0;
            timeline.play();
            Strtop.setGraphic(blinking_ball);

        }
        else if (bool==0){

            pathAnimation.stopRecording();
            timeline.stop();
            getPlay.setDisable(false);
            getGetSave.setDisable(false);
            logoutButton.setDisable(false);
            inserer.setDisable(true);
            bool=1;
            Strtop.setGraphic(blinking_ball);
            blinking_ball.setFill(Color.RED);
        }
    }

    /*
        @FXML
        public void fermer(ActionEvent event) {

            Stage stage;

            stage = (Stage) export.getScene().getWindow();

            stage.close();
            Trans.addShape(shapeInfo);

        }
    */


    @FXML
    public void Stoprecording() {
        Trans.StopRecording();
        timeline.stop();

    }

    @FXML
    public void Stoprecording2() {
        pathAnimation.stopRecording();
        timeline.stop();
    }

    @FXML
    public void playpathanimation() {

        pathAnimation.play();

        //timeline.playFromStart();
        timeline.stop();
    }

    @FXML
    public void insererdanschemin(ActionEvent event)
            throws IOException {
        shapeInfo = showDialogue2();
       // System.out.println("event inserer 00");

        if (shapeInfo != null) {
            pathAnimation.addShape(shapeInfo);
            sld.setDisable(false);
            Sub.setDisable(false);
            Strtop.setDisable(true);
            getPlay.setDisable(true);
            pathAnimation.startDrawing();
            inserer.setDisable(true);
            isAnswer=true;


        }

    }


    //Rectangle rc = new Rectangle(vd,vd);
    //rc.setFill(cr);
    //rc.setOpacity(20);


    //double a=Groupe.getWidth()- vd ;
    //double x=- rc.getCenterX();
    //double b=Groupe.getHeight() - vd;
            /*sst.setCenterX((a) / 2);
            sst.setCenterY((b) / 2);
         */
    ;





           /* if (event.getSource()==export) {

                stage = (Stage) export.getScene().getWindow();

                stage.close();
                Trans.addShape(shapeInfo, 200, 200);
            }*/


    //tool.getItems().addAll(createWarningButton(warningColor, colorStringProperty));

    public ShapeInfo showDialogue2() throws IOException {
        Parent root;Stage stage=new Stage();
        shapeInfo = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fenetrechemin.fxml"));

        Parent stage2 = loader.load();

        Controller controller = (Controller) loader.getController();

        stage.setScene(new Scene(stage2));
        stage.setTitle("inserer une forme");
        Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(applicationIcon);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(inserer.getScene().getWindow());
        controller.export.setTooltip(new Tooltip("exporter la forme au graphe"));
        controller.slider.setTooltip(new Tooltip("rayon de la forme"));
        controller.slider1.setTooltip(new Tooltip("rotation de la forme en degré"));
        controller.rec.setTooltip(new Tooltip("ajouter un rectangle"));
        controller.circle.setTooltip(new Tooltip("ajouter un cercle"));
        controller.hexagone.setTooltip(new Tooltip("ajouter un hexagone"));
        controller.triangle.setTooltip(new Tooltip("ajouter un triangle"));
        stage.showAndWait();

        return shapeInfo;
    }

    @FXML
    public void Save1() {

        Pattern pattern =Pattern.compile(".+\\\\Anim\\\\Enseignant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern1 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern2 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\.+");

        if (personne !=null)
        {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ESI", "*.esi")
            );
            if (personne.getType().equals("ens"))
            {

                  File userDirectoryString = new File("./Anim/Enseignant"+"/"+personne.getUser()+"_doc");

                fileChooser.setInitialDirectory(userDirectoryString);
                File file=fileChooser.showSaveDialog(null);
               if ( pattern.matcher(file.getAbsolutePath()).matches())
               {
                   Trans.save((file.getAbsolutePath()));
                   done=true;
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("sauvegarde");
                   alert.setHeaderText("sauvegarde réussite");
                   alert.setContentText("votre aniamtion a été bien enregistée");
                   alert.showAndWait();
                 //  System.out.println(file.getAbsoluteFile());
               }

                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}
            }
            else {
                File userDirectoryString = new File("./Anim/Etudiant"+"/"+personne.getUser()+"_doc");
                fileChooser.setInitialDirectory(userDirectoryString);

                File file=fileChooser.showSaveDialog(null);
                if ( pattern1.matcher(file.getAbsolutePath()).matches())
                {
                    Trans.save((file.getAbsolutePath()));
                    done=true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("sauvegarde");
                    alert.setHeaderText("sauvegarde réussite");
                    alert.setContentText("votre aniamtion a été bien enregistée");
                    alert.showAndWait();

                  //  System.out.println(file.getAbsoluteFile());

                }
                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}

            }


        }
        }

    @FXML
    public void Save2() {

        Pattern pattern =Pattern.compile(".+\\\\Anim\\\\Enseignant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern1 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\"+personne.getUser()+"_doc"+".+");
        Pattern pattern2 =Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\.+");

        if (personne !=null)
        {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ESI", "*.esi")
            );
            if (personne.getType().equals("ens"))
            {

                File userDirectoryString = new File("./Anim/Enseignant"+"/"+personne.getUser()+"_doc");

                fileChooser.setInitialDirectory(userDirectoryString);
                File file=fileChooser.showSaveDialog(null);
                if ( pattern.matcher(file.getAbsolutePath()).matches())
                {
                    pathAnimation.save((file.getAbsolutePath()));
                    done2=true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("sauvegarde");
                    alert.setHeaderText("sauvegarde réussite");
                    alert.setContentText("votre aniamtion a été bien enregistée");
                    alert.showAndWait();

                }

                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}
            }
            else {
                File userDirectoryString = new File("./Anim/Etudiant"+"/"+personne.getUser()+"_doc");
                fileChooser.setInitialDirectory(userDirectoryString);

                File file=fileChooser.showSaveDialog(null);
                if ( pattern1.matcher(file.getAbsolutePath()).matches())
                {
                    pathAnimation.save((file.getAbsolutePath()));
                    done2=true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("sauvegarde");
                    alert.setHeaderText("sauvegarde réussite");
                    alert.setContentText("votre aniamtion a été bien enregistée");
                    alert.showAndWait();

                }
                else {Alert alert=new Alert(Alert.AlertType.ERROR);alert.show();}

            }


        }
    }



    public void Signin ()
    {
        String typ="";
        if( type.equals("Professeur")){typ="ens";}
        else {typ="etud";}

        try {
            String string = "\\w+";
            if(!((name.getText().matches(string)) &&(pname.getText().matches(string))&&(sname.getText().matches(string))&&(psw.getText().matches(string))))
                throw new Exception();
           personne=Personne.signIn(name.getText(),pname.getText(),sname.getText(),psw.getText(),typ);
        } catch (CompteNonExistantException e) {
            e.show();
        } catch (CompteDejaExistantException e) {
            e.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "caractères invalides");
            alert.setTitle("caractères invalides");
            alert.show();
        }


    }



 /*public void conn() throws IOException {
     Stage stage= (Stage) ins.getScene().getWindow();
     Stage primary=new Stage();  String choice;    Parent stage2;
     try {

         personne=Personne.logIn(user.getText(),password.getText());
         stage.close();

         FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
            stage2=loader.load();
         Controller controller = (Controller)loader.getController();
            controller.UserId.setText(personne.getPrenom()+" "+personne.getNom());
            UserId.setTextAlignment(TextAlignment.RIGHT);

       stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
         Scene scc=new Scene(stage2);
         stage.setScene(scc);
         stage.show();
     } catch (CompteNonExistantException e) {
         e.show();
     }

 }*/
 @FXML
 public void conn() throws IOException {
     Stage stage= (Stage) ins.getScene().getWindow();
     Parent stage2; Stage primaryStage=new Stage();
     try {

         personne=Personne.logIn(user.getText(),password.getText());
         stage.close();

         Parent root = FXMLLoader.load(getClass().getResource("Choix.fxml"));
         primaryStage.setScene(new Scene(root));
         Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
         primaryStage.getIcons().add(applicationIcon);
         primaryStage.setTitle("choix");
         primaryStage.setWidth(600);
         primaryStage.setHeight(420);
         primaryStage.setResizable(false);
         primaryStage.showAndWait();

         String s1="Normal Mode";
         String s2="Chemin mode";

if (Chx !=null) {
    if (Chx.equals(s1)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        bool = 1;
        stage2 = loader.load();
        transition = true;
        Controller controller = (Controller) loader.getController();
        controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
        UserId.setTextAlignment(TextAlignment.RIGHT);




        stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());


        Scene scc = new Scene(stage2);
        stage.setScene(scc);
        Image applictionIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("mode transition");
        stage.setResizable(false);

        controller.Play.setTooltip(new Tooltip("jouer l'animation)"));
        // getPlay.setTooltip(new Tooltip("jouer l'animation "));
        controller.Sub.setTooltip(new Tooltip("valider le temps present"));
        controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
        controller.Lslider.setTooltip( new Tooltip("changement d'échele"));
        controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
        controller.getSave.setTooltip(new Tooltip("enregistrer l'animation"));
        controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
        controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
        controller.Sub.setDisable(true);
        controller.getSave.setDisable(true);
        controller.Play.setDisable(true);
        controller.Strtop.setDisable(true);


        stage.show();

        stage.setOnCloseRequest(e->{
            if(isDone){
                if(done){
                    answer = Display("Attention", "Voulez vous vraiment quitter ?");
                    done = false;
                    if(answer){
                        stage.close();
                    }else{
                        done= false;
                        e.consume();

                    }
                }else{
                    answer = Display("Attention ","Voulez vous vraiment quitter sans sauvegarder ?");
                    if(answer){
                        stage.close();
                    }else{
                        e.consume();
                    }
                }
            }
        });
    } else if (Chx.equals(s2)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chemin.fxml"));
        bool = 1;
        stage2 = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
        UserId.setTextAlignment(TextAlignment.RIGHT);

        stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
        controller.getPlay.setTooltip(new Tooltip("jouer l'animation "));
        controller.Sub.setTooltip(new Tooltip("valider le temps present"));
        controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
        controller.Lslider.setTooltip( new Tooltip("changement d'échele"));
        controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
        controller.getGetSave.setTooltip(new Tooltip("enregistrer l'animation"));
        controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
        controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
        controller.Sub.setDisable(true);
        controller.getGetSave.setDisable(true);
        controller.getPlay.setDisable(true);
        controller.inserer.setDisable(true);
        controller.sld.setDisable(true);


        Scene scc = new Scene(stage2);
        stage.setScene(scc);

        stage.getIcons().add(applicationIcon);
        stage.setTitle("mode chemin");
        stage.setResizable(false);

        chemin= true;
        stage.show();
        stage.setOnCloseRequest(e->{
            if(isAnswer){
                if(done2){
                    answer = Display("Attention", "Voulez vous vraiment quitter ?");

                    if(answer){
                        stage.close();
                        done2 = false;
                    }else{
                        e.consume();

                    }
                }else{
                    answer = Display("Attention","Voulez vous vraiment quitter sans sauvegarder ?");
                    if(answer){
                        stage.close();
                    }else{
                        e.consume();
                    }
                }
            }
        });

    }
}

     } catch (CompteNonExistantException e) {
         e.show();
     }

 }

public void ins(){
            Parent root = null;
            Stage stage = new Stage();
            try {
                root = FXMLLoader.load(getClass().getResource("signupPro.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
            stage.getIcons().add(applicationIcon);
            stage.setTitle("s'inscrire");
            stage.setResizable(false);
            stage.showAndWait();
        }





    @FXML
    public void hexa(ActionEvent event) throws IOException {

        cr = crw.getValue();
        vd = slider.getValue();
        crw.valueProperty().addListener((e, o, n) -> {
            Shape shape = SC.getShape();
            if (shape != null)
                shape.setFill(n);
        });

        SC.addShape(new PolygonInformation(vd,6),cr);
       // System.out.println("export apres ajout " + SC.export());

    }
    @FXML
    public void triangle(ActionEvent event) throws IOException {

        cr = crw.getValue();
        vd = slider.getValue();
        crw.valueProperty().addListener((e, o, n) -> {
            Shape shape = SC.getShape();
            if (shape != null)
                shape.setFill(n);
        });

        SC.addShape(new PolygonInformation(vd,3),cr);
      //  System.out.println("export apres ajout " + SC.export());

    }
    @FXML
    public void cercle(ActionEvent event) throws IOException {

        cr = crw.getValue();
        vd = slider.getValue();
        crw.valueProperty().addListener((e, o, n) -> {
            Shape shape = SC.getShape();
            if (shape != null)
                shape.setFill(n);
        });

        SC.addShape(new CircleInformation(vd),cr);
      //  System.out.println("export apres ajout " + SC.export());

    }
    @FXML
    public void Logout() throws IOException {
        Stage stage = (Stage)inserer.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));

        answer=Display("Attention","voulez vous vraiment deconnecter");
        if (answer) {
            stage.close();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
            stage1.getIcons().add(applicationIcon);
            stage1.setTitle("se connecter");
            stage1.setResizable(false);
            stage1.show();
        }

    }

public void pop()
{
    int Nb=0;
    AlertBox alrt=new AlertBox();
    try {
        Nb = alrt.display("Autre forme");
        if((Nb <= 2) || (Nb>50)) throw new Exception();
        crw.valueProperty().addListener((e, o, n) -> {
            Shape shape = SC.getShape();
            if (shape != null)
                shape.setFill(n);
        });
        SC.addShape(new PolygonInformation(slider.getValue(),Nb),crw.getValue());
    }catch(NumberFormatException e){
        Alert alert = new Alert(Alert.AlertType.ERROR, "entrez un entier");
        alert.show();
    }catch (Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR, "nombre incorrecte ou trop grand");
        alert.show();
    }
    System.out.print(Nb);
}

public void One()
 {
     Chx="Chemin mode";
     Stage stge= (Stage)im2.getScene().getWindow();
     stge.close();

 }

    public void One2(){
        Image ima = new Image(getClass().getResourceAsStream("C3.png"));
        im2.setImage(ima);
        im2.setOpacity(1);
        im2.setCursor(Cursor.HAND);
    }

    public void One3()
    {
        Image ima = new Image(getClass().getResourceAsStream("2.PNG"));
        im2.setImage(ima);
        im2.setOpacity(0.4);

    }
    public void two2(){
        Image ima1 = new Image(getClass().getResourceAsStream("N1.png"));
        im1.setImage(ima1);
        im1.setOpacity(1);
        im1.setCursor(Cursor.HAND);
    }

    public void two()
    {
        Chx="Normal Mode";

        Stage stge= (Stage)im1.getScene().getWindow();
        stge.close();


    }

    public void two3()
    {
        Image ima1 = new Image(getClass().getResourceAsStream("1.png"));
        im1.setImage(ima1);
        im1.setOpacity(0.4);

    }


    @FXML
    public void passerchemin() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chemin.fxml"));
        Parent stage2 = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
        UserId.setTextAlignment(TextAlignment.RIGHT);
        controller.getPlay.setTooltip(new Tooltip("jouer l'animation "));
        controller.Sub.setTooltip(new Tooltip("valider le temps present"));
        controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
        controller.Lslider.setTooltip( new Tooltip("changement d'échele"));
        controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
        controller.getGetSave.setTooltip(new Tooltip("enregistrer l'animation"));
        controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
        controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
        controller.Sub.setDisable(true);
        controller.inserer.setDisable(true);
        controller.getGetSave.setDisable(true);
        controller.getPlay.setDisable(true);


        stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
        Stage stage3=(Stage) Play.getScene().getWindow();
        Scene scc = new Scene(stage2);
        Stage stage = new Stage();
        stage.setScene(scc);
        Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("mode chemin");
        stage.setResizable(false);
        answer=Display("Attention","voulez vous vraiment passer en mode chemin");
        if (answer){
            stage3.close();
            stage.show();
            transition = false;
            chemin=true;
            isAnswer=false;
            done2=true;
            stage.setOnCloseRequest(event ->   {
                if (done2){
                    answer=Display("Attention","vouler vous vraiment quitter");
                    if (answer){
                        stage.close();


                    }
                    else event.consume();

                }
                else {
                    answer=Display("Attention","vouler vous vraiment quitter sans  sauvgarder");
                    if (answer){
                        stage.close();
                    }
                    else event.consume();
                }

            });

        }

    }

    public static boolean Display(String title,String message) {
       /* Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);


        Label label = new Label();
        label.setText(message);
        Button yesbutton = new Button("Yes");
        Button nobutton = new Button("NO");
        yesbutton.setOnAction(event ->
        {
            answer = true;
            window.close();
        });
        nobutton.setOnAction(event -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesbutton, nobutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene3 = new Scene(layout);
        window.setScene(scene3);
        window.showAndWait();*/
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        //alert.setHeaderText();
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            answer = true;
            alert.close();

        } else {
            // ... user chose CANCEL or closed the dialog
            answer = false;
            alert.close();
        }
        return answer;
    }

    @FXML
    public void passerTransition() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent stage2 = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
        UserId.setTextAlignment(TextAlignment.RIGHT);
        controller.Play.setTooltip(new Tooltip("jouer l'animation)"));
        // getPlay.setTooltip(new Tooltip("jouer l'animation "));
        controller.Sub.setTooltip(new Tooltip("valider le temps present"));
        controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
        controller.Lslider.setTooltip( new Tooltip("changement d'échele"));
        controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
        controller.getSave.setTooltip(new Tooltip("enregistrer l'animation"));
        controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
        controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
        controller.Sub.setDisable(true);
        controller.getSave.setDisable(true);
        controller.Play.setDisable(true);
        controller.Strtop.setDisable(true);
        stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
        Stage stage3=(Stage) getPlay.getScene().getWindow();
        Scene scc = new Scene(stage2);
        Stage stage = new Stage();
        Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
        stage.getIcons().add(applicationIcon);
        stage.setTitle("mode transition");
        stage.setResizable(false);
        stage.setScene(scc);
        answer=Display("Attention","voulez vous vraiment passer en mode Transition");
        if (answer){
            stage3.close();
            stage.show();
            chemin=false;
            transition=true;
            isDone=false;
            done=true;
            stage.setOnCloseRequest(event ->   {
                if (done){
                    answer=Display("Attention","vouler vous vraiment quitter");
                    if (answer){
                        stage.close();

                    }
                    else event.consume();

                }
                else {
                    answer=Display("Attention","vouler vous vraiment quitter sans  sauvgarder");
                    if (answer){
                        stage.close();
                    }
                    else event.consume();
                }

            });



        }

    }
    public void FileChooser() throws Exception {
        Stage stage = new Stage();

        Pattern pattern = Pattern.compile(".+\\\\Anim\\\\Enseignant\\\\" + personne.getUser() + "_doc" + ".+");
        Pattern pattern1 = Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\" + personne.getUser() + "_doc" + ".+");
        Pattern pattern2 = Pattern.compile(".+\\\\Anim\\\\Etudiant\\\\.+");


        if (personne != null) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("ESI", "*.esi")
            );
            if (personne.getType().equals("ens")) {

                File userDirectoryString = new File("./Anim/Enseignant" + "/" + personne.getUser() + "_doc");

                fileChooser.setInitialDirectory(userDirectoryString);
                File url = fileChooser.showOpenDialog(stage);

                if (pattern.matcher(url.getAbsolutePath()).matches() || pattern2.matcher(url.getAbsolutePath()).matches()) {
                   // System.out.println(url.getAbsoluteFile());

                    parent = AbstractRecord.loadAnimation(url.getAbsolutePath(), 850, 561);


                    if (parent instanceof PathAnimation) {
                        ((PathAnimation) parent).stopRecording();
                        if (transition) {
                            answer = Display("Passage", "attention cette action va provoquer le passage en mode chemin\n" +
                                    "voulez vous vraiment continuer");
                            if (answer) {

                              /*Controller controller=passerchemin();
                                controller.FileChooser();*/
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Chemin.fxml"));
                                Parent stage2 = loader.load();
                                Controller controller = (Controller) loader.getController();
                                controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
                                UserId.setTextAlignment(TextAlignment.RIGHT);

                                stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
                                controller.getPlay.setTooltip(new Tooltip("jouer l'animation "));
                                controller.Sub.setTooltip(new Tooltip("valider le temps present"));
                                controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
                                controller.Lslider.setTooltip(new Tooltip("changement d'échele"));
                                controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
                                controller.getGetSave.setTooltip(new Tooltip("enregistrer l'animation"));
                                controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
                                controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
                                controller.bool = 1;
                                controller.getPlay.setDisable(false);
                                controller.logoutButton.setDisable(false);
                                controller.pathAnimation = (PathAnimation) parent;
                                controller.graph1.getChildren().add(controller.pathAnimation);
                              //  System.out.println("childeen   " + graph1.getChildren());

                                //graph1.getChildren().add(pathAnimation);


                                //  Slider sld1 = new Slider(0,10,1)
                                ((PathAnimation) parent).setSlider(controller.sld);
                                Scene scc = new Scene(stage2);
                                stage.setScene(scc);
                                chemin = true;
                                transition = false;
                                Stage stage4 = (Stage) Play.getScene().getWindow();
                                stage4.close();
                                Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
                                stage.getIcons().add(applicationIcon);
                                stage.setTitle("mode chemin");
                                stage.setResizable(false);
                                stage.show();
                                //graph1.getChildren().add(pathAnimation);


                                stage.setOnCloseRequest(event -> {
                                    if (isAnswer) {
                                        if (done2) {
                                            answer = Display("Attention", "vouler vous vraiment quitter");
                                            if (answer) {
                                                stage.close();

                                            } else event.consume();

                                        } else {
                                            answer = Display("Attention", "vouler vous vraiment quitter sans  sauvgarder");
                                            if (answer) {
                                                stage.close();
                                            } else event.consume();
                                        }
                                    }
                                });
                            }
                        } else {
                            bool = 1;
                            getPlay.setDisable(false);
                            logoutButton.setDisable(false);


                            graph1.getChildren().remove(pathAnimation);
                            pathAnimation = (PathAnimation) parent;
                            graph1.getChildren().add(pathAnimation);

                            //  Slider sld1 = new Slider(0,10,1);
                            ((PathAnimation) parent).setSlider(sld);
                        }
             /*   getPlay.setOnAction(event -> {
                    ((PathAnimation) parent).play();
                });*/


                    }
                    if ((parent instanceof TransitionAnimation)) {
                        if (chemin) {
                            answer = Display("Passage", "attention cette action va provoquer le passage en mode Transition\n" +
                                    "voulez vous vraiment continuer");
                            if (answer) {
                             /*   Controller controller=passerTransition();
                                controller.FileChooser();*/
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
                                Parent stage2 = loader.load();
                                Controller controller = (Controller) loader.getController();
                                controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
                                UserId.setTextAlignment(TextAlignment.RIGHT);
                                controller.Play.setTooltip(new Tooltip("jouer l'animation)"));
                                // getPlay.setTooltip(new Tooltip("jouer l'animation "));
                                controller.Sub.setTooltip(new Tooltip("valider le temps present"));
                                controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
                                controller.Lslider.setTooltip(new Tooltip("changement d'échele"));
                                controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
                                controller.getSave.setTooltip(new Tooltip("enregistrer l'animation"));
                                controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
                                controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
                                controller.bool = 1;
                                controller.Play.setDisable(false);
                                controller.getSave.setDisable(false);
                                controller.logoutButton.setDisable(false);
                                controller.inserer.setDisable(false);
                                controller.sli.setDisable(true);
                                controller.Sub.setDisable(true);


                                stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());

                                controller.Trans = (TransitionAnimation) parent;
                                controller.pnstk.getChildren().add(controller.Trans);
                                //  pnstk.getChildren().add(Trans);
                            //    System.out.println(pnstk);

                              //  System.out.println(Trans);

                                //pnstk.getChildren().add(Trans);
                                // Slider sli1 = new Slider(0,10,1);
                                ((TransitionAnimation) parent).setSlider(controller.sli);
                                Scene scc = new Scene(stage2);
                                stage.setScene(scc);
                                transition = true;
                                chemin = false;
                                Stage stage4 = (Stage) getPlay.getScene().getWindow();
                                stage4.close();
                           //     System.out.println(pnstk.getChildren());
                                Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
                                stage.getIcons().add(applicationIcon);
                                stage.setTitle("mode transition");
                                stage.setResizable(false);

                                stage.show();

                                stage.setOnCloseRequest(event -> {
                                    if (isDone) {
                                        if (done) {
                                            answer = Display("Attention", "vouler vous vraiment quitter");
                                            done = false;
                                            if (answer) {
                                                stage.close();

                                            } else event.consume();
                                            done = false;

                                        } else {
                                            answer = Display("Attention", "vouler vous vraiment quitter sans  sauvgarder");
                                            if (answer) {
                                                stage.close();
                                            } else event.consume();
                                        }
                                    }
                                });
                            }
                        } else {
                            bool = 1;
                            Play.setDisable(false);
                            getSave.setDisable(false);
                            logoutButton.setDisable(false);
                            inserer.setDisable(false);
                            sli.setDisable(true);
                            Sub.setDisable(true);


                            pnstk.getChildren().remove(Trans);
                            Trans = (TransitionAnimation) parent;

                            pnstk.getChildren().add(Trans);
                            // Slider sli1 = new Slider(0,10,1);
                            ((TransitionAnimation) parent).setSlider(sli);
                        }
                        ;

                        // Play.setOnAction(event -> {((TransitionAnimation)parent).play();} );
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.show();
                }
            } else {
                File userDirectoryString = new File("./Anim/Etudiant" + "/" + personne.getUser() + "_doc");
                fileChooser.setInitialDirectory(userDirectoryString);
                try {
                    File url = fileChooser.showOpenDialog(stage);

                    if (pattern1.matcher(url.getAbsolutePath()).matches()) {
                      //  System.out.println(url.getAbsoluteFile());

                        parent = AbstractRecord.loadAnimation(url.getAbsolutePath(), 850, 561);


                        if (parent instanceof PathAnimation) {
                            if (transition) {
                                answer = Display("Passage", "attention cette action va provoquer le passage en mode chemin\n" +
                                        "voulez vous vraiment continuer");
                                if (answer) {
                               /*Controller controller=passerchemin();
                               controller. FileChooser();*/
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Chemin.fxml"));
                                    Parent stage2 = loader.load();
                                    Controller controller = (Controller) loader.getController();
                                    controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
                                    UserId.setTextAlignment(TextAlignment.RIGHT);
                                    controller.getPlay.setTooltip(new Tooltip("jouer l'animation "));
                                    controller.Sub.setTooltip(new Tooltip("valider le temps present"));
                                    controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
                                    controller.Lslider.setTooltip(new Tooltip("changement d'échele"));
                                    controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
                                    controller.getGetSave.setTooltip(new Tooltip("enregistrer l'animation"));
                                    controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
                                    controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
                                    controller.bool = 1;
                                    controller.getPlay.setDisable(false);
                                    controller.logoutButton.setDisable(false);

                                    stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
                                    controller.pathAnimation = (PathAnimation) parent;
                                    controller.graph1.getChildren().add(controller.pathAnimation);
                               //    System.out.println("childeen   " + graph1.getChildren());

                                    //graph1.getChildren().add(pathAnimation);


                                    //  Slider sld1 = new Slider(0,10,1)
                                    ((PathAnimation) parent).setSlider(controller.sld);
                                    Scene scc = new Scene(stage2);
                                    stage.setScene(scc);
                                    chemin = true;
                                    transition = false;
                                    Stage stage4 = (Stage) Play.getScene().getWindow();
                                    stage4.close();
                                    Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
                                    stage.getIcons().add(applicationIcon);
                                    stage.setTitle("mode chemin");
                                    stage.setResizable(false);
                                    stage.show();
                                    //graph1.getChildren().add(pathAnimation);


                                    stage.setOnCloseRequest(event -> {
                                        if (isAnswer) {
                                            if (done2) {
                                                answer = Display("Attention", "vouler vous vraiment quitter");
                                                if (answer) {
                                                    stage.close();

                                                } else event.consume();

                                            } else {
                                                answer = Display("Attention", "vouler vous vraiment quitter sans  sauvgarder");
                                                if (answer) {
                                                    stage.close();
                                                } else event.consume();
                                            }
                                        }
                                    });
                                }
                            } else {
                                bool = 1;
                                getPlay.setDisable(false);
                                logoutButton.setDisable(false);
                                graph1.getChildren().remove(pathAnimation);
                                pathAnimation = (PathAnimation) parent;
                                graph1.getChildren().add(pathAnimation);

                                //  Slider sld1 = new Slider(0,10,1);
                                ((PathAnimation) parent).setSlider(sld);
             /*   getPlay.setOnAction(event -> {
                    ((PathAnimation) parent).play();
                });*/
                            }


                        }
                        if ((parent instanceof TransitionAnimation)) {
                            if (chemin) {
                                answer = Display("Passage", "attention cette action va provoquer le passage en mode Transition\n" +
                                        "voulez vous vraiment continuer");
                                if (answer) {
                               /*Controller controller=passerTransition();
                               controller.FileChooser();*/
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
                                    Parent stage2 = loader.load();
                                    Controller controller = (Controller) loader.getController();
                                    controller.UserId.setText(personne.getPrenom() + " " + personne.getNom());
                                    UserId.setTextAlignment(TextAlignment.RIGHT);

                                    stage2.getStylesheets().add(getClass().getResource("Ui.css").toExternalForm());
                                    controller.Play.setTooltip(new Tooltip("jouer l'animation)"));
                                    // getPlay.setTooltip(new Tooltip("jouer l'animation "));
                                    controller.Sub.setTooltip(new Tooltip("valider le temps present"));
                                    controller.inserer.setTooltip(new Tooltip("ajouter une forme au graphe"));
                                    controller.Lslider.setTooltip(new Tooltip("changement d'échele"));
                                    controller.Lslider1.setTooltip(new Tooltip("rotation en degré "));
                                    controller.getSave.setTooltip(new Tooltip("enregistrer l'animation"));
                                    controller.Strtop.setTooltip(new Tooltip("commencer/terminer l'enregistrement"));
                                    controller.logoutButton.setTooltip(new Tooltip("Deconnecter"));
                                    controller.bool = 1;
                                    controller.Play.setDisable(false);
                                    controller.getSave.setDisable(false);
                                    controller.logoutButton.setDisable(false);
                                    controller.inserer.setDisable(false);
                                    controller.sli.setDisable(true);
                                    controller.Sub.setDisable(true);

                                    controller.Trans = (TransitionAnimation) parent;
                                    controller.pnstk.getChildren().add(controller.Trans);
                                    //  pnstk.getChildren().add(Trans);
                                //    System.out.println(pnstk);

                                  //  System.out.println(Trans);

                                    //pnstk.getChildren().add(Trans);
                                    // Slider sli1 = new Slider(0,10,1);
                                    ((TransitionAnimation) parent).setSlider(controller.sli);
                                    Scene scc = new Scene(stage2);
                                    stage.setScene(scc);
                                    Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
                                    stage.getIcons().add(applicationIcon);
                                    stage.setTitle("mode transition");
                                    transition = true;
                                    chemin = false;
                                    Stage stage4 = (Stage) getPlay.getScene().getWindow();
                                    stage4.close();
                                //    System.out.println(pnstk.getChildren());


                                    stage.show();

                                    stage.setOnCloseRequest(event -> {
                                        if (isDone) {
                                            if (done) {
                                                answer = Display("Attention", "vouler vous vraiment quitter");
                                                done = false;
                                                if (answer) {
                                                    stage.close();

                                                } else event.consume();
                                                done = false;

                                            } else {
                                                answer = Display("Attention", "vouler vous vraiment quitter sans  sauvgarder");
                                                if (answer) {
                                                    stage.close();
                                                } else event.consume();
                                            }
                                        }
                                    });

                                }
                            } else {

                                bool = 1;
                                Play.setDisable(false);
                                getSave.setDisable(false);
                                logoutButton.setDisable(false);
                                inserer.setDisable(false);
                                sli.setDisable(true);
                                Sub.setDisable(true);
                                pnstk.getChildren().remove(Trans);
                                Trans = (TransitionAnimation) parent;
                                pnstk.getChildren().add(Trans);
                                // Slider sli1 = new Slider(0,10,1);
                                ((TransitionAnimation) parent).setSlider(sli);
                                ;
                            }

                            // Play.setOnAction(event -> {((TransitionAnimation)parent).play();} );
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                    }

                }catch(Exception e){

                }


            }
        }
    }



        public void Apropo() {
            Stage stage=new Stage();
            Scene scene = new Scene(new Group());
            stage.setTitle("à propos");
            stage.setWidth(500);
            stage.setHeight(180);
            Image applicationIcon = new Image(getClass().getResourceAsStream("Icon.png"));
            stage.getIcons().add(applicationIcon);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            VBox vbox = new VBox();
            vbox.setLayoutX(20);
            vbox.setLayoutY(20);


            final String content = "Cette application est destinée au ministere de l'éducation nationale et \n" +
                    "a été conçue à des fins purement pédagogiques.\n" +
                    "Elle est ludique et simple à utiliser et ne nécéssite aucune formation particuliere. \n" +
                    "Elle aide les jeunes étudiants en 1ere année moyenne \n" +
                    "à assimiler les notions de base à propos des polygones et \n" +
                    "à se former à utiliser les nouvelles technologies. \n" +
                    "\n" +
                    "\n";
            final Text text = new Text(10, 20, "");

            final Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(8000));
                }

                protected void interpolate(double frac) {
                    final int length = content.length();
                    final int n = Math.round(length * (float) frac);
                    text.setText(content.substring(0, n));
                }

            };

            animation.play();



            vbox.getChildren().add(text);
            vbox.setSpacing(10);
            ((Group) scene.getRoot()).getChildren().add(vbox);

            stage.setScene(scene);
            stage.show();
        }

}


