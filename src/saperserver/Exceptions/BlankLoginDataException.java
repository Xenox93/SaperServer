package saperserver.Exceptions;

/**
 * @author Damian
 */
public class BlankLoginDataException extends Exception {
    
    public BlankLoginDataException() {
        
        super( "Login and/or password fields are empty !!!" );
    }
}
