package saperserver.Controller.Network.Interpreter;

/**
 * @author Damian
 */
public abstract class Interpreter
{
    protected Event event;
    
    //==========================================================================
    
    public void exec( final String command )
    {
        event.handle( command );
    }
}
