/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.util.Random;
import java.util.Scanner;
import static lab9.BSTtester.RunBSTTester;

/**
 *
 * @author Eric Walton
 */
public class Lab9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     BalancedSet set = new BalancedSet();
        Scanner kb = new Scanner(System.in);
        Random rg = new Random();
        for (int x=0; x<50; x++)
        {
            int num = rg.nextInt(100);
            System.out.println("Adding " + num);
            set.add(num);
            System.out.println("tree so far:");
            set.print(4);
        }
        System.out.println("final version of tree");
        set.print(4); 
        int num = rg.nextInt(100);
        System.out.println("Searching for: " + num);
        if (set.contains(num))
            System.out.println ("found it!");
        else
            System.out.println ("all that time, and nothing to show for it!");
        
        RunBSTTester();
        
    }
    
}
