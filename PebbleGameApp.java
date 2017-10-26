
/**
 * Write a description of class PebbleGameApp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PebbleGameApp extends Thread
{
    class Player{
        public int[] myHand = new int[10];
        
        public boolean is100(){
            int sum = 0;
            for(int i=0; i<10; i++){
                sum += myHand[i];
            }
            return sum == 100;
        }
    }
    public PebbleGameApp(){}
    
    public void run(){
        
    }
    
    public static void main (String args[]){
        
    }
}
