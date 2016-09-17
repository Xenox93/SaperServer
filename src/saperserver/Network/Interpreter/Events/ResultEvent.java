package saperserver.Network.Interpreter.Events;

import saperserver.Model.Database.Requests.GetRankingDBRequest;
import saperserver.Model.Database.Database;

import saperserver.Network.Interpreter.Event;
import saperserver.Network.NetRequest;

import saperserver.Network.Client;

import com.google.gson.Gson;
import saperserver.Model.Ranking;

/**
 *
 * @author Damian
 */
public class ResultEvent extends Event
{
    public ResultEvent( Client client ) {
        
        super( client );
    }
    
    /**
     * @param command
     * @throws Exception
     */
    @Override
    public void handle( NetRequest command ) throws Exception {
        
        if( command.getHeader().equals( "ranking" ) ) {
            
            System.out.println( "Ranking" );
            
            Ranking ranking = new Ranking();
            
            // Get results from Database !!!
            Database.request( new GetRankingDBRequest( ranking ) );
            
            client.sendMsg( new NetRequest( "ranking", new Gson().toJson( ranking, Ranking.class ) ) );
        }
        else
            forward( command );
    }
}