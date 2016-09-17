package saperserver.Model.Database.Requests;

import saperserver.Model.Account;
import saperserver.Model.Database.*;

public class RegisterDBRequest extends UpdateRequest {
    
    public RegisterDBRequest( Account account ) {
        
        //setQuery( "CREATE TABLE Account ( ID UNSIGNED INT AUTO_INCREMENT PRIMARY KEY UNIQUE NULL, login TEXT NOT NULL, password TEXT NOT NULL )" );
        //setQuery( "drop table Account" );
        //setQuery( "ALTER TABLE Account ALTER COLUMN ID UNSIGNED INT AUTO_INCREMENT PRIMARY KEY UNIQUE NULL" );
        
        StringBuilder sb = new StringBuilder();
        
        setQuery( sb.append( "INSERT INTO Account (login, password) VALUES ('" ).append( account.getLogin() ).append( "','" ).append( account.getPassword() ).append( "')" ).toString() );
    }
}