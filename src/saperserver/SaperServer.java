package saperserver;

import saperserver.Network.Server;

public class SaperServer {
    
    public static final String db_url = "jdbc:sqlite:database.db";
    public static final int SERVER_PORT = 5252;
    
    //==========================================================================
    
    public static void main( String[] args ) throws Exception {
        
        new Server( SERVER_PORT );
    }
}       