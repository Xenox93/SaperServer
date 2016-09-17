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