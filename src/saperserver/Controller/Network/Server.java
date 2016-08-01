package saperserver.Controller.Network;

import java.io.IOException;

import java.net.ServerSocket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damian
 */
public class Server
{
    private ServerSocket serverSocket;
    private final List< Client > clients = new ArrayList<>();
    
    //==========================================================================
    
    public Server( int port ) {
        try
        {
            serverSocket = new ServerSocket( port );
            wait4Clients();
        }
        catch( IOException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    
    //==========================================================================
    
    private void disconnect() {
        try
        {
            for( Client c : clients )
                c.disconnect();
            
            clients.clear();
            serverSocket.close();
        }
        catch( IOException e )
        {
            e.getStackTrace();
        }
    }
    public boolean isDisconnected() {
        return serverSocket.isClosed();
    }
    
    //==========================================================================
    
    private void wait4Clients() {
        
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                
                while( true )
                {
                    try
                    {
                        clients.add( new Client( serverSocket.accept() ) );
                    }
                    catch( IOException e )
                    {
                        e.getStackTrace();
                        disconnect();
                        interrupt();
                    }
                    
                    cleanUpFromDisconnectedClients();
                }
            }
        };
        
        thread.start();
    }
    private void cleanUpFromDisconnectedClients() {
        
        for( Client client : clients )
            if( client.isDisconnected() )
                clients.remove( client );
    }
}