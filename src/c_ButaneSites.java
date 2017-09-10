
public class c_ButaneSites {
	private String client;
	private String site;
	private String state;
	private int siteid;
	private String clientabbrv;
	private String siteabbrv;
	private int idrac;
	private int host;	
	private int view;
	private int sql;
	private int dev;
	private float generation;
	private String HMI;	
	private String address;
	private String phone;
	private String fieldtech;
	private String fieldsupervisor;
	private boolean twic;
	private String timezone;
	private boolean highperformance;
	
	
	//Get functions
	public String getClient() { return this.client; }
	public String getSite() { return this.site; }
	public String getState() { return this.state; }
	public int getSiteID() { return this.siteid; }
	public String getClientAbbrv() { return this.clientabbrv; }	
	public String getSiteAbbrv() { return this.siteabbrv; }
	public int getiDrac() { return this.idrac; }
	public int getHost() { return this.host; }
	public int getView() { return this.view; }
	public int getSQL() { return this.sql; }
	public int getDev() { return this.dev; }
	public float getGeneration() { return this.generation; }	
	public String getHMI() { return this.HMI; }
	public String getAddress() { return this.address; }
	public String getPhone() { return this.phone; }
	public String getFieldTech() { return this.fieldtech; }
	public String getFieldSupervisor() { return this.fieldsupervisor; }
	public boolean getTwic() { return this.twic; }
	public String getTimezone() { return this.timezone; }
	public boolean getHighPerformance() { return this.highperformance; }
	
	//Set Functions
	public void setClient(String client){ this.client = client;}
	public void setSite(String site){ this.site = site;}
	public void setState(String state){ this.state = state;}
	public void setSiteID(int siteid){ this.siteid = siteid;}
	public void setClientAbbrv(String clientabbrv){ this.clientabbrv = clientabbrv;}
	public void setSiteAbbrv(String siteabbrv){ this.siteabbrv = siteabbrv;}	
	public void setiDrac(int idrac){ this.idrac = idrac;}
	public void setHost(int host){ this.host= host;}
	public void setView(int view){ this.view = view;}
	public void setSQL(int sql){ this.sql = sql;}
	public void setDev(int dev){ this.dev = dev;}
	public void setGeneration(float generation){ this.generation = generation;}	
	public void setHMI(String HMI){ this.HMI = HMI;}
	public void setAddress(String address){ this.address = address;}
	public void setPhone(String phone){ this.phone = phone;}
	public void setFieldTech(String fieldtech){ this.fieldtech = fieldtech;}
	public void setFieldSupervisor(String fieldsupervisor){ this.fieldsupervisor = fieldsupervisor;}
	public void setTwic(boolean twic){ this.twic = twic;}
	public void setTimezone(String timezone){ this.timezone = timezone;}
	public void setHighPerformance(boolean highperformance){ this.highperformance = highperformance;}
	
	
	public String getName()
	{
		return this.client + " " + this.site + " (" + this.siteid + ")";		
		
	}
	
	public void updateSites(String client, String site, String state, int siteid, String clientabbrv, String siteabbrv, int idrac, 
			int host, int view, int sql, int dev, float generation, String HMI, String address, String phone, String fieldtech, String fieldsupervisor,
			boolean twic, String timezone, boolean highperformance)		
	{
		this.client = client;
		this.site = site;
		this.state = state;
		this.siteid = siteid;
		this.clientabbrv = clientabbrv;
		this.siteabbrv = siteabbrv;
		this.idrac = idrac;
		this.host= host;
		this.view = view;
		this.sql = sql;
		this.dev = dev;
		this.generation = generation;
		this.HMI = HMI;
		this.address = address;
		this.phone = phone;
		this.fieldtech = fieldtech;
		this.fieldsupervisor = fieldsupervisor;
		this.twic = twic;
		this.timezone = timezone;
		this.highperformance = highperformance;
	}
	

}
