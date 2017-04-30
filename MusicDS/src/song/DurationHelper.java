/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

/**
 *
 * @author Eric Walton
 */
public class DurationHelper {
    
    static double s = 0.0625;
    static double e = 0.125;
    static double q = 0.25;
    static double h = 0.50;
    static double w = 1;
    static double measureDuration = 0;
    
    public static double getDurationValue(char duration){
        double result = 0;
        
        switch (duration) {
                case 's':
                    measureDuration = s;
                    break;
                case 'e':
                    measureDuration = e;
                    break;
                case 'q':
                    measureDuration = q;
                    break;
                case 'h':
                    measureDuration = h;
                    break;
                case 'w':
                    measureDuration = w;
                    break;
                default:
                    break;
            }
        
        
        return result;
    }
    
}
