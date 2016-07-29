package saperserver.Model.Database;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author Damian
 */
public class Database {
    
    private static final String url = "jdbc:oracle:thin:@localhost:1521:ORA2015";
    private static final String user = "GR3_faku";
    private static final String password = "zaq1";
    
    //==========================================================================
    
    public void request( final Request request ) throws SQLException {
        
        final Connection db = connect();
        
            final Statement s = db.createStatement();
            
                request.exec( s );
                
            s.close();
            
        db.close();
    }
    
    //--------------------------------------------------------------------------
    
    private final Connection connect() throws SQLException {
        
        return DriverManager.getConnection( url, user, password );
    }
}
