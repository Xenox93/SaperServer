package saperserver.Model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Damian
 */
public abstract class DBRequest {
    
    private String query;
    
    //==========================================================================
    
    public void setQuery( final String query ) {
        this.query = query;
    }
    public final String getQuery() {
        return query;
    }
    
    //==========================================================================
    
    public void exec( final Statement s ) throws SQLException {
        
        getRows( s.executeQuery( query ) );
    }
    
    //==========================================================================
    
    protected abstract void parseRow( final ResultSet resultset ) throws SQLException;
    
    //==========================================================================
    
    private void getRows( final ResultSet resultset ) throws SQLException {
        
        while( resultset.next() )
            parseRow( resultset );
    }
}
