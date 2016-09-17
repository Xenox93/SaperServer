package saperserver.Network.Interpreter.Events;

import com.google.gson.Gson;

import saperserver.Model.Account;

import saperserver.Model.Database.Database;
import saperserver.Model.Database.Requests.LoginDBRequest;
import saperserver.Model.Database.Requests.RankingDBRequest;
import saperserver.Model.Database.Requests.RegisterDBRequest;

import saperserver.Network.Client;
import saperserver.Network.Interpreter.Event;
import saperserver.Network.NetRequest;

/**
 *
 * @author Damian
 */
public class RegisterEvent extends Event {
    
    public RegisterEvent( Client client )
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
        
        if( command.getHeader().equals( "register" ) ) {
            
            Account input_account = new Gson().fromJson( command.getData(), Account.class );
            Account output_account = new Account( input_account );
            
            //------------------------------------------------------------------
            // Check in Database whether Account exists !!!
            //------------------------------------------------------------------
            Database.request( new LoginDBRequest( input_account ) );
            
            if( input_account.getPassword().equals( "" ) ) {
                output_account.setError( "AccountExistException" );
                client.sendMsg( new NetRequest( "register", new Gson().toJson( output_account, Account.class ) ) );
                return;
            }
            
            //------------------------------------------------------------------
            // Otherwise try register a new account
            //------------------------------------------------------------------
            try{
                
                Database.request( new RegisterDBRequest( output_account ) );
            
            } catch( Exception ex ) {
                
                ex.printStackTrace();
                output_account.setError( "AccountRegisterFailedException" );
                output_account.setPassword( "" );
                
                client.sendMsg( new NetRequest( "register", new Gson().toJson( output_account, Account.class ) ) );
                
                return;
            }
            
            //------------------------------------------------------------------
            
            output_account.setPassword( "" );
            
            // INSERT FIRST RECORD TO DATABASE
            Database.request( new RankingDBRequest( output_account ) );
            
            client.sendMsg( new NetRequest( "register", new Gson().toJson( output_account, Account.class ) ) );
        }
        else
            forward( command );
    }
}
