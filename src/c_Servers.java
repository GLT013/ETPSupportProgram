
public class c_Servers {
	private String server;
	private String ip;
	private String description;
	
	
	
	//Get functions
	public String getServer() { return this.server; }
	public String getIP() { return this.ip; }
	public String getDescription() { return this.description; }
	
	
	//Set Functions
	public void setServer(String server){ this.server = server;}
	public void setIP(String ip){ this.ip = ip;}
	public void setDescription(String description){ this.description = description;}
	
	
	@Override
	public String toString(){
		return this.server;
}
	
	public String getName()
	{
		return this.server;		
		
	}
	
	public void updateServer(String server, String ip, String description)		
	{
		this.server = server;
		this.ip = ip;
		this.description = description;
		
		
	}
	

}
