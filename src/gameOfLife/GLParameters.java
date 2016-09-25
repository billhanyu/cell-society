package gameOfLife;

import java.util.ArrayList;
import java.util.List;
import cell.GridPosition;
import grid.Parameters;

public class GLParameters extends Parameters {
    
    private double ratioOfAlive;
    private List<GridPosition> listOfAlive;
    private boolean modifiedStart;

    public GLParameters (String title, String author, String rows, String cols) {
        super(title, author, rows, cols);
    }
    
    public GLParameters(Parameters p, String ratioOfAlive){
        super(p);
        this.ratioOfAlive = Double.parseDouble(ratioOfAlive);
        modifiedStart = false;
    }
    
    public GLParameters(Parameters p, List<String> listOfAlive){
        super(p);
        // just putting this line in for test purpose
        this.ratioOfAlive = .3;
        this.listOfAlive = new ArrayList<GridPosition>();
        for (int i = 0; i < listOfAlive.size(); i++){
            int row = Integer.parseInt(listOfAlive.get(i).split(" ")[0]);
            int col = Integer.parseInt(listOfAlive.get(i).split(" ")[1]);
            this.listOfAlive.add(new GridPosition(row, col));
        }
        modifiedStart = true;
    }

    
    public double getRatioOfAlive () {
        return ratioOfAlive;
    }

    public boolean isModifiedStart () {
        return modifiedStart;
    }
    
    public List<GridPosition> getListOfAlive(){
        return listOfAlive;
    }

}
