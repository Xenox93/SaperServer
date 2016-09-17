package saperserver.Network.Interpreter;

import saperserver.Network.Client;
import saperserver.Network.Interpreter.Events.LoginEvent;
import saperserver.Network.Interpreter.Events.RegisterEvent;
import saperserver.Network.NetRequest;

/**
 * @author Damian
 */
public class Interpreter
{
    protected Event event;
    
    //==========================================================================
    
    public Interpreter( Client client ) {
        
        event = new LoginEvent( client );
        
        event.add( new RegisterEvent( client ) );
    }
    
    //==========================================================================
    
    public void exec( final NetRequest command ) throws Exception {
        
        event.handle( command );
    }
}
