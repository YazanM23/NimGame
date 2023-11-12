package com.example.aihome;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    @FXML
    private ScrollPane scroll;

    @FXML
    ImageView imageView=new ImageView();
    @FXML

    private Parent root;
    @FXML
    private Label numberSticks;
    @FXML
    private Label levelLabel;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Button currentGame;

    @FXML
    private AnchorPane anc;

    @FXML
    private Button home;
    @FXML
    private Button playAgain;
    @FXML
    private Button levels;
    @FXML
    private VBox vbox;


    @FXML
    private ImageView currentGameArrow;
    @FXML
    private ImageView levelsArrow;
    @FXML
    private ImageView homeArrow;
    @FXML
    private StackPane stackP;
    @FXML
    private ImageView playAgainArrow;

    @FXML
    private TextField group1;

    @FXML
    private TextField group2;

    @FXML
    private AnchorPane groupsAnchor;


    List<HBox> hBoxList = new ArrayList<>();
    List<Boxes> globalHboxList = new ArrayList<>();
    List<Boxes> boxes = new ArrayList<>();
    int sticks;
    int id=1;
    int currentID=-1;
    int numberOfHandling=1;
    String level;
    String whoStarts;
    String playerName;
    @FXML
    private Label currentSelected;
    @FXML
    private Label   whoStartLabel;
//    private Label sticksLabel=new Label();

    public void number(int numberStick){


this.sticks=numberStick;


    }
    public void setLevel(String getLevel){
        level=getLevel;
    }
    public void whoStart(String start){
        whoStarts=start;
    }
    public  void getPlayerName(String name){
        playerName=name;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setSticks();
        numberSticks.setText("Number of sticks: "+sticks);
        levelLabel.setText("Level:"+level);
        whoStartLabel.setText("Who Start : "+whoStarts);
        playerNameLabel.setText("Player Name : "+playerName);

        currentGameArrow.setVisible(true);
        currentGame.setStyle("-fx-background-color: white;");
    }
