package saperserver.Controller.Network.Interpreter;

/**
 * @author Damian
 */
public abstract class Event
{
    private Event event = null;
    
    //==========================================================================
    
    public void add( Event event )
    {
        this.event = event;
    }
    
    public final Event get()
    {
        return event;
    }
    
    //==========================================================================
    
    public void forward( final String command )
    {
        if( event != null )
            event.handle( command );
    }
    
    //==========================================================================
    
    public abstract void handle( final String command );
}
