
package GameOfWar;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class GOWclient extends Application
{
    private final BorderPane root;
    private final GridPane topPane;
    private final GridPane cardPane;
    private final Label lblScore;
    private final TextField tfLeftScore;
    private final TextField tfRightScore;
    public Button btnReset;
    private int rightValue, leftValue;
    boolean rightsTurn = true;
    private static final byte MAX_DRAWS = 52;
    private byte cardsDrawn = 0;
    private final Label lblLeftOutput, lblRightOutput, lblRightCard, lblLeftCard, lblDeck;
    private Deck deck;

    public GOWclient(){
        
        this.root = new BorderPane();
        this.topPane = new GridPane();
        this.cardPane = new GridPane();
        this.lblScore = new Label("Score: ");
        this.tfLeftScore = new TextField("0");
        this.tfRightScore = new TextField("0");
        this.lblLeftOutput = new Label();
        this.lblRightOutput = new Label();
        this.btnReset = new Button("Reset");
        this.lblRightCard = new Label();
        this.lblLeftCard = new Label();
        this.lblDeck = new Label();
    }
    
    private static void alertPopUp(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("CardException Error");
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(message)));
        alert.show();
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        try{
        deck = new Deck();
        }
        catch(CardException ex){
                alertPopUp(ex.getMessage());
            }
        
        lblDeck.setOnMouseClicked(new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent event)
         {
             
             if(cardsDrawn >= MAX_DRAWS){
                 lblDeck.setDisable(true);
                 gameOver();
                 
                 return;
            }
             
            try{
                PlayingCard newCard = (PlayingCard)deck.dealCard();
                if(rightsTurn == true){
                    rightValue = newCard.getCardValue();
                    lblRightCard.setGraphic(newCard);
                    lblLeftCard.setGraphic(new TarotCard().getGraphic());
                    }
                else{
                    leftValue = newCard.getCardValue();
                    lblLeftCard.setGraphic(newCard);

                    if(rightValue > leftValue){
                    Score.addToRightScore(rightValue);
                    tfRightScore.setText(Integer.toString(Score.getRightScore()));
                }
                else if(rightValue < leftValue){
                    Score.addToLeftScore(leftValue);
                    tfLeftScore.setText(Integer.toString(Score.getLeftScore()));
                    }
                }
                rightsTurn = !rightsTurn;
                cardsDrawn++;
            }
            catch(CardException ex){
                alertPopUp(ex.getMessage());
            }
         }
      });
        
        btnReset.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) 
            {
                Score.resetScores();
                rightValue = Score.getRightScore();
                leftValue = Score.getLeftScore();
                tfRightScore.setText("0");
                tfLeftScore.setText("0");
                rightsTurn = true;
                lblDeck.setDisable(false);
                cardsDrawn = 0;
                resetCardImages();
            }
        });
        
        tfLeftScore.setPrefWidth(50);
        tfLeftScore.setDisable(true);
        tfRightScore.setPrefWidth(50);
        tfRightScore.setDisable(true);
        topPane.add(lblScore, 0, 0, 5, 1);
        lblScore.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        lblScore.setTextFill(Color.RED);
        Label lft = new Label("Left:");
        Label rgt = new Label("Right:");
        topPane.add(lft, 0, 1);
        topPane.add(rgt, 2, 1);
        topPane.add(tfLeftScore, 1, 1);
        topPane.add(tfRightScore, 3, 1);
        topPane.setHgap(20);
        topPane.setVgap(10);
        root.setTop(topPane);
        
        
        cardPane.setHgap(20);
        cardPane.add(lblLeftCard, 0, 0);
        cardPane.add(lblLeftOutput, 0, 1);
        cardPane.add(lblDeck, 1, 0);
        cardPane.add(lblRightCard, 2, 0);
        cardPane.add(lblRightOutput, 2, 1);
        cardPane.setAlignment(Pos.CENTER);
        this.resetCardImages();
        root.setCenter(cardPane);
        

        root.setBottom(btnReset);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Lab 4 Game of War");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void resetCardImages(){
        
        lblLeftCard.setGraphic(new ImageView("file:img\\maj_01.jpg"));
        lblRightCard.setGraphic(new ImageView("file:img\\maj_01.jpg"));
        lblDeck.setGraphic(new ImageView("file:img\\maj_01.jpg"));
        lblLeftOutput.setText("");
        lblRightOutput.setText("");
        lblScore.setText("Score:");
    }
    private void gameOver(){
        if(Score.getLeftScore() > Score.getRightScore()){
            lblLeftOutput.setText("Left Wins!");
            lblRightOutput.setText("Game Over!");
        }else if(Score.getRightScore() > Score.getLeftScore()){
            lblRightOutput.setText("Right Wins!");
            lblLeftOutput.setText("Game Over!");
        }else{
            lblRightOutput.setText("Tie!");
            lblLeftOutput.setText("Tie!");
        }
        lblScore.setText("Click Reset to play again");
    }
    
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
        
    
}