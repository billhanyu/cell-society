package SpreadingFire;

public class SFExtendedParameters {
        
    private int rowStart;
    private int colStart;
    
    public SFExtendedParameters(){
        rowStart = 0;
        colStart = 0;
    }
    public int getRowStart () {
        return rowStart;
    }
    public void setRowStart (int rowStart) {
        this.rowStart = rowStart;
    }
    public int getColStart () {
        return colStart;
    }
    public void setColStart (int colStart) {
        this.colStart = colStart;
    }
}
