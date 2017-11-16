/**
 * Write a description of class MyBag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyBag
{
    // instance variables - replace the example below with your own
    ArrayList<Integer> myPebbles = new ArrayList<Integer>();
    
    MyBag corrospondingBag;

    public MyBag(){}
    
    public MyBag(String bagFile)
    {
        readFrom(bagFile);
    }
    
    public void readFrom(String t) {
        try{
        Scanner scanner = new Scanner(new File(t));
        
        while(scanner.hasNextInt())
        {
           myPebbles.add(scanner.nextInt());
           
        }
        
        } catch (IOException e) {}
    }
    
    
    public void setCoBag(MyBag B){
        corrospondingBag = B;
    }
    
    public ArrayList<Integer> draw10(){
        ArrayList<Integer> pebbles10 = new ArrayList<Integer>();
        
        for(int i=0; i<10; i++){
            Random random = new Random();
            int select = random.nextInt(myPebbles.size()-1);
            pebbles10.add(myPebbles.remove(select));
        }
        System.out.println("you aight fam");
        return pebbles10;
    }
    
    public int drawOne(){
        Random random = new Random();
        if (myPebbles.size() == 0){
            myPebbles = corrospondingBag.emptyBag();
        }
        int select = random.nextInt(myPebbles.size());
        return myPebbles.remove(select);
    }
    
    public ArrayList<Integer> emptyBag() {
        ArrayList<Integer> copy = new ArrayList<Integer>(myPebbles);
        myPebbles.clear();
        return copy;
    }
    
    public boolean checkEmpty() {
        if (myPebbles.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void fillBag(ArrayList<Integer> whiteBag) {
        myPebbles = new ArrayList<Integer>(whiteBag);
    }
    
    public static void main (String args[]){
        MyBag bagA = new MyBag("pebbles.txt");
        MyBag bagX = new MyBag();
        bagA.draw10().size();
    }
}
