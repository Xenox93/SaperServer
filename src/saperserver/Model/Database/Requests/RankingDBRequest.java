package saperserver.Model.Database.Requests;

import saperserver.Model.Account;
import saperserver.Model.Database.UpdateRequest;

/**
 *
 * @author Damian
 */
public class RankingDBRequest  extends UpdateRequest {
    
    public RankingDBRequest( Account account ) {
        
        StringBuilder sb = new StringBuilder();
        
        setQuery( sb.append( "INSERT INTO Ranking (login) VALUES ('" ).append( account.getLogin() ).append( "')" ).toString() );
    }
    public RankingDBRequest( Account account, boolean is_winner ) {
        
        StringBuilder sb = new StringBuilder();
        
        if( is_winner )
            sb = sb.append( "UPDATE Ranking SET amount_of_games=amount_of_games+1, amount_of_winnings=amount_of_winnings+1 WHERE login='" ).append( account.getLogin() ).append( "' )" );
        else
            sb = sb.append( "UPDATE Ranking SET amount_of_games=amount_of_games+1 WHERE login='" ).append( account.getLogin() ).append( "' )" );
        
        setQuery( sb.toString() );
    }
}