boolean divided=false;
    @FXML
    protected void dividedSticks(ActionEvent e){
        int number=0;
        int number2=0;
        boolean flag;

        try {
             number=Integer.parseInt(group1.getText());
             number2=Integer.parseInt(group2.getText());
             flag=true;

        }
        catch (Exception ex){
flag=false;
        }


if(number>currentSelectedValue || !flag || number2>currentSelectedValue || number+number2>currentSelectedValue||number==number2|| number2+number!=currentSelectedValue||number==0||number2==0){
    group1.setStyle("-fx-border-color: red ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
    group2.setStyle("-fx-border-color: red ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");


}
else {
    groupsAnchor.setVisible(false);
    group1.setStyle("-fx-border-color: white ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
    group2.setStyle("-fx-border-color: white ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
    for(int i=0;i<boxes.size();i++){
        if(boxes.get(i).hbox.getId().equals(Integer.toString(currentID)) && !boxes.get(i).isPrinted){
            createSticksGroup1(number,number2);
            divided=true;
        }
    }

}



//        for(int j=0;j<number;j++){
//            Image image = new Image("stick.png");
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(50);
//            imageView.setFitHeight(100);
//
//            boxes.get(currentID).hbox.getChildren().add(imageView);
//            System.out.println("iam in divide");
//
//        } boxes.get(currentID).hbox.setStyle("-fx-background-color: #ececec;");
//
//
//System.out.println("first id: "+currentID);
//createSticksGroup2(number2);
//        for(int j=0;j<number2;j++){
//            Image image = new Image("stick.png");
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(50);
//            imageView.setFitHeight(100);
//
//            boxes.get(currentID).hbox.getChildren().add(imageView);
//            System.out.println("iam in divide2");
//
//        } boxes.get(currentID).hbox.setStyle("-fx-background-color: #ececec;");
//
//        System.out.println("Second id: "+currentID);
    }
    HBox globalHbox=new HBox();
    @FXML
    protected void GoBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Levels.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
boolean flagVbox=false;
    boolean setSbefore=false;


    public void setSticks(){
        if(!setSbefore) {
             HBox hbox=new HBox();
            setSbefore=true;
            currentSelectedValue = sticks;

            hbox.setPadding(new Insets(20));
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            HBox newHbox = new HBox();

            newHbox.setId("0");
            id++;

            for (int i = 0; i < sticks; i++) {

                Image image = new Image("stick.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(100);
                newHbox.getChildren().add(imageView);
            }
            HBox newBox = new HBox();
            Label label = new Label("Start");
            newBox.setAlignment(Pos.CENTER);
            newBox.getChildren().add(label);

            hbox.getChildren().add(newHbox);
            hbox.getChildren().add(newBox);
            newHbox.setStyle("-fx-background-color: #ececec;");
            hBoxList.add(newHbox);
            Boxes box = new Boxes();
            box.id = 0;
            box.value = sticks;
            box.hbox = newHbox;
            newHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    groupsAnchor.setVisible(true);
                    currentID = 0;
                 //   currentSelected.setText("Current Value : " + currentSelectedValue);

                    for(int i=0;i<boxes.size();i++){

                        if(boxes.get(i).hbox.getId().equals( newHbox.getId())){

                            if(!boxes.get(i).isPrinted) {

                                currentSelectedValue = boxes.get(i).value;
                                currentGlobalId=boxes.get(i).hisGlobalhBoxId;

                                groupsAnchor.setVisible(true);
                                currentSelected.setText("Current Value : " + currentSelectedValue);
                            }

                            else{
                                currentSelectedValue=-1;
                                currentSelected.setText("its already divided");
                                groupsAnchor.setVisible(false);
                                divided=true;
                            }

                        }

                    }


                }

            });
            boxes.add(box);
             vbox.getChildren().add(hbox);
        }

    }
    int globalID=0;
int currentSelectedValue=-1;
int currentGlobalId=-1;
int counter=0;
int numberOfBoxes=0;
boolean setCounter=true;
    public void createSticksGroup1(int number,int number2){



        for(int i=0;i<boxes.size();i++){
           if(boxes.get(i).hbox.getId().equals(Integer.toString(currentID))){
               boxes.get(i).isDivided=true;
           }
           if(boxes.get(i).hisGlobalhBoxId==currentGlobalId){
               boxes.get(i).isPrinted=true;
           }

        }




        Boxes box=new Boxes();

        globalHbox=new HBox();
        flagVbox=true;


        counter=0;
        numberOfBoxes=0;
        HBox newHbox=new HBox();
        globalHbox.setSpacing(20);
        globalHbox.setPadding(new Insets(10));
        globalHbox.setAlignment(Pos.CENTER);
        globalHbox.setId(Integer.toString(globalID));
        globalHboxList.add(box);

        newHbox.setId(Integer.toString(id));


        for(int j=0;j<number;j++){
            Image image = new Image("stick.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(100);
            newHbox.getChildren().add(imageView);

        }


        globalHbox.getChildren().add(newHbox);
        newHbox.setStyle("-fx-background-color: #ececec;");



        box.hbox=newHbox;
        box.id=id;
        box.value=number;
        box.hisGlobalhBoxId=globalID;
        hBoxList.add(newHbox);
        boxes.add(box);
        id=id+1;


        final boolean[] flag = {false};
        newHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                currentID=Integer.parseInt(newHbox.getId());



                for(int i=0;i<boxes.size();i++){

                    if(boxes.get(i).hbox.getId().equals( newHbox.getId())){

                        if(!boxes.get(i).isPrinted) {

                            currentSelectedValue = boxes.get(i).value;
                            currentGlobalId=boxes.get(i).hisGlobalhBoxId;

                            groupsAnchor.setVisible(true);
                            currentSelected.setText("Current Value : " + currentSelectedValue);
                        }

                        else{
                            currentSelectedValue=-1;
                            currentSelected.setText("its already divided");
                            groupsAnchor.setVisible(false);
                            divided=true;
                        }

                    }

                }


            }
        });










        box=new Boxes();
        HBox newHbox1=new HBox();
        newHbox1.setId(Integer.toString(id));

        for(int j=0;j<number2;j++){
            Image image = new Image("stick.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(100);
            newHbox1.getChildren().add(imageView);

        }
        globalHbox.getChildren().add(newHbox1);
        newHbox1.setStyle("-fx-background-color: #ececec;");

        box.hbox=newHbox1;
        box.id=id;
        box.hisGlobalhBoxId=globalID;
        box.value=number2;
        hBoxList.add(newHbox1);
        boxes.add(box);
        globalHboxList.add(box);
        id++;


        newHbox1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                currentID=Integer.parseInt(newHbox1.getId());


                for(int i=0;i<boxes.size();i++){
                    if(boxes.get(i).hbox.getId().equals(newHbox1.getId()) ){
                        if( !boxes.get(i).isPrinted) {
                            currentSelectedValue = boxes.get(i).value;
                            currentGlobalId=boxes.get(i).hisGlobalhBoxId;
                            groupsAnchor.setVisible(true);
                            currentSelected.setText("Current Value : "+currentSelectedValue);
                        }
                        else{
                            currentSelectedValue=-1;
                            currentSelected.setText("its already divided");
                            groupsAnchor.setVisible(false);
                            divided=true;
                        }
                    }
                }


            }
        });



        for(int i=0;i<boxes.size();i++){
            if(boxes.get(i).hisGlobalhBoxId==globalID-1){
                if(!boxes.get(i).isDivided){



                    box=new Boxes();
                    HBox newHbox2=new HBox();
                    newHbox2.setId(Integer.toString(id));

                    for(int j=0;j<boxes.get(i).value;j++){
                        Image image = new Image("stick.png");
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(100);
                        newHbox2.getChildren().add(imageView);

                    }
                    globalHbox.getChildren().add(newHbox2);
                    newHbox2.setStyle("-fx-background-color: #ececec;");

                    box.hbox=newHbox2;
                    box.id=id;
                    box.hisGlobalhBoxId=globalID;
                    box.value=boxes.get(i).value;
                    hBoxList.add(newHbox2);
                    boxes.add(box);
                    globalHboxList.add(box);

                    id++;
                    newHbox2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            currentID=Integer.parseInt(newHbox2.getId());


                            for(int i=0;i<boxes.size();i++){
                                if(boxes.get(i).hbox.getId().equals(newHbox2.getId()) ){
                                    if( !boxes.get(i).isPrinted) {
                                        currentSelectedValue = boxes.get(i).value;
                                        currentGlobalId=boxes.get(i).hisGlobalhBoxId;
                                        groupsAnchor.setVisible(true);
                                        currentSelected.setText("Current Value : "+currentSelectedValue);
                                    }
                                    else{
                                        currentSelectedValue=-1;
                                        currentSelected.setText("its already divided");
                                        groupsAnchor.setVisible(false);
                                        divided=true;
                                    }
                                }
                            }


                        }
                    });






                }
            }
        }
        if(numberOfHandling==1){
            Label label=new Label("You");
            globalHbox.getChildren().add(label);
            numberOfHandling=2;

        }
        else{
            numberOfHandling=1;
            Label label=new Label("Me");
            globalHbox.getChildren().add(label);

        }

        globalID++;



