package saperserver.Model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Damian
 */
public class UpdateRequest extends Request {
    
    @Override
    public void exec( final Statement s ) throws SQLException {
        
        s.executeUpdate( getQuery() );
    }

    @Override
    protected void parseRow( final ResultSet resultset ) throws SQLException {
    }
}
