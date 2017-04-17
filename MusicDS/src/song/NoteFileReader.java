/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Level;

/**
 *
 * @author Eric Walton
 */
public class NoteFileReader {

    private static char separator = ' ';

    public static Song readFile(String fileName) {
        
        // method variables
        Song song = new Song(fileName);
        Song repeatQueue = new Song();
        
        boolean repeat = false;
        // end method variables
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(song.getNoteFilePath())));
            String line;
            while ((line = reader.readLine()) != null) {
                Note note = new Note();
                String[] fileObject = line.split("\\s");
                
                note.setPitch(fileObject[0]);
                note.setDuration(fileObject[1].charAt(0));
                if (repeat) {
                    song.addNote(note);
                    repeatQueue.addNote(note);
                }else{
                    if (repeatQueue.getSongQueue().size() > 0) {
                        while(!repeatQueue.getSongQueue().isEmpty()){
                            song.addNote(repeatQueue.getSongQueue().poll());
                        }
                        
                    }else{
                        song.addNote(note);
                    }
                }
                if (fileObject[2].equals("true")) {
                    repeat = !repeat;
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File could not be found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("There was an error reading from the file.\nError: ");
            System.exit(-1);
        }
        return song;
    }

}
