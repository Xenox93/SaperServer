package saperserver.Network;

/**
 * @author Damian
 * @param <T>
 */
public class NetRequest {
    
    protected String header;
    protected String data;
    
    //==========================================================================
    
    public NetRequest( String header, String data ) {
        
        this.header = header;
        this.data = data;
    }
    
    //==========================================================================
    
    public final String getHeader() {
    
        return header;
    }
    public final String getData() {
    
        return data;
    }
}
