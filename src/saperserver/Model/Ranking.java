package saperserver.Model;

import java.util.ArrayList;

public class Ranking
{
    private final ArrayList< Result > results = new ArrayList<>();
    
    //==========================================================================
    
    public void add( String login, int amount_of_games, int amount_of_winners ) {
        
        results.add( new Result( login, amount_of_games, amount_of_winners ) );
    }
    public final ArrayList< Result > getResults() {
        
        return results;
    }
}
