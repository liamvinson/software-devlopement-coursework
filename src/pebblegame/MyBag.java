/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

/**
 * Write a description of class MyBag here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyBag
{
    // instance variables - replace the example below with your own
    ArrayList<Integer> myPebbles = new ArrayList<Integer>();

    MyBag corrospondingBag;
    String bagName;

    public MyBag(String bagName){
        this.bagName = bagName;
    }

    public MyBag(String bagFile, String bagName) throws IOException
    {
        readFrom(bagFile);
        this.bagName = bagName;
    }

    public void readFrom(String t) throws IOException {
        InputStream in = getClass().getResourceAsStream(t);
        Scanner scanner = new Scanner(in);
        String line;
        String[] lineVector;

        line = scanner.nextLine();

        lineVector = line.split(",");

        for (String item : lineVector){
            int temp = Integer.parseInt(item);
            if (temp < 1){
                throw new IOException("Numbers in file must be positive integers");
            }
            myPebbles.add(temp);
        }
    }

    public void setCoBag(MyBag bag){
        corrospondingBag = bag;
    }

    public ArrayList<Integer> draw10(){
        ArrayList<Integer> pebbles10 = new ArrayList<Integer>();

        for(int i=0; i<10; i++){
            Random random = new Random();
            int select = random.nextInt(myPebbles.size()-1);
            pebbles10.add(myPebbles.remove(select));
        }
        return pebbles10;
    }

    public int drawOne(){
        Random random = new Random();
        if (myPebbles.isEmpty()){
            myPebbles = corrospondingBag.emptyBag();
        }
        int select = random.nextInt(myPebbles.size());
        if (select != 0){ select--;}
        return myPebbles.remove(select);
    }

    public ArrayList<Integer> emptyBag() {
        ArrayList<Integer> copy = new ArrayList<Integer>(myPebbles);
        myPebbles.clear();
        return copy;
    }

    public boolean checkEmpty() {
        if (myPebbles.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void checkMin(int check) throws IOException{
        if (check > myPebbles.size()){
            throw new IOException("Number of pebbles must be 11 times the number of players!");
        }
    }
}

