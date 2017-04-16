/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eric;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Eric Walton
 */
public class Song {
    
//    Queue<Integer> queue = new LinkedList<Integer>();
    private Queue<Note> SongQueue;
    private String noteFilePath; // set for now but can be passed in later

    public Song() {
        this.SongQueue = new LinkedList<Note>();
    }

    public Song( String noteFilePath) {
        this.noteFilePath = noteFilePath;
        this.SongQueue = new LinkedList<Note>();
    }

    @Override
    public String toString(){
         String output = "Song: ";
        for (Note note : SongQueue) {
            output += note;
        }
        return output;
    }
    
    public void addNote(Note note) {
        this.SongQueue.add(note);
    }
    
    /**
     * @return the SongQueue
     */
    public Queue<Note> getSongQueue() {
        return SongQueue;
    }

    /**
     * @param SongQueue the SongQueue to set
     */
    public void setSongQueue(Queue<Note> SongQueue) {
        this.SongQueue = SongQueue;
    }

    /**
     * @return the noteFilePath
     */
    public String getNoteFilePath() {
        return noteFilePath;
    }

    /**
     * @param noteFilePath the noteFilePath to set
     */
    public void setNoteFilePath(String noteFilePath) {
        this.noteFilePath = noteFilePath;
    }

    
    
    
    
    
    
}
