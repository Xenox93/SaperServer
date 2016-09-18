package saperserver.Model;

import java.util.ArrayList;
import java.util.List;

import saperserver.Exceptions.LossException;
import saperserver.Exceptions.WinException;

/**
 *
 * @author Damian
 */
public class Board {
    
    private final List< List< Integer > > fields = new ArrayList<>();
    private final List< List< Integer > > output_fields = new ArrayList<>();
    
    //==========================================================================
    
    public void createBoard( Level level ) {
        
        fields.clear();
        output_fields.clear();
        
        for( int row = 0; row < level.getRows(); ++row ) {
            
            fields.add( new ArrayList<>() );
            output_fields.add( new ArrayList<>() );
            
            for( int col = 0; col < level.getCols(); ++col ) {
                
                fields.get( row ).add( row );
                output_fields.get( row ).add( -1 );
            }
        }
    }
    
    //--------------------------------------------------------------------------
    
    public void checkField( int row, int col ) throws WinException, LossException {
        
        if( fields.get( row ).get( col ).equals( 9 ) )
            throw new LossException();
        
        output_fields.get( row ).set( col, fields.get( row ).get( col ) );
        
        for( int r = 0; r < output_fields.size(); ++r )
            for( int c = 0; c < output_fields.get( r ).size(); ++c )
                if( output_fields.get( r ).get( c ).equals( -1 ) )
                    return;
        
        throw new WinException();
    }
    
    public final List< List< Integer > > getFields() {
        
        return fields;
    }
    public final List< List< Integer > > getOutputFields() {
        
        return output_fields;
    }
}