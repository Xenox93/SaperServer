package saperserver.Model;

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
    
    //--------------------------------------------------------------------------
    
    public void setLogin( final String login ) {
        
        this.login = login;
    }
    public final String getLogin() {
        return login;
    }
}
