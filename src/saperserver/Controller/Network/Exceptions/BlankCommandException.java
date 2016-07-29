package saperserver.Controller.Network.Exceptions;

/**
 * @author Damian
 */
public class BlankCommandException extends Exception {
    
    public BlankCommandException() {
        
        super( "Downloaded a command from the Client is blank !!!" );
    }
}
