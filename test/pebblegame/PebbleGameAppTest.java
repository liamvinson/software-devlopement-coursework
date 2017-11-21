/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblegame;

import java.util.Scanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liamvinson
 */
public class PebbleGameAppTest {
    
    public PebbleGameAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of doWork method, of class PebbleGameApp.
     */
    @Test
    public void testDoWork() {
        System.out.println("doWork");
        String s = "2 testGame.txt testGame.txt testGame.txt";
        Scanner input = new Scanner(s);
        PebbleGameApp.doWork(input);
        
        String s2 = "-1 1 testGame.txt testGame.txt testGame.txt";
        Scanner input2 = new Scanner(s2);
        PebbleGameApp.doWork(input2);
        
        String s3 = "3 testPebbles.txt testGame.txt testGame.txt testGame.txt";
        Scanner input3 = new Scanner(s3);
        PebbleGameApp.doWork(input3);
    }
}
