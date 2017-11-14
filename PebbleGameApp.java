
/**
 * Write a description of class PebbleGameApp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PebbleGameApp
{
    static class Player extends Thread{
        public ArrayList<Integer> myHand = new ArrayList<Integer>();
        MyBag lastBag;
        
        public void run(){
            //Only done one time.
            myHand = drawPebble();
            
            while(!done){
                if (is100() == true){
                    System.out.println("Yazan is a winner");
                    done = true;
                } else {
                    discard();
                    myHand.add(draw());
                    if (lastBag.checkEmpty()) {
                        if (lastBag == X) {
                            X.myPebbles = A.emptyBag();
                        } else if (lastBag == Y) {
                            Y.myPebbles = B.emptyBag();
                        } else {
                            Z.myPebbles = C.emptyBag();
                        }
                    }
                }
                
                
                
                
                
            }
        }
        
        public ArrayList<Integer> drawPebble(){
            MyBag [] bags = {X, Y, Z};
            Random random = new Random();
            int select = random.nextInt(bags.length);
            lastBag = bags[select];
            return bags[select].draw10();
            
        }
        
        public boolean is100(){
            int sum = 0;
            for(int i=0; i<10; i++){
                sum += myHand.get(i);
            }
            return sum == 100;
        }
        
        public void discard(){
            if (lastBag == X) {
                A.myPebbles.add(myHand.remove(0));//put in white bag
            } else if (lastBag == Y) {
                B.myPebbles.add(myHand.remove(0));
            } else {
                C.myPebbles.add(myHand.remove(0));
            }
        }
        
        public int draw() {
            MyBag [] bags = {X, Y, Z};
            Random random = new Random();
            int select = random.nextInt(bags.length);
            lastBag = bags[select];
            return bags[select].drawOne();
        }
    }
    
    static MyBag X;
    static MyBag Y;
    static MyBag Z;
    
    static boolean done = false;
    
    static MyBag A = new MyBag();
    static MyBag B = new MyBag();
    static MyBag C = new MyBag();
    
    public static void main (String args[]){
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the three files: ");
        X = new MyBag(reader.next());
        Y = new MyBag(reader.next());
        Z = new MyBag(reader.next());
        
        
        
        Player liam = new Player();
        Player yazan = new Player();
       
        liam.start();
        yazan.start();
        
        reader.close();   
    }
}
