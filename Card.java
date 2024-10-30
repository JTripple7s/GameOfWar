
package GameOfWar;

import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.control.*;

abstract public class Card extends Label
{
    protected Image imgCard;
    protected String imgPath;
    
    public Card(String filePath)throws CardException{
        this.setImage(filePath);
    }
    
    public Card()throws CardException{
        this("file:img\\155.gif");
    }
  
    protected abstract void createCard(String image)throws CardException;
    
    private void setImage(String cardPath)throws CardException{
        this.createCard(cardPath);
    }
    
    public Image getCardImage()
    {
        return imgCard;
    }
      
}
    
            
        


