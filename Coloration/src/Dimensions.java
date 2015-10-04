
public class Dimensions {
	
	private int rows;
	private int columns;
	
	public Dimensions(int r, int c)
	{
		this.rows = r;
		this.columns = c;
	}
	
	public Dimensions(String r, String c)
	{
		this.rows = Integer.valueOf(r);
		this.columns = Integer.valueOf(c);
	}
	
	public int getRows()
	{
		return this.rows;
	}
	
	public int getColumns()
	{
		return this.columns;
	}
}
