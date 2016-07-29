package saperserver.Model;

import java.sql.SQLException;

import saperserver.Model.Database.*;
import saperserver.Model.Database.Requests.*;

import saperserver.Model.Exceptions.Account.*;

/**
 * @author Damian
 */
public class Account {
    
    private String login;
    
    //==========================================================================
    
    public Account() {
    }
    public Account( final String login ) {
        
        this.login = login;
    }
    public Account( final Account account ) {
        
        login = account.getLogin();
    }
    
    //==========================================================================
    
    public void login( final String login, final String password ) throws BlankLoginDataException, IncorrectLoginDataException {
        
        if( login.isEmpty() || password.isEmpty() )
            throw new BlankLoginDataException();
        
        try {
            
            Database db = new Database();
            db.request( new LoginRequest( this, login, password ) );
            
        } catch( SQLException ex ) {
            throw new IncorrectLoginDataException();
        }
    }
    public void register( final String login, final String password ) throws BlankLoginDataException, IncorrectLoginDataException {
        
        if( login.isEmpty() || password.isEmpty() )
            throw new BlankLoginDataException();
        
        Database db = new Database();
        
        try {
            
            db.request( new LoginRequest( this, login, password ) );
            
        } catch( SQLException ex ) {
            
            try {
                
                db.request( new RegisterRequest( login, password ) );
                db.request( new LoginRequest( this, login, password ) );

            } catch( SQLException e ) {
                throw new IncorrectLoginDataException();
            }
        }
    }
    
    //--------------------------------------------------------------------------
    
    public void setLogin( final String login ) {
        
        this.login = login;
    }
    public final String getLogin() {
        return login;
    }
}
