package saperserver.Controller.Network.Interpreter;

import saperserver.Controller.Network.Client;
import saperserver.Controller.Network.Request;

/**
 * @author Damian
 */
public abstract class Event
{
    private Event event = null;
    protected Client client;
    
    //==========================================================================
    
    public Event( Client client ) {
        
        this.client = client;
    }
    
    //==========================================================================
    
    public final Event add( Event event ) {
        
        this.event = event;
        
        return this.event;
    }
    public final Event get() {
        
        return event;
    }
    
    //==========================================================================
    
    public void forward( final Request command ) {
        
        if( event != null )
            event.handle( command );
    }
    
    //==========================================================================
    
    public abstract void handle( final Request command );
}
