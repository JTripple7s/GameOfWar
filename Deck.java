
package GameOfWar;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<PlayingCard> 
{
    
    private int myIndex = 0;
    private static final int LAST_CARD = 52;
    
    private void shuffleDeck(){
        Collections.shuffle(this);
    }
    
    private void loadCards(String filePath) throws CardException{
        int filePathNumber;
        String fullFilePath;
        
        for(int i = 1;i <= 52; i++){
            filePathNumber = i+100;
            fullFilePath = filePath + filePathNumber + ".gif";
            System.out.println(fullFilePath); 
            PlayingCard newCard = new PlayingCard(fullFilePath);
            this.add(newCard);
        }
        this.shuffleDeck();
    }
    
    public Card dealCard(){
        if(myIndex >= LAST_CARD){
            
            this.shuffleDeck();
            myIndex = 0;
        }
        return this.get(myIndex++);
    }
    
    public Deck(String filePath) throws CardException{
        this.loadCards(filePath);
    }
    
    public Deck() throws CardException{
        this("file:img\\");
    }
    
}
