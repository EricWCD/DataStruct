/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.util.Scanner;
import org.jfugue.*;

/**
 * All of the interface methods are implemented in note but need to be configured.
 * Test class for MusicNote assignment in CSC-153
 * @author Cate Sheller
 * @version 20 January 2016
 * 
 * @author Laura Simmonds
 * @author Eric Walton
 * @author Jessica Hoppe
 * 
 * edited 15 April 2017
 */


public class MusicDS {

    private static Song song = new Song();
    private static Scanner kb = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         MusicNote note = new Note();
          // Automatic tests
//        runAutoTests(note);
        // Interactive tests
        runMusicNote3Test();
//        runUserTests(note); moved to end of runMusicNote3Test
    } // end main
    
    private static void runMusicNote3Test(){
        
        System.out.println("Making an empty song");
        Song autoSong = new Song();
        System.out.println(autoSong);
        System.out.println("Reading ABC song from file");
        System.out.println(autoSong = NoteFileReader.readFile("abc.txt"));
        System.out.println("Getting song lenght");
        System.out.println(autoSong.GetSongLength(autoSong));
        System.out.println("Reverse abc song: ");
        System.out.println(autoSong.reverseSong(autoSong));
        System.out.println("Changing ABC tempo to faster");
        System.out.println(autoSong.changeTempo(2));
        System.out.println("Changing ABC tempo to slower");
        System.out.println(autoSong.changeTempo(-2));
        System.out.println("Getting 2nd song frere jacques");
        Song autoSong2 = NoteFileReader.readFile("frerejacques.txt");
        System.out.println(autoSong2);
        System.out.println("Reversing abc back to original: ");
        System.out.println(autoSong.reverseSong(autoSong));
        System.out.println("Adding frere jacques to the end of abc song: ");
        System.out.println(autoSong.appendSong(autoSong, autoSong2));
        System.out.println("Go to user menu or quit");
        System.out.println("M. Menu");
        System.out.println("Q. Quit");
        if (kb.nextLine().equalsIgnoreCase("m")) {
            MusicNote note = new Note();
            runUserTests(note);
        }else{
            System.exit(0);
        }
        
    }
    
   public static void runAutoTests(MusicNote n) {
       
        System.out.println("*******Testing Measure");
         Measure measure = new Measure();
         System.out.println("Adding C4 quarter note 4 times.");
        try {
            measure.addNote( n);
            measure.addNote( n);
            measure.addNote( n);
            measure.addNote( n);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
        System.out.println("Adding C4 quarter note again. Should throw error");
        try {
            measure.addNote( n);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }
        System.out.println("measure count " + measure.getMeasure().size());
        System.out.println(measure);
        
        System.out.println("******* AUTO TESTS*******");
        getNoteInfo(n);
        System.out.println("*******Testing setPitch(String)*******");
        System.out.println("Changing pitch to G3");
        n.setPitch("G3");
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n);
        System.out.println("Attempting to change to Z1");
        try {
            n.setPitch("Z1");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*******Testing setPitch(int)*******");
        System.out.println("Changing pitch to MIDI value 71");
        n.setPitch(71);
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n);
        System.out.println("Attempting to change to -5");
        try {
            n.setPitch(-5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n);
        System.out.println("*******Testing setDuration(char)*******");
        System.out.println("Changing duration to w");
        n.setDuration('w');
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n);
        System.out.println("Attempting to change to x");
        try {
            n.setDuration('x');
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*******Testing changePitch(int)*******");
        System.out.println("Changing pitch by 5");
        n.changePitch(5);
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n);
        System.out.println("Attempting to change by 700");
        try {
            n.changePitch(700);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*******Testing changePitch(int)*******");
        System.out.println("Changing pitch by -3");
        n.changePitch(-3);
        getNoteInfo(n);
        // Optional: plays note as sound
        playNote(n); 
        System.out.println("Attempting to change by -2000");
        try {
            n.changePitch(-2000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        
    }

    public static void getNoteInfo(MusicNote n) {
        System.out.println("n=" + n);
        System.out.println("Pitch symbol is " + n.getPitchSymbol());
        System.out.println("Pitch MIDI value is " + n.getPitchMIDIvalue());
        System.out.println("Pitch frequency is " + n.getPitchFrequency());
        System.out.println("Note duration is " + n.getDuration());
    }

    // Optional: uses code from jfugue library
    public static void playNote(MusicNote n) {
        Player piano = new Player();
        String note = n.getPitchSymbol();
        char dur = n.getDuration();
        if (dur=='s')
            dur = 'i';  // jfugue uses 'i' for sixteenth instead of 's'
        note = note + dur;
        Pattern phrase = new Pattern(note);
        piano.play(phrase);
    }
    
    
    

    public static void runUserTests(MusicNote n) {
        System.out.println("Current note information:");
        getNoteInfo(n);
        boolean done = false;
        while (!done) {
            System.out.println("Choose an option:");
            System.out.println("A. Change to specific pitch");
            System.out.println("B. Change duration");
            System.out.println("C. Set pitch higher");
            System.out.println("D. Set pitch lower");
            System.out.println("E. See note information");
            System.out.println("F. Hear note");
            System.out.println("G. Measure Menu");
            System.out.println("H. Read Song From File");
            System.out.println("I. Get song length");
            System.out.println("J. Change song tempo");
            System.out.println("K. Reverse song");
            System.out.println("L. Append a song to a song");
            System.out.println("M. Quit");
            System.out.print("Your choice: ");
            String input = kb.nextLine();
            if(input.equalsIgnoreCase("a"))
                n = changeP(kb, n);
            else if(input.equalsIgnoreCase("b"))
                n = changeD(kb, n);
            else if(input.equalsIgnoreCase("c"))
                n = higher(kb, n);
            else if(input.equalsIgnoreCase("d"))
                n = lower(kb, n);
            else if(input.equalsIgnoreCase("e"))
                getNoteInfo(n);
            else if(input.equalsIgnoreCase("f"))
                playNote(n);
            else if(input.equalsIgnoreCase("g"))
                runMeasureMenu(n);
            else if (input.equalsIgnoreCase("h")) {
                System.out.println("file names: abc.txt or frerejacques.txt");
                System.out.println("Enter file name: ");
                song = NoteFileReader.readFile(kb.nextLine());
                System.out.println("\n" + song);
                menuOrExit();
            }else if (input.equalsIgnoreCase("i")) {
                System.out.println("\n" + song);
                System.out.println("Length in seconds: " + song.GetSongLength(song));
                menuOrExit();
            } else if (input.equalsIgnoreCase("j")) {
                System.out.println("Please choose one of the following:");
                System.out.println(" 1. Leave the song unchanged.");
                System.out.println(" 2. Make each note half as long");
                System.out.println("-2. Make eacch note twice as long");
                int intInput = kb.nextInt();
                System.out.println("New length in seconds: " + song.changeTempo(intInput));
                menuOrExit();
            }else if (input.equalsIgnoreCase("k")) {
                System.out.println("\n" + song);
                System.out.println("Reversed: " + song.reverseSong(song));
                menuOrExit();
            }else if (input.equalsIgnoreCase("l")) {
                System.out.println("file names: abc.txt or frerejacques.txt");
                System.out.println("Enter file name: ");
                Song song2 = NoteFileReader.readFile(kb.nextLine());
                System.out.println("\n" + song);
                System.out.println("New Song: " + song.appendSong(song, song2));
                menuOrExit();
            }else if(input.equalsIgnoreCase("m")) {
                System.out.println("Program ending");
                done = true;
            }
            else
                System.out.println(input + " not a valid option. Try again.");
        }
        
    }
    
    public static void menuOrExit(){
        boolean done = false;
        while(!done){
            System.out.println("\nChoose an option:");
            System.out.println("M. Menu");
            System.out.println("Q. Quit");
            String input = kb.nextLine();
            if(input.equalsIgnoreCase("m")){
                done = true;
            }else if(input.equalsIgnoreCase("q")){
                System.exit(0);
            }
                
        }
    }
    
    
    public static void runMeasureMenu(MusicNote n){
        Measure measure = new Measure();
        Rest rest = new Rest();
        boolean done = false;
        while(!done){
             System.out.println("Choose an option:");
            System.out.println("A. Change to specific pitch");
            System.out.println("B. Change duration");
            System.out.println("C. Set pitch higher");
            System.out.println("D. Set pitch lower");
            System.out.println("E. See note information");
            System.out.println("F. Hear note");
            System.out.println("G. Add note");
            System.out.println("H. Change rest duration");
            System.out.println("I. Add rest");
            System.out.println("J. Quit");
            System.out.println("Current note: " + n.getPitchSymbol() + " " + n.getDuration());
            System.out.println("Rest info: " + "" + rest.Pitch + " " + rest.getDuration());
            System.out.println("measure count " + measure.getMeasure().size());
            System.out.println(measure);
            System.out.print("Your choice: ");
            String input = kb.nextLine();
            if(input.equalsIgnoreCase("a"))
                n = changeP(kb, n);
            else if(input.equalsIgnoreCase("b"))
                n = changeD(kb, n);
            else if(input.equalsIgnoreCase("c"))
                n = higher(kb, n);
            else if(input.equalsIgnoreCase("d"))
                n = lower(kb, n);
            else if(input.equalsIgnoreCase("e"))
                getNoteInfo(n);
            else if(input.equalsIgnoreCase("f"))
                playNote(n);
            else if(input.equalsIgnoreCase("g")){
                try {
                    measure.addNote(n);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            else if(input.equalsIgnoreCase("h")){
                // change rest duration
                rest = changeRestD(kb);
            }
            else if(input.equalsIgnoreCase("i")){
                // add rest
                try {
                    measure.addNote(rest);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            else if(input.equalsIgnoreCase("j")){
                done = true;
            }
            
        }
    }
    

    public static MusicNote changeP(Scanner kb, MusicNote n) {
        String input;
        MusicNote m = new Note();
        m.setPitch(n.getPitchSymbol());
        m.setDuration(n.getDuration());
        System.out.println("Choose one option:");
        System.out.println("1) Change pitch with symbol");
        System.out.println("2) Change pitch with MIDI value");
        System.out.print("Enter 1 or 2: ");
        String choice = kb.nextLine();
        if(choice.equals("1")) {
            System.out.print("Enter new symbol: ");
            input = kb.nextLine();
            try {
                m.setPitch(input);
                System.out.println("New note information");
                getNoteInfo(m);
            }
            catch (IllegalArgumentException e) {
                System.out.println(input + " was not valid. No change to note.");
            }
        }
        else if (choice.equals("2")) {
            System.out.print("Enter new MIDI value: ");
            input = kb.nextLine();
            try {
                int mValue = Integer.parseInt(input);
                m.setPitch(mValue);
                System.out.println("New note information");
                getNoteInfo(m);
            }
            catch (NumberFormatException e) {
                System.out.println(input + " is not a number. No change to note.");
            }
            catch (IllegalArgumentException e) {
                System.out.println(input + 
                            " was not a valid MIDI value. No change to note");
            }
        }

        return m;
    }

    public static MusicNote changeD(Scanner kb, MusicNote n) {
        String input;
        MusicNote m = new Note();
        m.setPitch(n.getPitchSymbol());
        m.setDuration(n.getDuration());
        System.out.print("Enter letter for duration (w, h, q, e, s): ");
        input = kb.nextLine();
        try {
            m.setDuration(input.charAt(0));
            System.out.println("New note information");
            getNoteInfo(m);
        } catch (IllegalArgumentException e){
            System.out.println(input + 
                    " was not a valid duration. No change to note.");
        }      
        return m;
    }

    public static MusicNote higher(Scanner kb, MusicNote n) {
        System.out.print("Enter positive number for pitch change: ");
        String input = kb.nextLine();
        Note nn = new Note (n.getPitchMIDIvalue(), n.getDuration());
        try {
            int change = Integer.parseInt(input);
            if (change < 0)
                change = Math.abs(change);
            nn.changePitch(change);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Note unchanged");
        }
        return nn;
    }

    public static MusicNote lower(Scanner kb, MusicNote n) {
        System.out.print("Enter negative number for pitch change: ");
        String input = kb.nextLine();
        Note nn = new Note (n.getPitchMIDIvalue(), n.getDuration());
        try {
            int change = Integer.parseInt(input);
            if (change > 0)
                change = -change;
            nn.changePitch(change);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Note unchanged");
        }
        return nn;
    }
    
    public static Rest changeRestD(Scanner kb) {
        String input;
        Rest rest = new Rest();
        System.out.print("Enter letter for duration (w, h, q, e, s): ");
        input = kb.nextLine();
        try {
            rest.setDuration(input.charAt(0));
            System.out.println("New note information");
        } catch (IllegalArgumentException e){
            System.out.println(input + 
                    " was not a valid duration. No change to note.");
        }      
        return rest;
    }
    
} // end class
