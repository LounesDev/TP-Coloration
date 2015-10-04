import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureColoring {
	
	public int[][] read(String path) throws FileFormatException
	{
		//File variables
		BufferedReader in = null;
		File pictureFile = new File(path);
		
		if(!isValid("pbm", pictureFile))
		{
			throw new FileFormatException("Wrong format");
		}
		
		//Dimensions
		Dimensions dimensions = getPictureDimensions(pictureFile);
		
		//Reading and Saving variables
		int[][] pixels = new int[dimensions.getRows()][dimensions.getColumns()];
		String line;
		int rows = 0;
		int columns = 0;
		
		try {
			//Object to read file with
			in = new BufferedReader(new InputStreamReader(new FileInputStream(pictureFile)));
			
			//Set cursor after the two first lines
			in.readLine();
			in.readLine();
			
			while((line = in.readLine()) != null)
			{
				for(int i = 0; i < line.length(); i++)
				{

					if(rows > dimensions.getRows() - 1)
					{
						break;
					}
					
					pixels[rows][columns] = Character.getNumericValue(line.charAt(i));
					columns++;
					
					if(columns == dimensions.getColumns() - 1)
					{
						columns = 0;
						rows++;
					}
					
				}
				
				
			}
			
			return pixels;
						
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public void write(boolean type, int[][] pixels, String path)
	{
		File picture = new File(path);
		PrintWriter out = null;
		int rows = pixels.length;
		int columns = pixels[0].length;
		int cpt = 0;
		
		try {
			out = new PrintWriter(picture, "UTF-8");
			
			if(type) 
			{
				out.println("P3");
				out.println(columns + " " + rows);
				out.println(maxValue(pixels));
			} else {
				out.println("P1");
				out.println(columns + " " + rows);
			}
			
			
			
			for(int i = 0; i < pixels.length; i++)
			{
				for(int j = 0; j < pixels[0].length; j++)
				{
					if(!type)
					{
						out.print(pixels[i][j]);
						cpt++;
						if(cpt == 70)
						{
							out.println();
							cpt = 0;
						}
					} else {
						out.print(pixels[i][j] + " ");
						cpt++;
						if(cpt == 10)
						{
							out.println();
							cpt = 0;
						}
					}
				}
			}
			out.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void generate(int n, int m, String path)
	{
		int[][] pixels = new int[n][m];
		Random rand = new Random();
		int random = 0;
		
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				random = rand.nextInt((1 - 0) + 1) + 0; 
				pixels[i][j] = random;
			}
		}
		
		write(false, pixels, path);
		
	}
	
	public boolean isValid(String format, File file)
	{
		String name = file.getName();
		String extension = name.substring(name.length() - 3);

		if(!extension.equals(format))
		{
			return false;
		}
		
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			
			String magicNumber = buffer.readLine();
			String dimensionsLine = buffer.readLine();
			
			if(!magicNumber.matches("^[P][1-6]\\s*([#].*){0,1}") || !dimensionsLine.matches("^\\d*\\s\\d*\\s*([#].*){0,1}"))
			{
				return false;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return true;
	}
	
	public int maxValue(int[][] pixels)
	{
		int max = 0;
		int lineMax;
		
		for(int i = 0; i < pixels.length; i++)
		{
			if((lineMax = max(pixels[i])) > max)
			{
				max = lineMax;
			}
		}
		
		return max;
	}
	
	public int max(int[] array)
	{
		int max = array[0];
		
		for(int i = 1; i < array.length; i++)
		{
			if(array[i] > max)
			{
				max = array[i];
			}
		}
		
		return max;
	}
	
	public Dimensions getPictureDimensions(File pictureFile)
	{
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(pictureFile));
			buffer.readLine();
			
			String dimensionsLine = buffer.readLine();
			Pattern pattern = Pattern.compile("\\d*\\s\\d*");
			Matcher matcher = pattern.matcher(dimensionsLine);
			
			String dimensionsTab[] = null;
			
			if(matcher.find())
			{
				dimensionsTab = matcher.group(0).split(" ");
			}
			
			return new Dimensions(dimensionsTab[1], dimensionsTab[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
			
}
