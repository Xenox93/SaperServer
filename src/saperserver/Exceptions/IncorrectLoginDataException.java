package saperserver.Exceptions;

/**
 * @author Damian
 */
public class IncorrectLoginDataException extends Exception {
    
    public IncorrectLoginDataException() {
        
        super( "Login and/or password are incorrect !!!" );
    }
}