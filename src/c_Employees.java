
public class c_Employees {
	
	private String name;
	private String title;
	private String email;
	private String officePhone;
	private String mobilePhone;
	
	
	//Get functions
	public String getName() { return this.name; }
	public String getTitle() { return this.title; }
	public String getEmail() { return this.email; }
	public String getOffice() { return this.officePhone; }
	public String getMobile() { return this.mobilePhone; }
		
			
		//Set functions		
	public void setName(String name){ this.name = name;}
	public void setTitle(String title){ this.title = title;}
	public void setEmail(String email){ this.email = email;}
	public void setOffice(String officePhone){ this.officePhone = officePhone;}
	public void setMobile(String mobilePhone){ this.mobilePhone = mobilePhone;}
		
	public void setEmployee(String name, String title, String email, String officePhone, String mobilePhone)
	{
		this.name = name;
		this.title = title;
		this.email = email;
		this.officePhone = officePhone;
		this.mobilePhone = mobilePhone;
	}
	
	public void setSunocoContact(String name, String title, String email, String mobilePhone)
	{
		this.name = name;
		this.title = title;
		this.email = email;		
		this.mobilePhone = mobilePhone;
	}
	
	

}
