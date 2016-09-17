package saperserver.Model;

/**
 *
 * @author Damian
 */
public class Level {
    
    private int rows = 0;
    private int cols = 0;
    private int amount_of_mines = 0;
    
    //==========================================================================
    
    public Level() {
    }
    public Level( int rows, int cols, int amount_of_mines ) {
        
        this.rows = rows;
        this.cols = cols;
        this.amount_of_mines = amount_of_mines;
    }
    
    //==========================================================================
    
    public void setRows( int rows ) {
        
        this.rows = rows;
    }
    public void setCols( int cols ) {
        
        this.cols = cols;
    }
    public void setAmountOfMines( int amount_of_mines ) {
        
        this.amount_of_mines = amount_of_mines;
    }
    
    public final int getRows() {
        
        return rows;
    }
    public final int getCols() {
        
        return cols;
    }
    public final int getAmountOfMines() {
        
        return amount_of_mines;
    }
}