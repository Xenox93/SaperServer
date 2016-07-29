package saperserver;

import saperserver.Controller.Network.Server;

public class SaperServer {
    
    public static void main( String[] args ) {
        
        Server server = new Server( 5252 );
    }
}
