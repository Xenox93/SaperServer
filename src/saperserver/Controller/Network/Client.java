package saperserver.Controller.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import saperserver.Controller.Network.Exceptions.BlankCommandException;

/**
 * @author Damian
 */
public class Client {
    
    private Socket socket;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    //==========================================================================

    public Client( String address, int port )
    {
        try
        {
            socket = new Socket( InetAddress.getByName( address ), port );
            
            in = new ObjectInputStream( socket.getInputStream() );
            out = new ObjectOutputStream( socket.getOutputStream() );
        }
        catch( IOException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    
    public Client( Socket socket )
    {
        this.socket = socket;
        
        try
        {
            out = new ObjectOutputStream( this.socket.getOutputStream() );
            in = new ObjectInputStream( this.socket.getInputStream() );
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
            socket.close();
        }
        catch( IOException e )
        {
            e.getStackTrace();
        }
    }
    public boolean isDisconnected() {
        return socket.isClosed();
    }
    
    //==========================================================================
    
    public void sendMsg( String msg )
    {
        try
        {
            out.writeObject( msg );
            out.flush();
        }
        catch( IOException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    private String getMsg() throws BlankCommandException
    {
        try
        {
           if( !socket.isClosed() )
               return in.readObject().toString();
        }
        catch( IOException | ClassNotFoundException e )
        {
            e.getStackTrace();
            disconnect();
        }
        
        throw new BlankCommandException();
    }
}
