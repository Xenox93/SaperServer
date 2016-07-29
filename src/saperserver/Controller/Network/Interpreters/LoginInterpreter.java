package saperserver.Controller.Network.Interpreters;

import saperserver.Controller.Network.Interpreter.Interpreter;
import saperserver.Controller.Network.Interpreters.Events.PrintEvent;

/**
 * @author Damian
 */
public class LoginInterpreter extends Interpreter {
    
    public LoginInterpreter() {
        
        event = new PrintEvent();
    }
}
