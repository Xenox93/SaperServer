package saperserver.Model;

/**
 * @author Damian
 */
public class Error {
    
    protected String error;
    
    //==========================================================================
    
    protected boolean isError() {
        
        return !error.isEmpty();
    }
    
    //==========================================================================
    
    public void setError( String error ) {
        
        this.error = error;
    }
    
    //--------------------------------------------------------------------------
    
    public void checkError() throws Exception {
    }
}
