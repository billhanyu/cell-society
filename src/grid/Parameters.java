package grid;

public class Parameters {
    
        protected String title;
        protected String author;
	protected int rows;
	protected int cols;
	
	public Parameters(String title, String author, String rows, String cols){
	    this.title = title;
	    this.author = author;
	    this.rows = Integer.parseInt(rows);
	    this.cols = Integer.parseInt(cols);
	}
	
	public Parameters (Parameters p) {
	    this.title = p.getTitle();
	    this.author = p.getAuthor();
	    this.rows = p.getRows();
	    this.cols = p.getCols();
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

}
