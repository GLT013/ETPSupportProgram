import java.util.Random;

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
	
	
	public static String Greetings()
	{
		
		Random rand = new Random();
		int  n = rand.nextInt(6) + 1;
	
	 String[] Greetings = new String[20];
	 Greetings[0] = "Hi";
	 Greetings[1] = "Hello";
	 Greetings[2] = "Howdy";
	 Greetings[3] = "Whats New";
	 Greetings[4] = "Whats Happenin";
	 Greetings[5] = "G'day";
	 Greetings[6] = "Wazzah";
	 Greetings[7] = "Yo";
	 
	 return Greetings[n];
	}
	
	

}
