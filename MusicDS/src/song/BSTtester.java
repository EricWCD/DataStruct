package song;

import static java.lang.Integer.parseInt;
import java.util.*;

/**
 *
 * @author nh228u27
 */
public class BSTtester {
    
    public static void RunBSTTester(){
              BST<Integer> tree = new BST<>(10);
        Scanner kb = new Scanner(System.in);
        System.out.println("Building binary search tree with known values");
        tree.insert(15);
        tree.insert(8);
        tree.insert(3);
        tree.insert(6);
        tree.insert(12);
        tree.insert(16);
        tree.insert(7);
        tree.print();
        Object[] treeData = tree.makeArray();
        for (int x=0; x<treeData.length; x++) {
            System.out.print(treeData[x] + " ");
        }
        System.out.println(); 
        int target = (int)(Math.random()*20 + 1);
        System.out.println("Looking for " + target);
        if(tree.findTarget(target))
            System.out.println("Found");
        else
            System.out.println("Not found");
        System.out.println("Building binary search tree with random values");
        tree = new BST<Integer>((int)(Math.random()*50));
        for (int x=0; x<14; x++)
            tree.insert((int)(Math.random()*50));
        tree.print();
        target = tree.getRoot().getData();
        
        /**
         * 3a assignment tester
         */
        System.out.println("Enter a value to find the number of instances less than your value in the tree.");
        int userNumber = parseInt(kb.nextLine());
        System.out.println("nums less than "  + userNumber + ": " + tree.numLess(userNumber));
        
        /**
         * 3b assignment tester
         */
        System.out.println("Enter a value to find the number of instances greater than your value in the tree.");
        userNumber = parseInt(kb.nextLine());
        System.out.println("nums greater than " + userNumber + ": " + tree.numGreater(userNumber));
        
        /**
         * 3c assignment tester
         */
        System.out.println("Enter a begin value and an end value to find instances between them.");
        System.out.println("Enter begin number");
        userNumber = parseInt(kb.nextLine());
        System.out.println("Enter end number");
        int endNumber = parseInt(kb.nextLine());
        System.out.println("nums between " + userNumber + " and " + endNumber + ": " + tree.numBetween(userNumber, endNumber));
        
        while(target != 0) {
            System.out.print("Enter value to remove (or 0 to quit): ");
            target = kb.nextInt();
            tree.remove(target);
            tree.print();
        }  
    }
    
    
    
} // end of class
