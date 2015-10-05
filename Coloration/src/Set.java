
public class Set {
	
	private int WHITE = 0;
	private int BLACK = 1;
	
	private int size;

	private Color color;
	
	private Set head;
	private Set next;
	private Set tail;
	
	public Set(int originColor)
	{
		if(originColor == WHITE)
		{
			color = new Color();
		} else {
			color = new Color(0, 0, 0);
		}
		
		this.head = this;
		this.next = null;
		this.tail = this;
		this.size = 1;
	}
	
	public boolean isBlack()
	{
		int[] colors = this.color.getComponents();
		return colors[0] == 0 && colors[1] == 0 && colors[2] == 0;
	}
	
	public String toString()
	{
		int[] colors = this.color.getComponents();
		return "[" + colors[0] + "," + colors[1] + "," + colors[2] + "]";
	}
	
	public String printList()
	{
		String tostring = "";
		Set current = this;
		while(current != null)
		{
			int[] colors = current.color.getComponents();
			tostring += "[" + colors[0] + "," + colors[1] + "," + colors[2] + "]";
			
			current = current.next;
		}
		
		return tostring;
	}
	
	public void setSize(int s)
	{
		this.size = s;
	}
	
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	public void setHead(Set h)
	{
		this.head = h;
	}
	
	public void setTail(Set t)
	{
		this.tail = t;
	}
	
	public void setNext(Set n)
	{
		this.next = n;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public Set getHead()
	{
		return this.head;
	}
	
	public Set getTail()
	{
		return this.tail;
	}
	
	public Set getNext()
	{
		return this.next;
	}
	
}
