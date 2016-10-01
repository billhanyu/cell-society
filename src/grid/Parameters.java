package grid;

public class Parameters {

	protected String title;
	protected String author;
	protected int rows;
	protected int cols;
	private boolean setByLocations;
	private GraphicType type;

	public Parameters(String title, String author, int rows, int cols, String shape){
	        this.title = title;
		this.author = author;
		this.rows = rows;
		this.cols = cols;
		this.type = GraphicType.Rectangle;
	}

	public Parameters (Parameters p) {
		this.title = p.getTitle();
		this.author = p.getAuthor();
		this.rows = p.getRows();
		this.cols = p.getCols();
		this.type = p.getGraphicType();
	}

	public String getTitle () {
		return title;
	}

	public String getAuthor () {
		return author;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public GraphicType getGraphicType() {
		return type;
	}
	
	public void setByLocations(boolean isSet){
	        this.setByLocations = isSet;
	}
	
	public boolean isSetByLocations(){
	        return setByLocations;
	}
}
