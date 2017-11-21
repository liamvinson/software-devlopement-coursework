package pebblegame;

/**
 *
 * @author liamvinson
 */

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;

public class PebbleGameApp {
    static class Player extends Thread{
        public ArrayList<Integer> myHand = new ArrayList<Integer>();
        MyBag lastBag;
        String playerName;
        public Player(String playerName){
            this.playerName = playerName;
        }

        @Override
        public synchronized void run(){
            //Only done one time.
            myHand = drawPebble();
            try{
                PrintWriter writer = new PrintWriter(playerName + ".txt", "UTF-8");

                while(!done){
                    if (is100() == true){
                        System.out.println(playerName + " Wins");
                        done = true;
                    } else {
                        int peb = discard();
                        int pebD = draw();

                        writer.println(playerName + " has discarded a " + peb + " to " + lastBag.corrospondingBag.bagName); 
                        writer.println(playerName + " hand is " + myHand);

                        myHand.add(pebD);
                        writer.println(playerName + " has drawn a " + pebD + " from " + lastBag.bagName); 
                        writer.println(playerName + " hand is " + myHand);
                    }
                    Thread.yield();
                }
                writer.close();
            } catch(IOException e){}
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

        public int discard(){
            int peb = myHand.remove(0);
            lastBag.corrospondingBag.myPebbles.add(peb);
            return peb;
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

    static MyBag A = new MyBag("A");
    static MyBag B = new MyBag("B");
    static MyBag C = new MyBag("C");
    
    public static void doWork(Scanner input) {
        System.out.println("Welcome to the PebbleGame! \n You will be asked to enter the number of players. \n and then for the location of three files in turn containing comma seperated integer values for the pebble weights. \n The integer values must be strictly positive. \n The game will then be simulated, and output written to files in this directory. \n");

        int number=0;
        boolean playerCheck=false;

        while (!playerCheck){
            System.out.println("Enter the number of players: ");
            number = Integer.parseInt(input.next());
            if (number >=1){
                playerCheck = true;
            } else {
                System.out.println("Invalid player entry!");
            }
        }
        Player[] players = new Player[number];

        for(int i=0 ; i<number ;i++){
            players[i] = new Player("player" + (i+1));
        }

        boolean fileCheck=false;
        int check = 11*number;

        while (!fileCheck){
            try{
                System.out.println("Enter File 1: ");
                X = new MyBag(input.next(), "X");
                X.checkMin(check);

                System.out.println("Enter File 2: ");
                Y = new MyBag(input.next(), "Y");
                Y.checkMin(check);

                System.out.println("Enter File 3: ");
                Z = new MyBag(input.next(), "Z");
                Z.checkMin(check);

                fileCheck = true;
            }catch (IOException e){
                System.out.println(e);
            }
        }
        X.setCoBag(A);
        Y.setCoBag(B);
        Z.setCoBag(C);

        A.setCoBag(X);
        B.setCoBag(Y);
        C.setCoBag(Z);

        for(int i=0 ; i<number ;i++){
            players[i].start();
        }

        input.close();
    }

    public static void main (String args[]){
        doWork(new Scanner(System.in));
    }
}
