package song;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cate Sheller
 */
public interface MusicNote 
{
    /**
     * Sets note pitch using a String containing a letter and a number
     * @param p is a valid letter-number combination
     * where the letter is in the range A..G (upper or lowercase)
     * and the number is in the range 1..7
     * postcondition: pitch is set to specified value
     * @throws: IllegalArgumentException
     */
	public void setPitch(String p);
    
	/**
     * Sets note pitch using a whole number value
     * @param midi is a valid MIDI value for the notes in the range
     * A1 - C8
     * postcondition: pitch is set to specified value
     * @throws: IllegalArgumentException
     */    
    public void setPitch(int midi);
     
    /**
      * Sets note duration to specified value
      * @param name is a char from the set {w, h, q, e, s}
      * representing whole, half, quarter, eighth and sixteenth 
      * notes respectively
      * postcondition: duration is set to specified value
      * @throws: IllegalArgumentException
      */
    public void setDuration(char name);
        
    /**
     * Changes pitch up or down by specified factor
     * @param factor is an int value representing the
     * number of pitches between the current pitch and the desired
     * pitch; factor must fall within the possible range of values
     * such that pitch cannot be set above C8 or below A0
     * postcondition: pitch is changed by specified factor;
     * if factor is positive, pitch will be higher and if negative,
     * pitch will be lower
     * @throws IllegalArgumentException
     */
    public void changePitch(int factor);
		
	/**
	 * Returns duration of note
	 * @return char value from the set {w, h, q, e, s}
     * representing whole, half, quarter, eighth and sixteenth 
     * notes respectively
	 */
    public char getDuration ();
       
    /**
     * Returns note's pitch symbol
     * @return String version of pitch: e.g. C4 for middle C
     */
    public String getPitchSymbol();
        
    /**
     * Returns note's MIDI value
     * @return an int version of pitch: valid MIDI value
     */
    public int getPitchMIDIvalue();
        
    /**
     * Returns note's frequency in Hertz
     * @return pitch represented by double value in Hertz
     */
    public double getPitchFrequency();    
}
