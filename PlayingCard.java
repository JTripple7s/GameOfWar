
package GameOfWar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayingCard extends Card
{
    private int myValue;
    private Suit mySuit;
    
    PlayingCard(String cardImage)throws CardException{
        super(cardImage);
    }
    
    PlayingCard()throws CardException{
        this("file:img\\155.gif");
    }
    
    @Override
    protected void createCard(String image) throws CardException
    {
        try{
        imgPath = (image);
        imgCard = new Image(imgPath);
        this.setGraphic(new ImageView(imgCard));
        this.calCardValue();
        this.getCardSuit();
        }
        catch(Exception E){
            throw new CardException(E.getMessage() + "creatCard error using" + image);
        }
    }
    
     private void calCardValue()
    {
        String discard = ("");
        imgPath = imgPath.replace("file:img\\" , discard);
        imgPath = imgPath.replace(".gif", discard);
        int cardValue = Integer.parseInt(imgPath);
        int cardValuePostSub = cardValue - 100;
        if (cardValuePostSub % 13 == 0){
            myValue = 13;
        }
        else if (cardValuePostSub % 13 == 1){
            myValue = 14;
        }
        else myValue = cardValuePostSub % 13;
            
    }
    
    
    private void calcCardSuit()
    {
        if(myValue >= 2 && myValue <= 13){
            this.mySuit = Suit.Diamonds;
        }
        else if(myValue >= 14 && myValue <= 26){
            this.mySuit = Suit.Clubs;
        }
        else if(myValue >= 27 && myValue <= 39){
            this.mySuit = Suit.Hearts;
        }
        else if(myValue >= 40 && myValue <= 52){
            this.mySuit = Suit.Spades;
        }
    }    
    
    public void setCard(String image) throws CardException{
        this.createCard(image);
    }
    
    public int getCardValue()
    {
        return this.myValue;
    }
    
    public Suit getCardSuit()
    {
        return this.mySuit;
    }
        
        
    
}
