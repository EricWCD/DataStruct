/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.util.Arrays;
import static song.DurationHelper.getDurationValue;


/**
 *
 * @author Eric Walton
 * Jessica Hoppe
 */
public class Note implements MusicNote {

   int PitchMIDIvalue = 0;
   double PitchFrequency = 0;
   String Pitch = null;
   char Duration = 'w';
   /**
    * char array that holds a char that represents duration 
    * w = whole
    * h = half
    * q = quarter
    * e = eighth
    * s = sixteenth
    */
   char[] validDurations = new char[]{'w','h','q','e','s'};
   /**
    * 2 dimensional object array that holds int, string, double
    * the int is the MEDI value 
    * the string is the key
    * the double is the frequency 
    */
   Object[][] validPitchOptions = {{21,"A0",27.500},{22,"A0#",29.135},{23,"B0",30.868},{24,"C1",32.703},{25,"C1#",34.648},
                      {26,"D1",36.708},{27,"D1#",38.891},{28,"E1",41.203},{29,"F1",43.654},{30,"F1#",46.249},
                      {31,"G1",48.999},{32,"G1#",51.913},{33,"A1",55.000},{34,"A1#",58.270},{35,"B1",61,735},
                      {36,"C2",65.406},{37,"C2#",69.296},{38,"D2",73.416},{39,"D2#",77.782},{40,"E2",82.407},
                      {41,"F2",87.307},{42,"F2#",92.499},{43,"G2",97.999},{44,"G2#",103.83},{45,"A2",110.00},
                      {46,"A2#",116.54},{47,"B2",123.47},{48,"C3",130.81},{49,"C3#",138.59},{50,"D3",146.83},
                      {51,"D3#",155.56},{52,"E3",164.81},{53,"F3",174.61},{54,"F3#",185.00},{55,"G3",196.00},
                      {56,"G3#",207.65},{57,"A3",220.00},{58,"A3#",233.08},{59,"B3",246.94},{60,"C4",261.63},
                      {61,"C4#",277.18},{62,"D4",293.67},{63,"D4#",311.13},{64,"E4",329.63},{65,"F4",349.23},
                      {66,"F4#",369.99},{67,"G4",392.00},{68,"G4#",415.30},{69,"A4",440.00},{70,"A4#",466.16},
                      {71,"B4",493.88},{72,"C5",523.25},{73,"C#5",554.37},{74,"D5",587.33},{75,"D5#",622.25},
                      {76,"E5",659.26},{77,"F5",698.46},{78,"F5#",739.99},{79,"G5",783.99},{80,"G5#",830.61},
                      {81,"A5",880.00},{82,"A5#",932.33},{83,"B5",987.77},{84,"C6",1046.5},{85,"C6#",1108.7},
                      {86,"D6",1174.7},{87,"D6#",1244.5},{88,"E6",1318.5},{89,"F6",1396.9},{90,"F6#",1480.0},
                      {91,"G6",1568.0},{92,"G6#",1661.2},{93,"A6",1760.0},{94,"A6#",1864.7},{95,"B6",1975.5},
                      {96,"C7",2093.0},{97,"C7#",2217.5},{98,"D7",2349.3},{99,"D7#",2489.0},{100,"E7",2637.0},
                      {101,"F7",2793.0},{102,"F7#",2960.0},{103,"G7",3136.0},{104,"G7#",3322.4},{105,"A7",3520.0},
                      {106,"A7#",3729.3},{107,"B7",3951.1},{108,"C8",4186.0}
                    };
   
   /**
    * default constructor that sets the pitch to middle C and the duration to quarter
    */
   public Note(){
       this.Pitch = "C4";
       this.Duration = 'q';
   }
           
