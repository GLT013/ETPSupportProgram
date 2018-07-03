
public class c_CleanString {
	
	public static String Clean_String(String str)
	 {
		 if (str == null) { 
	            return null; 
	        } 
	 
	        if (str.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]","").length() < 1) { 
	            return str; 
	        } 
	 
	        String clean_string = str; 
	        //clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\");	 **Removed on 7/3/2018**        
	        clean_string = clean_string.replaceAll("\\n"," "); //replace new line with nothing. New lines not allowed.
	        clean_string = clean_string.replaceAll("\\r", "\\\\r"); 
	        clean_string = clean_string.replaceAll("\\t", "\\\\t"); 
	        clean_string = clean_string.replaceAll("\\00", "\\\\0"); 
	        clean_string = clean_string.replaceAll("'", "\\''");
	        clean_string = clean_string.replaceAll("\\'", "'\'");
	        
	        
	        
	        //clean_string = clean_string.replaceAll("'", "\"");
	        //clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
	        //clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
	        //clean_string = clean_string.replaceAll("\\n","\\\\n");
	        
	        
	         
	 
	        if (clean_string.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]" 
	                ,"").length() < 1) 
	        { 
	            return clean_string; 
	        } 
	        
	        return str;
	 }

}
