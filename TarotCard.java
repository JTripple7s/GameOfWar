
package GameOfWar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TarotCard extends Card
{
    int myValue;
    private String myName;
    
    TarotCard(String cardImage)throws CardException{
        super(cardImage);
    }
    
    TarotCard()throws CardException{
        this("file:img\\maj_00.jpg");
    }
    
    @Override
    protected void createCard(String image) throws CardException
    {
        try{
        imgPath = (image);
        imgCard = new Image(imgPath);
        this.setGraphic(new ImageView(imgCard));
        this.calcCardValue();
        this.calcCardName();
        }
        catch(Exception E){
            throw new CardException(E.getMessage() + "creatCard error using" + image);
        }
    }
    
    private void calcCardValue(){
        
        String discard = ("");
        imgPath = imgPath.replace("file:img\\maj_" , discard);
        imgPath = imgPath.replace(".jpg", discard);
        myValue = Integer.parseInt(imgPath);
    }
    
    private void calcCardName(){
        myName = ("Major Arcana" + myValue);
    }
    
    public int getCardValue(){
        return myValue;
    }
    
    public String getCardName(){
        return myName;
    }
}
