package saperserver.Controller.Network;

/**
 * @author Damian
 */
public class Request {
    
    protected String service;
    protected String content;
    
    //==========================================================================
    
    public Request() {
    }
    public Request( String request ) {
        
        service = request;
    }
    
    //==========================================================================
    
    public final String getService() {
        
        return service;
    }
    public final String getContent() {
        
        return content;
    }
    
    //==========================================================================
    
    @Override
    public String toString() {
        
        return service;
    }
}
