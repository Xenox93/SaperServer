package saperserver.Model.Database.Requests;

import saperserver.Exceptions.BlankLoginDataException;

import saperserver.Model.Database.*;

public class RegisterRequest extends UpdateRequest {
    
    public RegisterRequest( final String login, final String password ) throws BlankLoginDataException {
        
        StringBuilder sb = new StringBuilder();
        
        //setQuery( "CREATE TABLE Account ( ID UNSIGNED INT AUTO_INCREMENT PRIMARY KEY UNIQUE NULL, login TEXT NOT NULL, password TEXT NOT NULL )" );
        //setQuery( "drop table Account" );
        //setQuery( "ALTER TABLE Account ALTER COLUMN ID UNSIGNED INT AUTO_INCREMENT PRIMARY KEY UNIQUE NULL" );
        setQuery( sb.append( "INSERT INTO Account (login, password) VALUES ('" ).append( login ).append( "','" ).append( password ).append( "')" ).toString() );
    }
}