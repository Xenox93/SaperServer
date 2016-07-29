package saperserver.Model.Database.Requests;

import java.sql.ResultSet;
import java.sql.SQLException;

import saperserver.Model.Account;
import saperserver.Model.Database.*;

public class LoginRequest extends Request {
    
    private Account account;
    
    //==========================================================================
    
    public LoginRequest( Account account, final String login, final String password ) {
        
        this.account = account;
        
        StringBuilder sb = new StringBuilder();
        setQuery( sb.append( "SELECT login FROM Account WHERE login='" ).append( login ).append( "' AND password='" ).append( password ).toString() );
    }

    @Override
    protected void parseRow( final ResultSet resultset ) throws SQLException {
        
        account.setLogin( resultset.getString( "login" ) );
    }
}
