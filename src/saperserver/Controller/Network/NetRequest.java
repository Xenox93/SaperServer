package saperserver.Controller.Network;

/**
 * @author Damian
 * @param <T>
 */
public class NetRequest<T> {
    
    protected String service;
    protected T content;
    
    //==========================================================================
    
    public String getService() {
    
        return service;
    }
    public T getContent() {
    
        return content;
    }
    
    //==========================================================================
    
    @Override
    public String toString() {
        
        return new StringBuilder( "service:" ).append( service ).append( ",content:" ).append( content ).toString();
    }
}
