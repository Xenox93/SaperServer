package saperserver.Network.Interpreter.Events;

import saperserver.Network.Client;
import saperserver.Network.Interpreter.Event;
import saperserver.Network.NetRequest;

/**
 *
 * @author Damian
 */
public class CheckFieldEvent extends Event
{
    public CheckFieldEvent( Client client ) {
        
        super( client );
    }
    
    /**
     * @param command
     * @throws Exception
     */
    @Override
    public void handle( NetRequest command ) throws Exception {
        
        if( command.getHeader().equals( "check_field" ) ) {
            
            client.sendMsg( new NetRequest( "get_fields", "" ) );
        }
        else
            forward( command );
    }
}