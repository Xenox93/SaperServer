package saperserver.Controller.Network;

import java.io.IOException;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Damian
 */
public class Server
{
    private ServerSocket serverSocket;
    private final Map< InetAddress, Client > clients = new HashMap<>();
    
    //==========================================================================
    
    public Server( int port )
    {
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
    
    public void disconnect()
    {
        try
        {
            for( Map.Entry< InetAddress, Client > c : clients.entrySet() )
                c.getValue().disconnect();
            
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
    
    //--------------------------------------------------------------------------
            
    public void sendMsg( InetAddress address, String msg )
    {
        try
        {
            if( clients.containsKey( address ) )
                clients.get( address ).sendMsg( msg );
        }
        catch( NullPointerException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    
    //==========================================================================
    
    public void wait4Clients() {
        
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                
                Socket socket;
        
                while( true )
                {
                    try
                    {
                        socket = serverSocket.accept();
                        clients.put( socket.getInetAddress(), new Client( socket ) );
                    }
                    catch( IOException e )
                    {
                        e.getStackTrace();
                        disconnect();
                        interrupt();
                    }
                    finally {
                        
                        // Clean Up from a disconnected clients
                        for( Map.Entry< InetAddress, Client > client : clients.entrySet() )
                            if( client.getValue().isDisconnected() )
                                clients.remove( client.getKey() );
                        
                    }
                }
            }
        };
        
        thread.start();
    }
}