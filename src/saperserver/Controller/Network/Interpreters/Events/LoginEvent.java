package saperserver.Controller.Network.Interpreters.Events;

import saperserver.Controller.Network.Client;
import saperserver.Controller.Network.Interpreter.Event;
import saperserver.Controller.Network.NetRequest;
import saperserver.Controller.Network.Requests.LoginNetRequest;
import saperserver.Model.Account;

/**
 * @author Damian
 */
public class LoginEvent extends Event
{
    public LoginEvent( Client client )
    {
        super( client );
    }
    
    //==========================================================================

    /**
     * @param command
     * @throws Exception
     */
    @Override
    public void handle( NetRequest command ) throws Exception {
        
        if( command.getService().equals( "login" ) ) {
            
            Account account = (Account)( command.getContent() );
            LoginNetRequest request = new LoginNetRequest( account );
            //LoginNetRequest request = new LoginNetRequest( new Account( "", "" ) );
            
            // Sprawdzenie w BD czy takie konto istnieje - LoginDBRequest !!!
            if( !account.getLogin().equals( "1" ) || !account.getPassword().equals( "1" ) )
                account.setError( "IncorrectLoginDataException" );
            
            client.sendMsg( request );
        }
        else
            forward( command );
    }
}
