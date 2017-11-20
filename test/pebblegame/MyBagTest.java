/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liamvinson
 */
public class MyBagTest {
    
    static MyBag black;
    static MyBag white;
    
    public MyBagTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        black = new MyBag("testPebbles.txt", "black");
        white = new MyBag("white");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readFrom method, of class MyBag.
     */
    @Test
    public void testReadFrom() throws Exception {
        System.out.println("readFrom");
        
        ArrayList<Integer> expectedPebbles = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
        assertEquals(black.myPebbles, expectedPebbles);
    }
    
    @Test(expected = IOException.class)
    public void testReadFromIOException() throws Exception {
        String t = "testPebbles2.txt";
        
        MyBag instance = new MyBag(t, "A");
        ArrayList<Integer> expectedPebbles = new ArrayList<Integer>(Arrays.asList(1,2,3,4,-5,6,7,8,9,10));
    }

    /**
     * Test of setCoBag method, of class MyBag.
     */
    @Test
    public void testSetCoBag() {
        System.out.println("setCoBag");
        black.setCoBag(white);
        white.setCoBag(black);
        assertEquals(black.corrospondingBag, white);
        assertEquals(white.corrospondingBag, black);
    }

    /**
     * Test of draw10 method, of class MyBag.
     */
    @Test
    public void testDraw10() throws Exception {
        System.out.println("draw10");
        int expResult = 10;
        int result = black.draw10().size();
        assertEquals(expResult, result);
        assertEquals(expResult, black.myPebbles.size());
    }

    /**
     * Test of drawOne method, of class MyBag.
     */
    @Test
    public void testDrawOne() throws Exception{
        System.out.println("drawOne");
        
        for(int i=0; i<5; i++) {
            white.myPebbles.add(black.drawOne());
        }
        
        
        
        assertEquals(5, white.myPebbles.size());
        assertEquals(5, black.myPebbles.size());
    }

    /**
     * Test of emptyBag method, of class MyBag.
     */
    @Test
    public void testEmptyBag() {
        System.out.println("emptyBag");
        
        ArrayList<Integer> expResult = new ArrayList<Integer>(white.myPebbles);
        black.myPebbles = white.emptyBag();
        assertEquals(expResult, black.myPebbles);
        assertEquals(0, white.myPebbles.size());
    }

    /**
     * Test of checkEmpty method, of class MyBag.
     */
    @Test
    public void testCheckEmpty() {
        System.out.println("checkEmpty");
        
        boolean result = white.checkEmpty();
        boolean result2 = black.checkEmpty();
        
        assertEquals(true, result);
        assertEquals(false, result2);
    }

    /**
     * Test of checkMin method, of class MyBag.
     */
    @Test(expected = IOException.class)
    public void testCheckMinThrows() throws Exception {
        System.out.println("checkMin");
        
        black.checkMin(25);
    }
    
    @Test
    public void testCheckMin() throws Exception {
        System.out.println("checkMin");
        
        black.checkMin(5);
    }
    
}
