package saperserver.Network;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damian
 */
public class Server
{
    private ServerSocket serverSocket;
    private List< Client > clients = new ArrayList<>();
    
    //==========================================================================
    
    public Server( int port ) {
        try
        {
            serverSocket = new ServerSocket( port );
            wait4Clients();
        }
        catch( Exception e )
        {
            e.printStackTrace();
            disconnect();
        }
    }
    
    //==========================================================================
    
    private void disconnect() {
        
        try
        {
            if( clients != null ) {
                
                for( Client c : clients )
                    c.disconnect();
                
                clients.clear();
                clients = null;
            }
            
            serverSocket.close();
            serverSocket = null;
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
    public boolean isDisconnected() {
        return serverSocket.isClosed();
    }
    
    //==========================================================================
    
    private void wait4Clients() {
        
        (new Thread() {
            
            @Override
            public void run() {
                
                Socket client_tmp = null;
                
                while( true ) {
                    
                    try {

                        cleanUpFromDisconnectedClients();
                        
                        client_tmp = serverSocket.accept();
                        if( client_tmp != null )
                            clients.add( new Client( client_tmp ) );

                    } catch( Exception e ) {

                        e.printStackTrace();
                        disconnect();
                        interrupt();
                    }
                }
            }
            
        }).start();
    }
    private void cleanUpFromDisconnectedClients() {
        
        for( Client client : clients )
            if( client.isDisconnected() )
                clients.remove( client );
    }
}