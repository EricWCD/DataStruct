/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

public class BalancedSet {
    private final int MINIMUM = 1;
    private final int MAXIMUM = 2*MINIMUM;
    int dataCount;
    int[ ] data = new int[MAXIMUM + 1];
    int childCount;
    BalancedSet[ ] subset = new BalancedSet[MAXIMUM + 2]; 

    public BalancedSet( ) {
        dataCount = 0;
        childCount = 0;
    }

    public void print(int indent) {
        final int INDENT = 4;
        int i;
        int space;
        for (space = 0; space < indent; space++)
            System.out.print(" ");
        for (i = 0; i < dataCount; i++)
            System.out.print(data[i] + " ");
        System.out.println( );         
        for (i = 0; i < childCount; i++)
            subset[i].print(indent + INDENT);
    }

    private boolean isLeaf( )  {
        return (childCount == 0);
    }
    
    private int firstGE(int target)   {
        int x;
        for (x=0; x<dataCount && data[x] < target; x++);
        return x;        
    }
    
    public boolean contains(int target)  {
        int i = firstGE(target);
        if (i < data.length && data[i] == target) // found it
            return true; 
        if (childCount == 0) // this is a leaf â€“ not found
            return false;
        return subset[i].contains(target);    
    } 

    // ****************** START HERE *****************
    private void insertData(int insertIndex, int entry) {
        
    }

    private void insertSubset(int insertIndex, BalancedSet set) {
    }

    private void looseAdd(int entry) {
        int i;
        for (i = 0; i < dataCount && data[i] < entry; i++);
            if (i < data.length && data[i] == entry) {
                return;
            }
            if (childCount == 0) {// add entry at this node
                for (int x = data.length -1; x > i; x--) {
                    data[x] = data[x -1]; // shift elements to make room
                    data[i] = entry;
                    dataCount++;
                }
        }else{// add entry to a subset, housekeep
//                subset[i].looseAdd(entry);
                if (subset[i].dataCount > MAXIMUM) {
                    fixExcess(i);
                }
            }
    }

    public void add(int element) {
//        looseAdd(element);
        // add data, then chck to see if node still OK; if not:
    }
    
    private void fixExcess(int i) {
    }
    
    
        
}