if(flagVbox) {
    vbox.getChildren().add(globalHbox);
}
    }



//    public void createSticksGroup2(int number){
//        Boxes box=new Boxes();
//        HBox newHbox=new HBox();
//        newHbox.setId(Integer.toString(id));
//        id++;
//
//        for(int j=0;j<number;j++){
//            Image image = new Image("stick.png");
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(50);
//            imageView.setFitHeight(100);
//            newHbox.getChildren().add(imageView);
//            System.out.println("iam in divide");
//        }
//        newHbox.setStyle("-fx-background-color: #ececec;");
//
//        newHbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                groupsAnchor.setVisible(true);
//                currentID=Integer.parseInt(newHbox.getId());
//                groupsAnchor.setVisible(true);
//            }
//        });
//
//        box.hbox=newHbox;
//        box.id=id;
//        box.value=number;
//        hBoxList.add(newHbox);
//        boxes.add(box);

//        // Add newHbox to vbox only once
//
//    }


    @FXML
    protected void CurrentButton(){
        setSticks();
        currentGameArrow.setVisible(true);
        homeArrow.setVisible(false);
        levelsArrow.setVisible(false);
        playAgainArrow.setVisible(false);
       currentGame.setStyle("-fx-background-color: white;");
       home.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
        levels.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
       playAgain.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");



    }
    @FXML
    protected void HomeButton(ActionEvent e) throws IOException {
        currentGameArrow.setVisible(false);
        homeArrow.setVisible(true);
        levelsArrow.setVisible(false);
        playAgainArrow.setVisible(false);
      currentGame.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
      home.setStyle("-fx-background-color: white;");
       levels.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
      playAgain.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");



      root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();




    }

    @FXML
    protected void levelsButton(ActionEvent e) throws IOException {
        currentGameArrow.setVisible(false);
        homeArrow.setVisible(false);
        levelsArrow.setVisible(true);
        playAgainArrow.setVisible(false);
        currentGame.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
      home.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
       levels.setStyle("-fx-background-color: white;");
        playAgain.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
       GoBack(e);
    }
    @FXML
    protected void playAgainButton(){
        currentGameArrow.setVisible(false);
        homeArrow.setVisible(false);
        levelsArrow.setVisible(false);
        playAgainArrow.setVisible(true);
        boxes=new ArrayList<>();
        globalHboxList=new ArrayList<>();
        vbox.getChildren().clear();

        setSbefore=false;
        setSticks();



        PauseTransition delay = new PauseTransition(Duration.seconds(0.7));
        delay.setOnFinished(event -> {

            currentGame.setStyle("-fx-background-color: white;");
            home.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
            levels.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
            playAgain.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");

            playAgainArrow.setVisible(false);
            currentGameArrow.setVisible(true);
        });
delay.play();

playAgainArrow.setVisible(true);
currentGameArrow.setVisible(false);
        currentGame.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
        home.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
       levels.setStyle("/Users/yazanmansour/Downloads/AiHome/src/main/resources/CssFiles/Buttons.css");
        playAgain.setStyle("-fx-background-color: white;");












    }





}
