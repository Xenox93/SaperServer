package saperserver.Network.Interpreter.Events;

import saperserver.Network.Client;
import saperserver.Network.Interpreter.Event;
import saperserver.Network.NetRequest;

/**
 *
 * @author Damian
 */
public class PrepareBoardEvent extends Event
{
    public PrepareBoardEvent( Client client ) {
        
        super( client );
    }
    
    /**
     * @param command
     * @throws Exception
     */
    @Override
    public void handle( NetRequest command ) throws Exception {
        
        if( command.getHeader().equals( "prepare_board" ) ) {
            
            client.sendMsg( new NetRequest( "get_board", "" ) );
        }
        else
            forward( command );
    }
}