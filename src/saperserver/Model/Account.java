package saperserver.Model;

import saperserver.Model.Exceptions.Account.IncorrectLoginDataException;

import saperserver.Controller.Network.Error;

/**
 * @author Damian
 */
public class Account extends Error {
    
    private String login;
    private String password;
    
    //==========================================================================
    
    public Account() {
        
        this.error = "";
        this.login = "";
        this.password = "";
    }
    public Account( String login, String password ) {
        
        this.error = "";
        this.login = login;
        this.password = password;
    }
    public Account( Account account ) {
        
        login = account.getLogin();
        password = account.getPassword();
    }
    
    //--------------------------------------------------------------------------
    
    public void setLogin( String login ) {
        
        this.login = login;
    }
    public void setPassword( String password ) {
        
        this.password = password;
    }
    
    public final String getLogin() {
        return login;
    }
    public final String getPassword() {
        return password;
    }
    
    //==========================================================================
    
    @Override
    public String toString() {
        
        return new StringBuilder( "login:" ).append( login ).append( ",password:" ).append( password ).append( ",error:" ).append( error ).toString();
    }

    /**
     *
     * @throws IncorrectLoginDataException
     */
    @Override
    public void checkError() throws IncorrectLoginDataException {
        
        if( !isError() )
            return;
        
        if( error.equals( "IncorrectLoginDataException" ) )
            throw new IncorrectLoginDataException();
    }
}
