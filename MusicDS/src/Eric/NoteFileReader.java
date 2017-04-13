/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Eric Walton
 */
public class NoteFileReader {

    private static char separator = ' ';

    public static Song readFile() {
        
        // method variables
        Song song = new Song("abc.txt");
        Song repeatQueue = new Song();
        Note note = new Note();
        boolean repeat = false;
        // end method variables
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(song.getNoteFilePath())));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fileObject = line.split("\\s");
                
                note.setPitch(fileObject[0]);
                note.setDuration(fileObject[1].charAt(0));
                
                if (fileObject[2].equals("true")) {
                    repeat = !repeat;
                }
                
                if (repeat) {
                    song.getSongQueue().add(note);
                    repeatQueue.getSongQueue().add(note);
                }else{
                    if (repeatQueue.getSongQueue().size() > 0) {
                        while(!repeatQueue.getSongQueue().isEmpty()){
                            song.getSongQueue().add(repeatQueue.getSongQueue().poll());
                        }
                        
                    }else{
                        song.getSongQueue().add(note);
                    }
                }
                System.out.println("r: " + repeatQueue.getSongQueue().size());
                System.out.println("s: " + song.getSongQueue().size());
            
                // Project thisProject = new Project();
//                thisProject.setName(fileObject[2]);
//                thisProject.setDescription(fileObject[3]);
//                projects.add(thisProject);
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
