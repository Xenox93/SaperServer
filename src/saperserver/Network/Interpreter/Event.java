package saperserver.Network.Interpreter;

import saperserver.Network.Client;
import saperserver.Network.NetRequest;

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
    
    public void forward( final NetRequest command ) throws Exception {
        
        if( event != null )
            event.handle( command );
    }
    
    //==========================================================================
    
    public abstract void handle( final NetRequest command ) throws Exception;
}
