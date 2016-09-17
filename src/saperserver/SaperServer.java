package saperserver;

import saperserver.Network.Server;

public class SaperServer {
    
    public static final String db_url = "jdbc:sqlite:database.db";
    public static final int SERVER_PORT = 5252;
    
    //==========================================================================
    
    public static void main( String[] args ) throws Exception {
        
        //Database.request( new GetRankingDBRequest( new Ranking() ) );
        //Database.request( new RankingDBRequest( new Account( "xenox93", "xenox93" ) ) );
        
        new Server( SERVER_PORT );
    }
}
/*setQuery( "CREATE TABLE Ranking (" + 
                  " id UNSIGNED INT AUTO_INCREMENT PRIMARY KEY," +
                  " login TEXT UNIQUE NOT NULL references Account(login)," +
                  " amount_of_games UNSIGNED INT DEFAULT '0'," +
                  " amount_of_winnings UNSIGNED INT DEFAULT '0'" +
                  " )" );*/
        //setQuery( "drop table Account" );
        //setQuery( "ALTER TABLE Account ALTER COLUMN ID UNSIGNED INT AUTO_INCREMENT PRIMARY KEY UNIQUE NULL" );
        
        