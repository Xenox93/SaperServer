package saperserver.Network.Interpreter.Events;

import com.google.gson.Gson;
import saperserver.Model.Board;
import saperserver.Model.Level;
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
            
            Level level = new Gson().fromJson( command.getData(), Level.class );
            
            client.getBoard().createBoard( level );
            
            client.sendMsg( new NetRequest( "get_board", new Gson().toJson( client.getBoard(), Board.class ) ) );
        }
        else
            forward( command );
    }
}