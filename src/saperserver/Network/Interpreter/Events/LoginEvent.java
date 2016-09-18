package saperserver.Network.Interpreter.Events;

import com.google.gson.Gson;

import saperserver.Network.Client;
import saperserver.Network.Interpreter.Event;
import saperserver.Network.NetRequest;

import saperserver.Model.Account;
import saperserver.Model.Database.Database;
import saperserver.Model.Database.Requests.LoginDBRequest;

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
        
        if( command.getHeader().equals( "login" ) ) {
            
            Account account = new Gson().fromJson( command.getData(), Account.class );
            
            // Sprawdzenie w BD czy takie konto istnieje - LoginDBRequest !!!
            Database.request( new LoginDBRequest( account ) );
            
            if( !account.getPassword().equals( "" ) )
                account.setError( "IncorrectLoginDataException" );
            else
                client.getAccount().setLogin( account.getLogin() );
            
            account.setPassword( "" );
            
            client.sendMsg( new NetRequest( "login", new Gson().toJson( account, Account.class ) ) );
        }
        else
            forward( command );
    }
}
