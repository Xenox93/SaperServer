package saperserver.Controller.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import saperserver.Controller.Network.Exceptions.BlankCommandException;

import saperserver.Controller.Network.Interpreter.Interpreter;
import saperserver.Controller.Network.Interpreters.LoginInterpreter;

/**
 * @author Damian
 */
public class Client {
    
    private Socket socket;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Interpreter interpreter = new LoginInterpreter( this );
    
    //==========================================================================

    public Client( Socket socket ) {
        
        this.socket = socket;
        
        try
        {
            out = new ObjectOutputStream( this.socket.getOutputStream() );
            in = new ObjectInputStream( this.socket.getInputStream() );
            
            getMsgs();
        }
        catch( IOException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    
    //==========================================================================
    
    public final void disconnect() {
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
    
    public void sendMsg( Request msg ) {
        try
        {
            out.writeObject( msg.toString() );
            out.flush();
        }
        catch( IOException e )
        {
            e.getStackTrace();
            disconnect();
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void getMsgs() {
        
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                
                while( true )
                {
                    try
                    {
                        interpreter.exec( getMsg() );
                    }
                    catch( BlankCommandException e )
                    {
                        e.getStackTrace();
                        disconnect();
                        interrupt();
                    }
                }
            }
        };
        
        thread.start();
    }
    private Request getMsg() throws BlankCommandException {
        
        try
        {
           if( !socket.isClosed() ) {
               
               String request = in.readObject().toString();
               
               if( request.isEmpty() )
                   throw new BlankCommandException();
               
               return new Request( request );
           }
        }
        catch( IOException | ClassNotFoundException e )
        {
            e.getStackTrace();
            disconnect();
        }
        
        throw new BlankCommandException();
    }
}
