package saperserver.Model;

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
}
