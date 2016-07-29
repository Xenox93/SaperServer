package saperserver.Model.Database.Requests;

import saperserver.Model.Database.*;
import saperserver.Model.Exceptions.Account.*;

public class RegisterRequest extends UpdateRequest {
    
    public RegisterRequest( final String login, final String password ) throws BlankLoginDataException {
        
        StringBuilder sb = new StringBuilder();
        setQuery( sb.append( "INSERT INTO Account (login, password) VALUES ('" ).append( login ).append( "','" ).append( password ).append( "')" ).toString() );
    }
}