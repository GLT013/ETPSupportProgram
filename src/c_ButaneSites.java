
public class c_ButaneSites {
	private String client;
	private String site;
	private String state;
	private String stateabbrv;
	private int siteid;
	private String clientabbrv;
	private String siteabbrv;
	private String sharepoint;
	private int idrac;
	private int host;	
	private int view;
	private int sql;
	private int dev;
	private float generation;	
	private String address;
	private String phone;
	private String fieldtech;
	private String fieldsupervisor;
	private boolean twic;
	private String timezone;
	private boolean highperformance;
	private boolean centralSQL;
	private boolean centralView;
	
	
	//Get functions
	public String getClient() { return this.client; }
	public String getSite() { return this.site; }
	public String getState() { return this.state; }
	public String getStateAbbrv() { return this.stateabbrv; }
	public String getSharepoint() { return this.sharepoint; }
	public int getSiteID() { return this.siteid; }
	public String getClientAbbrv() { return this.clientabbrv; }	
	public String getSiteAbbrv() { return this.siteabbrv; }
	public int getiDrac() { return this.idrac; }
	public int getHost() { return this.host; }
	public int getView() { return this.view; }
	public int getSQL() { return this.sql; }
	public int getDev() { return this.dev; }
	public float getGeneration() { return this.generation; }	
	public String getAddress() { return this.address; }
	public String getPhone() { return this.phone; }
	public String getFieldTech() { return this.fieldtech; }
	public String getFieldSupervisor() { return this.fieldsupervisor; }
	public boolean getTwic() { return this.twic; }
	public String getTimezone() { return this.timezone; }
	public boolean getHighPerformance() { return this.highperformance; }
	public boolean getCentralSQL() { return this.centralSQL; }
	public boolean getCentralView() { return this.centralView; }
	
	//Set Functions
	public void setClient(String client){ this.client = client;}
	public void setSite(String site){ this.site = site;}
	public void setState(String state){ this.state = state;}
	public void setStateAbbrv(String stateabbrv){ this.stateabbrv = stateabbrv;}
	public void setSharepoint(String sharepoint){ this.sharepoint = sharepoint;}
	public void setSiteID(int siteid){ this.siteid = siteid;}
	public void setClientAbbrv(String clientabbrv){ this.clientabbrv = clientabbrv;}
	public void setSiteAbbrv(String siteabbrv){ this.siteabbrv = siteabbrv;}	
	public void setiDrac(int idrac){ this.idrac = idrac;}
	public void setHost(int host){ this.host= host;}
	public void setView(int view){ this.view = view;}
	public void setSQL(int sql){ this.sql = sql;}
	public void setDev(int dev){ this.dev = dev;}
	public void setGeneration(float generation){ this.generation = generation;}	
	public void setAddress(String address){ this.address = address;}
	public void setPhone(String phone){ this.phone = phone;}
	public void setFieldTech(String fieldtech){ this.fieldtech = fieldtech;}
	public void setFieldSupervisor(String fieldsupervisor){ this.fieldsupervisor = fieldsupervisor;}
	public void setTwic(boolean twic){ this.twic = twic;}
	public void setTimezone(String timezone){ this.timezone = timezone;}
	public void setHighPerformance(boolean highperformance){ this.highperformance = highperformance;}
	public void setCentralSQL(boolean centralSQL){ this.centralSQL = centralSQL;}
	public void setCentralView(boolean centralView){ this.centralView = centralView;}
	
	@Override
	public String toString(){
		return this.client + " " + this.site + " (" + this.siteid + ")";
		//return this.site + " (" + this.siteid + ")";
}
	
	public String getName()
	{
		return this.client + " " + this.site + " (" + this.siteid + ")";		
		
	}
	
	public String getChecklistName()
	{
		return this.client + " " + this.site;
	}
	
	//This is used for ButaneSites
	public c_ButaneSites(String client2,String site2,String state2,String stateabbrv2,int siteid2,String clientabbrv2,String siteabbrv2,int idrac2,int host2,int view2,int sql2,int dev2,float generation2,String address2,String phone2,String fieldtech2,String fieldsupervisor2,boolean twic2,String timezone2,boolean highperformance2,boolean centralSQL2,boolean centralView2, String sharepoint2)
	{
		client = client2;
		site = site2;
		state = state2;
		stateabbrv = stateabbrv2;
		siteid = siteid2;
		clientabbrv = clientabbrv2;		
		siteabbrv = siteabbrv2;
		idrac = idrac2;
		host = host2;
		view = view2;
		sql = sql2;
		dev = dev2;
		generation = generation2;
		address = address2;
		phone = phone2;
		fieldtech = fieldtech2;
		fieldsupervisor = fieldsupervisor2;
		twic = twic2;
		timezone = timezone2;
		highperformance = highperformance2;
		centralSQL = centralSQL2;
		centralView = centralView2;		
		sharepoint = sharepoint2;
	}
	
	//This is used for Create Checklist
	public c_ButaneSites(String client2, String site2, String stateabbrv2,String clientabbrv2, String siteabbrv2, int siteid2, int viewip2, int sqlip2, int devip2, float generation2, boolean highperformance2, boolean centralsql2, boolean centralview2)
	{
		client = client2;
		site = site2;
		siteid = siteid2;
		stateabbrv = stateabbrv2;
		clientabbrv = clientabbrv2;
		siteabbrv = siteabbrv2;
		view = viewip2;
		sql = sqlip2;
		dev = devip2;
		generation = generation2;
		highperformance = highperformance2;
		centralSQL = centralsql2;
		centralView = centralview2;		
	}
	
	//This is used for SiteChanges
	public c_ButaneSites(String client2, String site2, int siteid2)
	{
		client = client2;
		site = site2;
		siteid = siteid2;
				
	}
	
	public void updateSites(String client, String site, String state,String stateabbrv, int siteid, String clientabbrv, String siteabbrv, int idrac, 
			int host, int view, int sql, int dev, float generation, String address, String phone, String fieldtech, String fieldsupervisor,
			boolean twic, String timezone, boolean highperformance, boolean centralSQL, boolean centralView)		
	{
		this.client = client;
		this.site = site;
		this.state = state;
		this.stateabbrv = stateabbrv;
		this.siteid = siteid;
		this.clientabbrv = clientabbrv;
		this.siteabbrv = siteabbrv;
		this.idrac = idrac;
		this.host= host;
		this.view = view;
		this.sql = sql;
		this.dev = dev;
		this.generation = generation;		
		this.address = address;
		this.phone = phone;
		this.fieldtech = fieldtech;
		this.fieldsupervisor = fieldsupervisor;
		this.twic = twic;
		this.timezone = timezone;
		this.highperformance = highperformance;
		this.centralSQL = centralSQL;
		this.centralView = centralView;
		
	}
	

}
