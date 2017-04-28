/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

/**
 *
 * @author Eric Walton
 */
public class Program4Manager {
    
    public static void DoProgram4Stuff(){
        Note note = new Note();
//        BST<MusicNote> tree = new BST<note>(10);
          BST<Note> tree1 = new BST<>(note);
          System.out.println("Tree 1: " + tree1.getRoot().getData().getPitchSymbol());
//          System.out.println(tree1.getRoot().getData().compareTo(note)); // need to flesh out compareTo
    }
    
} // end of class
