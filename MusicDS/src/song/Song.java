/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Eric Walton
 * @author Jessica Hoppe
 * @author Laura Simmonds
 */
public class Song {

//    Queue<Integer> queue = new LinkedList<Integer>();
    private Queue<Note> SongQueue;
    private String noteFilePath; // set for now but can be passed in later

    public Song() {
        this.SongQueue = new LinkedList<Note>();
    }

    public Song(String noteFilePath) {
        this.noteFilePath = noteFilePath;
        this.SongQueue = new LinkedList<Note>();
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

    @Override
    public String toString() {
        String output = "Song: ";
        int songSize = 0;
        for (Note note : SongQueue) {
            songSize++;
            if (songSize % 15 == 0) {
                output += "\n";
            }
            output += note;
        }
        return output;
    }

    public double GetSongLength(Song song) {
        double result = 0;
        for (Note note : SongQueue) {
            switch (note.Duration) {
                case 'w':
                    result += 1;
                    break;
                case 'h':
                    result += 0.5;
                    break;
                case 'q':
                    result += 0.25;
                    break;
                case 'e':
                    result += 0.125;
                    break;
                case 's':
                    result += 0.0625;
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return result;
    }

    public double changeTempo(int tempo) {
        double result = 0;
        for (Note note : SongQueue) {
            if (tempo == 1) {
                switch (note.Duration) {
                    case 'w':
                        result += 1;
                        break;
                    case 'h':
                        result += 0.5;
                        break;
                    case 'q':
                        result += 0.25;
                        break;
                    case 'e':
                        result += 0.125;
                        break;
                    case 's':
                        result += 0.0625;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            if (tempo == 2) {
                switch (note.Duration) {
                    case 'w':
                        result += .5;
                        break;
                    case 'h':
                        result += 0.25;
                        break;
                    case 'q':
                        result += 0.125;
                        break;
                    case 'e':
                        result += 0.0625;
                        break;
                    case 's':
                        result += 0.0625;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            if (tempo == -2) {
                switch (note.Duration) {
                    case 'w':
                        result += 1;
                        break;
                    case 'h':
                        result += 1;
                        break;
                    case 'q':
                        result += 0.5;
                        break;
                    case 'e':
                        result += 0.25;
                        break;
                    case 's':
                        result += 0.0625;
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            if (tempo == 0) {
                throw new IllegalArgumentException("Tempo can not be 0");
            }
        }
        return result;
    }

    public Song reverseSong(Song song) {

        Stack stacky = new Stack();  //create a stack

        //empty the song while pushing it onto the stack
        while (!song.SongQueue.isEmpty()) {
            stacky.push(song.SongQueue.poll());
        }
        // takes the stack and puts it back in the song in reverse order
        while (!stacky.isEmpty()) {
            song.SongQueue.offer((Note) stacky.pop());
        }
        return song;
    }

    public Song appendSong(Song song1, Song song2) {
        Song newSong = new Song();
        while (!song1.getSongQueue().isEmpty()) {
            newSong.addNote(song1.getSongQueue().poll());
        }
        while (!song2.getSongQueue().isEmpty()) {
            newSong.addNote(song2.getSongQueue().poll());
        }
        
        return newSong;
    }

} // end class