    /**
     * A constructor that sets the pitch and the duration
     * @param pitch
     * @param duration 
     */
    public Note(int pitch,char duration) {
        // need to set the pitch to the midi value
        if (isDurationValid(duration)) {
            this.Duration = duration;
        }
        Object[] validNote = null;
        for (Object[] midiNote:validPitchOptions) {
            if ((int)midiNote[0] == pitch) {
               validNote = midiNote;
            }
        }
        if (validNote != null) {
             this.Pitch = validNote[1].toString();
             this.PitchMIDIvalue = (int)validNote[0];
             this.PitchFrequency = (double)validNote[2];
        } else {
            throw new IllegalArgumentException("Invalid midi value please select 21 through 108");
        } 
    }
    /**
     * A constructor that sets the pitch
     * @param pitch 
     */
    public Note(String pitch){
        this.Pitch = pitch;
    }
    /**
     * A constructor that sets the duration
     * @param duration 
     */
    public Note(char duration){
        if (isDurationValid(duration)) {
            this.Duration = duration;
        }
    }        
   
    /**
     * 
     * @param duration
     * @return 
     */
    private boolean isDurationValid(char duration){
        boolean valid = false;
        for (char dur:validDurations) {
            if (duration == dur) {
                valid = true;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Invalid duration");
        }
        
        return valid;
    }
    
   /**
    * 
    * @param p 
    */
    @Override
    public void setPitch(String p) {
        Object[] validNote = null;
        for (Object[] note:validPitchOptions) {
            if (note[1].toString().equals(p)) {
                validNote = note;
            }
        }
        if (validNote != null) {
            this.Pitch = validNote[1].toString();
            this.PitchMIDIvalue = (int)validNote[0];
            this.PitchFrequency = (double)validNote[2];
        } else {
            throw new IllegalArgumentException("Invalid note");
        }
    }

    /**
     * 
     * @param midi 
     */
    @Override
    public void setPitch(int midi) {
        Object[] validNote = null;
        for (Object[] midiNote:validPitchOptions) {
            if ((int)midiNote[0] == midi) {
               validNote = midiNote;
            }
        }
        if (validNote != null) {
             this.Pitch = validNote[1].toString();
             this.PitchMIDIvalue = (int)validNote[0];
             this.PitchFrequency = (double)validNote[2];
        } else {
            throw new IllegalArgumentException("Invalid midi value please select 21 through 108");
        } 
    }

    /**
     * 
     * @param name 
     */
    @Override
    public void setDuration(char name) {
        if (isDurationValid(name)) {
            this.Duration = name;
        }
    }

    /**
     * 
     * @param factor 
     */
    @Override
    public void changePitch(int factor) {
        int newMidi = this.PitchMIDIvalue + factor;
        Object[] newNote = null;
        for (Object[] note:validPitchOptions) {
            if ((int)note[0] == newMidi) {
                newNote = note;
            }
        }
        if (newNote != null) {
            this.PitchMIDIvalue = (int)newNote[0];
            this.Pitch = newNote[1].toString();
            this.PitchFrequency = (double)newNote[2];
        }else{
            throw new IllegalArgumentException("Pitch change is not a valid change.");
        }
        
    }

    /**
     * 
     * @return 
     */
    @Override
    public char getDuration() {
        return this.Duration;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String getPitchSymbol() {
        return this.Pitch;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int getPitchMIDIvalue() {
        return this.PitchMIDIvalue;
    }

    /**
     * 
     * @return 
     */
    @Override
    public double getPitchFrequency() {
        return this.PitchFrequency;
    }
    
   public String toString(){ 
       return this.getPitchSymbol() + " " + this.Duration + ", ";
   }

    @Override
    public int compareTo(MusicNote o) {
        int result = compareMIDI(o);

        if (result == 0) {
            result = compareDuration(o);
        }
        
        return result;
    }

    /**
     * 
     * @param other
     * @return 
     */
    public int compareMIDI(MusicNote other){
        int result = 0;
        
        if (other.getPitchMIDIvalue() > this.PitchMIDIvalue) {
            result = 1;
        }else if (other.getPitchMIDIvalue() == this.PitchMIDIvalue){
            result = 0;
        }else{
            result = -1;
        }
        
        return result;
    }
    
    /**
     * 
     * @param other
     * @return 
     */
    public int compareDuration(MusicNote other){
        int result = 0;
        double otherDur = getDurationValue(other.getDuration());
        double thisDur = getDurationValue(this.Duration);
        
        if (otherDur > thisDur) {
            result = 1;
        }else if (otherDur == thisDur){
            result = 0;
        }else{
            result = -1;
        }
        return result;
    }

    
    
    
    
} // end of class
