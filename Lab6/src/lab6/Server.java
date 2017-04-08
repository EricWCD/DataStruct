
package lab6;

/****************************************************************************** 
* @author Michael Main 
* (main@colorado.edu) 
* 
* @version 
* Jun 12, 1998 
* 
* Revisions by Cate Sheller, Nov 13, 2007 
******************************************************************************/ 
public class Server {
    private int secondsForService; // Seconds for a single wash 
	private int timeLeft; // Seconds until this Server is no longer busy 
	public Server(int s) { 
	secondsForService = s; 
	timeLeft =0; 
	} 
	public boolean isBusy( ) { 
		return (timeLeft > 0); 
	} 
	public void reduceRemainingTime( ) { 
		if (timeLeft > 0) timeLeft--; 
	} 
	public void start( ) { 
		if (timeLeft > 0) 
			throw new IllegalStateException("Server is already busy."); 
		timeLeft = secondsForService;
  } 
}
