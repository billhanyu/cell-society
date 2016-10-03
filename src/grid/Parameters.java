package grid;

/**
 * @author Brian
 * Holds basic parameters for a simulation: title, author, number of rows, number of columns,
 * shape, and whether or not the simulation is initialized via parameters or via specific
 * lists of cell configurations
 */
public class Parameters {

	protected String title;
	protected String author;
	protected int rows;
	protected int cols;
	private boolean setByLocations;
	private String type;

	public Parameters(String title, String author, int rows, int cols, String shape){
		this.title = title;
		this.author = author;
		this.rows = rows;
		this.cols = cols;
		this.type = shape;
	}

	public Parameters (Parameters p) {
		this.title = p.getTitle();
		this.author = p.getAuthor();
		this.rows = p.getRows();
		this.cols = p.getCols();
		this.type = p.getGraphicType();
	}

	/**
	 * Get title of the simulation
	 * @return simulation title
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * Get author of the simulation
	 * @return author
	 */
	public String getAuthor () {
		return author;
	}

	/**
	 * Get number of rows in the simulation grid
	 * @return number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Get number of columns in simulation grid
	 * @return number of columns
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Change number of rows in grid
	 * @param rows
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * Change number of columns in grid
	 * @param cols
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * Get the current shape of the simulation cells
	 * @return String of shape
	 */
	public String getGraphicType() {
		return type;
	}

	/**
	 * Determines whether or not the simulation is to be initialized randomly or by
	 * explicit lists of cells
	 * @param isSet
	 */
	public void setByLocations(boolean isSet){
		this.setByLocations = isSet;
	}

	/**
	 * Get type of Simulation to initialize (random or specific cell locations)
	 * @return boolean 
	 */
	public boolean isSetByLocations(){
		return setByLocations;
	}
}
