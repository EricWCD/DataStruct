/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.util.Scanner;

/**
 *
 * @author Eric Walton
 */
public class Program4Manager {

    private static Scanner kb = new Scanner(System.in);

    public static void DoProgram4Stuff() {
        
        Song song1 = getSong();
        BST<MusicNote> tree1 = new BST<>(song1.getSongQueue().element());
        Song song2 = new Song();
               song2 = getSong();
               
        BST<MusicNote> tree2 = new BST<>(song2.getSongQueue().element());
//        System.out.println("song1 " + song1); // uncomment these to see the song
//        System.out.println("song2" + song2); // uncomment these to see the song
        for (MusicNote note : song1.getSongQueue()) {
            tree1.insert(note);
        }
        for (MusicNote note2 : song2.getSongQueue()) {
            tree2.insert(note2);
        }

        System.out.println("note: " + tree1.getRoot().getData() + "\n" + "note2: " + tree2.getRoot().getData());
        System.out.println("Compare midi test: " + tree1.getRoot().getData().compareTo(tree2.getRoot().getData()));
//        tree1.getRoot().inorderPrint(); // uncomment to see inorder tree1
            
    }

    public static Song getSong() {
        Song song = new Song();
        System.out.println("file names: abc.txt or frerejacques.txt");
        try {
            System.out.println("Enter file name: ");
            song = NoteFileReader.readFile(kb.nextLine());
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return song;
    }

} // end of class
