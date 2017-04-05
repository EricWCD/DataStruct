/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eric;

/**
 *
 * @author Eric Walton
 */
public class Rest implements MusicNote{

    char Duration = 'w';
    String Pitch = "R";
    /**
    * char array that holds a char that represents duration 
    * w = whole
    * h = half
    * q = quarter
    * e = eighth
    * s = sixteenth
    */
   char[] validDurations = new char[]{'w','h','q','e','s'};
    
    @Override
    public void setPitch(String p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPitch(int midi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDuration(char name) {
        if (isDurationValid(name)) {
            this.Duration = name;
        }
    }

    @Override
    public void changePitch(int factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getDuration() {
        return this.Duration;
    }

    @Override
    public String getPitchSymbol() {
        return Pitch;
    }

    @Override
    public int getPitchMIDIvalue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPitchFrequency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
} // end of class
