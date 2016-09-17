package saperserver.Model.Database.Requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import saperserver.Model.Database.DBRequest;
import saperserver.Model.Ranking;

/**
 *
 * @author Damian
 */
public class GetRankingDBRequest extends DBRequest {
    
    private Ranking ranking;
    
    //==========================================================================
    
    public GetRankingDBRequest( Ranking ranking ) {
        
        this.ranking = ranking;
        
        setQuery( "SELECT login, amount_of_games, amount_of_winnings FROM Ranking" );
    }
    
    //==========================================================================

    @Override
    protected void parseRow( ResultSet resultset ) throws SQLException {
        
        for( ; !resultset.isAfterLast(); resultset.next() )
            ranking.add( resultset.getString( "login" ), resultset.getInt( "amount_of_games" ), resultset.getInt( "amount_of_winnings" ) );
    }
}
