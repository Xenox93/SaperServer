package saperserver.Controller.Network.Requests;

import saperserver.Controller.Network.NetRequest;

import saperserver.Model.Account;

/**
 * @author Damian
 */
public class LoginNetRequest extends NetRequest<Account> {
    
    public LoginNetRequest( Account account ) {
        
        service = "login";
        content = account;
    }
}
