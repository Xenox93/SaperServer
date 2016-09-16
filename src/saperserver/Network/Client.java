package saperserver.Network;

import com.google.gson.Gson;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import org.json.JSONObject;

import saperserver.Exceptions.BlankCommandException;
import saperserver.Network.Interpreter.Interpreter;

/**
 * @author Damian
 */
public class Client {
    
    private Socket socket = null;
    
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    
    private Interpreter interpreter = null;
    
    //==========================================================================

    public Client( Socket socket ) throws Exception {
        
        try {
            
            this.socket = socket;
            
            out = new ObjectOutputStream( this.socket.getOutputStream() );
            in = new ObjectInputStream( this.socket.getInputStream() );
            
            if( interpreter == null )
                interpreter = new Interpreter( this );
            
            getMsgs();
        }
        catch( Exception e ) {
            
            e.printStackTrace();
            disconnect();
            
            throw e;
        }
    }
    
    //==========================================================================
    
    public final void disconnect() {
        
        try {
            
            if( out != null ) {
                out.close();
                out = null;
            }
            
            if( in != null ) {
                in.close();
                in = null;
            }
            
            if( interpreter != null )
                interpreter = null;
            
            if( socket != null ) {
                socket.close();
                socket = null;
            }
            
            System.out.println( "Client disconnected !!!" );
        }
        catch( Throwable e ) {
            e.printStackTrace();
        }
    }
    public boolean isDisconnected() {
        
        return (socket != null) ? socket.isClosed() : true;
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
        
        (new Thread() {
            
            @Override
            public void run() {
                
                try {
                    
                    while( true )
                        interpreter.exec( getMsg() );
                
                } catch( BlankCommandException ex ) {
                } catch( Exception ex ) {
                    ex.printStackTrace();
                    disconnect();
                    interrupt();
                }
            }
        }).start();
    }
    private NetRequest getMsg() throws Exception {
        
        String request = in.readObject().toString();
        
        if( request.isEmpty() )
            throw new BlankCommandException();

        System.out.println( request );

        JSONObject obj = new JSONObject( request );
        
        String service = obj.getString( "header" );
        String content = obj.getString( "data" );
          
        if( service.isEmpty() || content.isEmpty() )
            throw new BlankCommandException();
        
        return new NetRequest( service, content );
    }
}
