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
        Song song = new Song("abc.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(song.getNoteFilePath())));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fileObject = line.split("\\s");
                
                Note note = new Note();
                System.out.println("pitch: " + fileObject[0] + " durp: " + fileObject[1]);
                note.setPitch(fileObject[0]);
                note.setDuration(fileObject[1].charAt(0));
                song.getSongQueue().add(note);
                System.out.println("song: " + song.getSongQueue().size());
            
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
