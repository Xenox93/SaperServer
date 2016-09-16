package saperserver.Exceptions;

/**
 * @author Damian
 */
public class BlankCommandException extends Exception {
    
    public BlankCommandException() {
        
        super( "Downloaded a command from the Client is blank !!!" );
    }
}
