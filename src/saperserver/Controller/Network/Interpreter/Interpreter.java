package saperserver.Controller.Network.Interpreter;

import saperserver.Controller.Network.Request;

/**
 * @author Damian
 */
public abstract class Interpreter
{
    protected Event event;
    
    //==========================================================================
    
    public Interpreter() {
    }
    public Interpreter( Interpreter interpreter ) {
        
        event = interpreter.getEvent();
    }
    
    //==========================================================================
    
    public Event getEvent() {
        
        return event;
    }
    
    //==========================================================================
    
    public void exec( final Request command ) {
        event.handle( command );
    }
}
