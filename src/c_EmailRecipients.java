
public class c_EmailRecipients {
	
		private int ID;
		private String name;
		private String email;
		private int nameid;
			
		//Get functions
		public int getID() { return ID; }
		public String getName() { return name; }
		public String getEmail() { return email;}
		public int getNameID() { return nameid;}
		
		//Set functions		
		public void setID(int id2) { ID = id2; }
		public void setName(String name2) { name2 = name; }
		public void setEmail(String email2) { email2 = email;}
		public void setName(int nameid2) { nameid2 = nameid; }
		
		public c_EmailRecipients(String name2, String email2)
		{
		
			name = name2;
			email = email2;			
			
			
		}
		
		public String toString(){
			return name;
		}
		
	
		
	
}
