package saperserver.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import saperserver.Exceptions.LossException;
import saperserver.Exceptions.WinException;

/**
 *
 * @author Damian
 */
public class Board {
    
    private List< List< Integer > > fields = new ArrayList<>();
    private List< List< String > > output_fields = new ArrayList<>();
    
    private int amount_of_mines = 0;
    
    //==========================================================================
    
    public void createBoard( Level level ) {
        
        prepareBoard( level );
        setMines( level );
    }
    
    //--------------------------------------------------------------------------
    
    public void checkFields( int row, int col ) throws WinException, LossException {
        
        if( fields.get( row ).get( col ).equals( 9 ) )
            throw new LossException();
        
        checkField( row, col );
        
        if( isFinish() )
            throw new WinException();
    }
    
    public final List< List< Integer > > getFields() {
        
        return fields;
    }
    public final List< List< String > > getOutputFields() {
        
        return output_fields;
    }
    
    //==========================================================================
    
    private boolean isFinish() {
        
        int amount_undiscovered_fields = 0;
        
        for( int r = 0; r < output_fields.size(); ++r )
            for( int c = 0; c < output_fields.get( r ).size(); ++c )
                if( output_fields.get( r ).get( c ).equals( "" ) )
                    ++amount_undiscovered_fields;
        
        if( amount_undiscovered_fields == amount_of_mines )
            return true;
        else
            return false;
    }
    private void checkField( int row, int col ) {
        
        // Range beyond board
        if( row < 0 || row > fields.size()-1 )
            return;
        if( col < 0 || col > fields.get( row ).size()-1 )
            return;
        
        // It has been discovered
        if( output_fields.get( row ).get( col ).equals( "0" ) )
            return;

        // Discover it
        if( !output_fields.get( row ).get( col ).equals( "9" ) && output_fields.get( row ).get( col ).equals( "" ) )
            output_fields.get( row ).set( col, String.valueOf( fields.get( row ).get( col ) ) );   // odkryj!

        // wartość > 0 wyjście
        if( !output_fields.get( row ).get( col ).equals( "0" ) )
            return;

        // Execute function for each neighbor of field
        for( int r = -1; r < 2; ++r )
            for( int c = -1; c < 2; ++c )
                checkField( row + r, col + c );
    }
    
    //--------------------------------------------------------------------------
    
    private void prepareBoard( Level level ) {
        
        fields = new ArrayList<>();
        output_fields = new ArrayList<>();
        
        fields.clear();
        output_fields.clear();
        
        amount_of_mines = level.getAmountOfMines();
        
        for( int row = 0; row < level.getRows(); ++row ) {
            
            fields.add( new ArrayList<>() );
            output_fields.add( new ArrayList<>() );
            
            for( int col = 0; col < level.getCols(); ++col ) {
                
                fields.get( row ).add( 0 );
                output_fields.get( row ).add( "" );
            }
        }
    }
    private void setMines( Level level ) {
        
        int amount_of_mines = level.getAmountOfMines();
        
        int row, col;
        
        while( amount_of_mines > 0 )
        {
            row = (int)(Math.random() * level.getRows() );
            col = (int)(Math.random() * level.getCols() );
            
            System.out.println( row + " " + col );

            if( fields.get( row ).get( col ) != 9 ) {
                setMine( row, col, level );
                amount_of_mines--;
            }
        }
    }
    private void setMine( int row, int col, Level level ) {
        
        // Set mine on field
        fields.get( row ).set( col, 9 );

        // Increase the value around
        for( int r = -1; r < 2; ++r ) {
            for( int c = -1; c < 2; ++c ) {

                // Avoid an edge of board
                if( (col + c) < 0 || (row + r) < 0 )
                    continue;
                
                if( (col + c) > level.getCols()-1 || (row + r) > level.getCols()-1 )
                    continue;
                
                // Avoid a field with mine
                if( fields.get( row + r ).get( col + c ) == 9 )
                    continue;
                
                // Increase the vaule this field
                fields.get( row + r ).set( col + c, (fields.get( row + r ).get( col + c ) + 1) );
            }
        }
    }
}