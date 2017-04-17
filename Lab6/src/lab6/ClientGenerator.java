
package lab6;

/****************************************************************************** 
* @author Michael Main 
* (main@colorado.edu) 
* * @version 
* Jun 12, 1998 
******************************************************************************/ 
public class ClientGenerator {
    private double probability; 
	// The approximate probability of query( ) returning true. 
	public ClientGenerator(double p) { 
		if ((p < 0) || (1 < p)) 
			throw new IllegalArgumentException("Illegal p: " + p); 
		probability = p; 
	} 
	public boolean query( ) { 
		return (Math.random( ) < probability); 
	} 
}
