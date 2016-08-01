package saperserver.Controller.Network.Requests;

import saperserver.Controller.Network.Request;

/**
 * @author Damian
 */
public class LoginRequest extends Request {
    
    public LoginRequest( String login, String password ) {
        
        service = "login";
        //content = login + password;
    }
}
