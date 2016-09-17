package saperserver;

import saperserver.Model.Account;

import saperserver.Model.Database.Database;
import saperserver.Model.Database.Requests.LoginDBRequest;
import saperserver.Network.Server;

public class SaperServer {
    
    public static final String db_url = "jdbc:sqlite:database.db";
    public static final int SERVER_PORT = 5252;
    
    //==========================================================================
    
    public static void main( String[] args ) throws Exception {
        
        //Database.request( new LoginDBRequest( new Account() ) );
        //Database.request( new RegisterDBRequest( new Account() ) );
        
        new Server( SERVER_PORT );
    }
}
