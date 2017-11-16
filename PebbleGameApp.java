
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
        
        public synchronized void run(){
            //Only done one time.
            myHand = drawPebble();
            
            while(!done){
                if (is100() == true){
                    System.out.println("winner");
                    done = true;
                } else {
                   discard();
                   myHand.add(draw());
                   int sum = 0;
                   for(int i=0; i<10; i++){
                       sum += myHand.get(i);
                    }
                   System.out.println(sum);
                }
                Thread.yield();
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
        
        System.out.println("Welcome to the PebbleGame! \n You will be asked to enter the number of players. \n and then for the location of three files in turn containing comma seperated integer values for the pebble weights. \n The integer values must be strictly positive. \n The game will then be simulated, and output written to files in this directory. \n");
        
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Enter the number of players: ");
        int number = Integer.parseInt(reader.next());
        Player[] players = new Player[number];
        
        for(int i=0 ; i<number ;i++){
            players[i] = new Player();
        }
        
        System.out.println("Enter File 1: ");
        X = new MyBag(reader.next());
        
        System.out.println("Enter File 2: ");
        Y = new MyBag(reader.next());
        
        System.out.println("Enter File 3: ");
        Z = new MyBag(reader.next());
        
        X.setCoBag(A);
        Y.setCoBag(B);
        Z.setCoBag(C);
        
        A.setCoBag(X);
        B.setCoBag(Y);
        C.setCoBag(Z);
        
        int check = 11*number;
        
        X.checkMin(check);
        Y.checkMin(check);
        Z.checkMin(check);
        
        for(int i=0 ; i<number ;i++){
            players[i].start();
        }
        
        reader.close();   
    }
}
