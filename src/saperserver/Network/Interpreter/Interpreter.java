package saperserver.Network.Interpreter;

import saperserver.Network.Interpreter.Events.*;
import saperserver.Network.NetRequest;
import saperserver.Network.Client;

/**
 * @author Damian
 */
public class Interpreter
{
    protected Event event;
    
    //==========================================================================
    
    public Interpreter( Client client ) {
        
        event = new LoginEvent( client );
        
        event.add( new RegisterEvent( client ) ).add( new ResultEvent( client ) ).add( new PrepareBoardEvent( client ) ).add( new CheckFieldEvent( client ) );
    }
    
    //==========================================================================
    
    public void exec( final NetRequest command ) throws Exception {
        
        event.handle( command );
    }
}
