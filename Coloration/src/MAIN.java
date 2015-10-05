
public class MAIN {
	
	public static void main(String args[]) throws FileFormatException
	{
		PictureColoring pc = new PictureColoring();
		//pc.generate(500, 500, "images/toto.pbm");
		pc.colorPicture("images/test.pbm");
		
	}

}
