import java.util.Random;

import javax.swing.JOptionPane;

public class c_EasterEggs {
	
	public static String EasterEggs()
	{
		
		Random rand = new Random();
		int  n = rand.nextInt(6) + 1;
	
	 String[] EasterEggs = new String[20];
	 EasterEggs[0] = "Listen bud, I wouldn't do that if I were you.";
	 EasterEggs[1] = "I think it's cute you still try.";
	 EasterEggs[2] = "Yeah...I'm going to need you to go ahead and go online for this";
	 EasterEggs[3] = "Sorry champ, Edits are not available in Offline Mode.";
	 EasterEggs[4] = "If you could go ahead and go online for edits, that'd be great.";
	 EasterEggs[5] = "Oh boy..Wow! Hmm how should I say this. Umm no.";
	 EasterEggs[6] = "Who do you think you are I am!";
	 EasterEggs[7] = "Grizzly Adams did have a beard.";
	 
	 return EasterEggs[n];
	}
	
	

}
