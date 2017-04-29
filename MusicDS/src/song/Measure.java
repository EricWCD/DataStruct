/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

import java.util.ArrayList;

/**
 *
 * @author Eric Walton
 */
public class Measure implements MusicNote {

    ArrayList<MusicNote> measure = new ArrayList<MusicNote>();
    double s = 0.0625;
    double e = 0.125;
    double q = 0.25;
    double h = 0.50;
    double w = 1;
    double measureDuration = 0;

    
    public void addNote(MusicNote note) {
        if (!isMeasureFull()) {
            measure.add(note);
            if (isMeasureFull()) {
                measure.remove(measure.size() - 1);
                throw new IllegalArgumentException("Note duration too large.");
            }
        }
    }

    public ArrayList<MusicNote> getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        String output = "Measure: ";
        for (int i = 0; i < measure.size(); i++) {
            output += "" + measure.get(i).getPitchSymbol() + " " + measure.get(i).getDuration() + ", ";
        }
        return output;
    }

    public boolean isMeasureFull() {
        char currentDuration;
        boolean full = false;
        measureDuration = 0;
        for (int i = 0; i < measure.size(); i++) {
            currentDuration = measure.get(i).getDuration();
            switch (currentDuration) {
                case 's':
                    measureDuration += s;
                    break;
                case 'e':
                    measureDuration += e;
                    break;
                case 'q':
                    measureDuration += q;
                    break;
                case 'h':
                    measureDuration += h;
                    break;
                case 'w':
                    measureDuration += w;
                    break;
                default:
                    break;
            }
        }
        if (measureDuration > 1) {
            full = true;
        }

        return full;
    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changePitch(int factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getDuration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPitchSymbol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPitchMIDIvalue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getPitchFrequency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(MusicNote o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
