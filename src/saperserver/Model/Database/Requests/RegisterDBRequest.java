package saperserver.Model.Database.Requests;

import saperserver.Model.Account;
import saperserver.Model.Database.*;

public class RegisterDBRequest extends UpdateRequest {
    
    public RegisterDBRequest( Account account ) {
        
        StringBuilder sb = new StringBuilder();
        
        setQuery( sb.append( "INSERT INTO Account (login, password) VALUES ('" ).append( account.getLogin() ).append( "','" ).append( account.getPassword() ).append( "')" ).toString() );
    }
}