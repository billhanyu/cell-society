package xml.model;

public class SpreadingFireModel extends SimulationParameters {
    
    private final double probCatch;
    private final int numRows;
    private final int numCols;
    
    public SpreadingFireModel (String title, String author, String size) {
        super(title, author, size);
        probCatch = 0;
        numRows = 5;
        numCols = 5;
        // TODO Auto-generated constructor stub
    }
    
    public SpreadingFireModel (String title, String author, String size, String numRows, String numCols, String probCatch){
        super(title, author, size);
        this.probCatch = Double.parseDouble(probCatch);
        this.numRows = Integer.parseInt(numRows);
        this.numCols = Integer.parseInt(numCols);
    }

    public double getProbCatch () {
        return probCatch;
    }

    public int getNumRows () {
        return numRows;
    }

    public int getNumCols () {
        return numCols;
    }


    
   
    
     
    
}
