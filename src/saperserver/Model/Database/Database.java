package saperserver.Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import saperserver.SaperServer;

/**
 * @author Damian
 */
public class Database {
    
    public static synchronized void request( final DBRequest request ) throws Exception {
        
        final Connection db = connect();
        
            final Statement s = db.createStatement();
            
                request.exec( s );
                
            s.close();
            
        db.close();
    }
    
    //--------------------------------------------------------------------------
    
    private static final Connection connect() throws Exception {
        
        Class.forName( "org.sqlite.JDBC" );
        
        return DriverManager.getConnection( SaperServer.db_url );
    }
}
