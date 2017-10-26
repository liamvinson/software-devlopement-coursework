/**
 * Write a description of class MyBag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyBag
{
    // instance variables - replace the example below with your own
    private boolean isEmpyty;
    ArrayList<Integer> myPebbles = new ArrayList<Integer>();
    
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
    
    public ArrayList<Integer> emptyBag() {
        ArrayList<Integer> copy = new ArrayList<Integer>(myPebbles);
        myPebbles.clear();
        return copy;
    }
    
    public void fillBag(ArrayList<Integer> whiteBag) {
        myPebbles = new ArrayList<Integer>(whiteBag);
    }
    
    public static void main (String args[]){
        MyBag bagA = new MyBag("pebbles.txt");
        MyBag bagX = new MyBag();
        
                System.out.println(bagX.myPebbles.size());
        bagX.fillBag(bagA.emptyBag());
        System.out.println(bagX.myPebbles.size());
        
    }
}
