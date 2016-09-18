package saperserver.Network.Interpreter.Events;

import com.google.gson.Gson;
import org.json.JSONObject;

import saperserver.Exceptions.LossException;
import saperserver.Exceptions.WinException;

import saperserver.Model.Board;

import saperserver.Model.Database.Database;
import saperserver.Model.Database.Requests.RankingDBRequest;
import saperserver.Model.Level;

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
            
            JSONObject object = new JSONObject( command.getData() );
            
                try {
                    
                    client.getBoard().checkFields( object.getInt( "row" ), object.getInt( "col" ) );
                    
                } catch( LossException ex ) {
                    
                    client.sendMsg( new NetRequest( "loss", "" ) );
                    client.getBoard().createBoard( new Level(0,0,0) );
                    
                    Database.request( new RankingDBRequest( client.getAccount(), false ) );
                    
                    return;
                    
                } catch( WinException ex ) {
                    
                    client.sendMsg( new NetRequest( "win", "" ) );
                    client.getBoard().createBoard( new Level(0,0,0) );
                    
                    Database.request( new RankingDBRequest( client.getAccount(), true ) );
                    
                    return;
                }
            
            client.sendMsg( new NetRequest( "get_field", new Gson().toJson( client.getBoard(), Board.class ) ) );
        }
        else
            forward( command );
    }
}