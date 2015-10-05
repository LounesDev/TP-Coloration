import java.util.ArrayList;
import java.util.Random;

public class Color {
	
	private int MAX = 255;
	private int MIN = 0;
	
	private int[] components;
	private static ArrayList<Color> generatedColors = new ArrayList<Color>();
	
	public Color()
	{
		Random rand = new Random();
		boolean exists = true;
		
		this.components = new int[3];
		
		while(exists)
		{
			exists = false;
			
			for(int i = 0; i < 3; i++)
			{				
					this.components[i] = rand.nextInt((MAX - MIN) + 1) + MIN;				
			}
			
			for(Color c : generatedColors)
			{
				if(c.equals(this.components[0], this.components[1], this.components[2]))
				{
					exists = true;
				} 
			}
			
			if(this.components[0] == 0 && this.components[1] == 0 && this.components[2] == 0)
			{
				exists = true;
			}
		}
		
		generatedColors.add(this);
		
		
	}
	
	public Color(int c1, int c2, int c3)
	{
		this.components = new int[3];
		this.components[0] = c1;
		this.components[1] = c2;
		this.components[2] = c3;
	}
	
	public boolean equals(int c1, int c2, int c3)
	{		
		return this.components[0] == c1 && this.components[1] == c2 
				&& this.components[2] == c3;
	}
	
	public boolean equals(Color color)
	{
		return this.components[0] == color.getComponents()[0] && this.components[1] == color.getComponents()[1] 
				&& this.components[2] == color.getComponents()[2];
	}
	
	public int[] getComponents()
	{
		return this.components;
	}
	
}
