
package GameOfWar;


public class CardException extends Exception
{
    CardException(){
        super("Unknown Error");
    }
    
    CardException(String message){
        super(message);
    }
    
}
