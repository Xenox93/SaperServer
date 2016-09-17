package saperserver.Model.Database.Requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import saperserver.Model.Account;
import saperserver.Model.Database.*;

public class LoginDBRequest extends DBRequest {
    
    private Account account;
    
    //==========================================================================
    
    public LoginDBRequest( Account account ) {
        
        //setQuery( "SELECT * FROM Account" );
        
        this.account = account;
        
        StringBuilder sb = new StringBuilder();
        setQuery( sb.append( "SELECT login, password FROM Account WHERE login='" ).append( account.getLogin() ).append( "' AND password='" ).append( account.getPassword() ).append( "'").toString() );
    }

    @Override
    protected void parseRow( final ResultSet resultset ) throws SQLException {
        
        if( !account.getLogin().equals( resultset.getString( "login" ) ) || !account.getPassword().equals( resultset.getString( "password" ) ) ) 
            account.setError( "IncorrectLoginDataException" );
        else
            account.setPassword( "" );
        
        /*while( resultset.next() )
            System.out.println( resultset.getString( "login" ) + " " + resultset.getString( "password" ) );*/
    }
}
