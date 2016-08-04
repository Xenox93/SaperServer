package saperserver.Controller.Network;

import com.google.gson.Gson;
import java.io.EOFException;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import saperserver.Controller.Network.Exceptions.BlankCommandException;

import saperserver.Controller.Network.Interpreter.Interpreter;
import saperserver.Controller.Network.Interpreters.LoginInterpreter;
import saperserver.Controller.Network.Requests.LoginNetRequest;

/**
 * @author Damian
 */
public class Client {
    
    private Socket socket;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private Interpreter interpreter = new LoginInterpreter( this );
    
    //==========================================================================

    public Client( Socket socket ) throws Exception {
        
        this.socket = socket;
        
        try {
            
            out = new ObjectOutputStream( this.socket.getOutputStream() );
            in = new ObjectInputStream( this.socket.getInputStream() );
        }
        catch( Exception e ) {
            
            e.printStackTrace();
            disconnect();
            
            throw e;
        }
        
        getMsgs();
    }
    
    //==========================================================================
    
    public final void disconnect() {
        
        try {
            socket.close();
        }
        catch( Exception e ) {
        }
    }
    public boolean isDisconnected() {
        return socket.isClosed();
    }
    
    //==========================================================================
    
    public void sendMsg( NetRequest msg ) throws Exception {
        
        try {
            
            out.writeObject( new Gson().toJson( msg ) );
            out.flush();
        }
        catch( Exception e ) {
            
            e.printStackTrace();
            disconnect();
            
            throw e;
        }
    }
    
    //--------------------------------------------------------------------------
    
    private void getMsgs() {
        
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                
                while( true )
                {
                    try {
                        
                        interpreter.exec( getMsg() );
                        
                    } catch( BlankCommandException e ) {
                    } catch( Exception e ) {

                        disconnect();
                        interrupt();
                    }
                }
            }
        };
        
        thread.start();
    }
    private NetRequest getMsg() throws Exception {
        
        try {
            
            String request = in.readObject().toString();
        
            if( request.isEmpty() )
                throw new BlankCommandException();

            System.out.println( request );

            return new Gson().fromJson( request, LoginNetRequest.class );
        
        } catch( java.io.EOFException e ) {
            
            throw new BlankCommandException();
        }
    }
}
