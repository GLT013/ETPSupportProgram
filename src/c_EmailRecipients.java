
public class c_EmailRecipients {
	
		private int ID;
		private String name;
		private String email;
			
		//Get functions
		public int getID() { return ID; }
		public String getName() { return name; }
		public String getEmail() { return email;}
		
		//Set functions		
		public void setID(int id2) { ID = id2; }
		//public void setEmail(String EmailAddr) { email = EmailAddr; }
		//public void setName(String name) { name = this.name;}
		public void setName(String name2) { name2 = name; }
		public void setEmail(String email2) { email2 = email;}
		
		public c_EmailRecipients(String name2, String email2)
		{
		
			name = name2;
			email = email2;
			
		}
		
		public String toString(){
			//String priceFormat =  new DecimalFormat("#.##").format(price);
			//String ItemAndPrice = item + " - $" + priceFormat;
			return name;
		}
		
	
		
	
}
