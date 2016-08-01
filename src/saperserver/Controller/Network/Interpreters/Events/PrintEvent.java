package saperserver.Controller.Network.Interpreters.Events;

import saperserver.Controller.Network.Client;
import saperserver.Controller.Network.Interpreter.Event;
import saperserver.Controller.Network.Request;
import saperserver.Controller.Network.Requests.LoginRequest;

/**
 * @author Damian
 */
public class PrintEvent extends Event
{
    public PrintEvent( Client client )
    {
        super( client );
    }
    
    //==========================================================================
    
    @Override
    public void handle( Request command )
    {
        if( command.getService().equals( "login" ) ) {
            
            client.sendMsg( new LoginRequest("", "") );
            System.out.println( "login" );
        }
        else
            forward( command );
    }
}
