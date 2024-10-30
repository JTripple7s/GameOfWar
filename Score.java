
package GameOfWar;

import java.lang.Throwable;
public class Score 
{
    private static int myLeftScore = 0;
    private static int myRightScore = 0;
    
    public static void addToRightScore(int Scoreing)
    {
        myRightScore += Scoreing;
    }
    
    public static void addToRightScore(String scoring) throws CardException
    {
        
        try{
            addToRightScore(Integer.parseInt(scoring));
        }
        catch(Exception E){
            throw new CardException("addToRightScore error " + scoring + E.getMessage());
        }
    }
    public static void addToLeftScore(int Scoreing)
    {
        myLeftScore += Scoreing;
        
    }
    public static void addToLeftScore(String scoring) throws CardException
    {
        try{
            addToLeftScore(Integer.parseInt(scoring));
        }
        catch(Exception E){
            throw new CardException("addToLeftScore error " + scoring + E.getMessage());
        }
        
    }
    public static int getRightScore()
    {
        return myRightScore;
    }
    public static int getLeftScore()
    {
        return myLeftScore;
    }
    
    public static void resetScores()
    {
        myRightScore = 0;
        myLeftScore = 0;
        
    }
    
}
