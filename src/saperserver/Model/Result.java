package saperserver.Model;

/**
 *
 * @author Damian
 */
public class Result {
    
    private final String login;
    private final int amount_of_games;
    private final int amount_of_winners;
    
    public Result( String login, int amount_of_games, int amount_of_winners ) {
        
        this.login = login;
        this.amount_of_games = amount_of_games;
        this.amount_of_winners = amount_of_winners;
    }
    
    //==========================================================================
    
    public final String getLogin() {
        
        return login;
    }
    public final int getAmountOfGames() {
        
        return amount_of_games;
    }
    public final int getAmountOfWinners() {
        
        return amount_of_winners;
    }
}
