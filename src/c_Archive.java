
public class c_Archive {
	
	private String client;
	private String site;
	private String category;
	private String ticket;
	private String entereddate;
	private String description;
	private String assigned;
	private String status;
	private String resolution;
	private String internal;
	private String active;
	private String emailsent;
	private String updatedate;
	private String timespent;
	private String ccnotified;
	private String lastupdatedby;
	
		
	//Get functions
	public String getClient() { return client; }
	public String getSite() { return site; }
	public String getCategory() { return category;}
	public String getTicket(){ return this.ticket;}	
	public String getEnteredDate(){ return entereddate;}
	public String getDescription(){ return description;}
	public String getAssigned(){ return assigned;}
	public String getStatus(){ return status;}
	public String getResolution(){ return resolution;}
	public String getInternal(){ return internal;}
	public String getActive(){ return active;}
	public String getEmailSent(){ return emailsent;}
	public String getUpdateDate(){ return updatedate;}
	public String getTimeSpent(){ return timespent;}
	public String getCCNotified(){ return ccnotified;}
	public String getLastUpdatedBy(){ return lastupdatedby;}
	
	
	//Set functions		
	public void setClient(String _client) { client = _client; }
	public void setSite(String _site) { site = _site; }
	public void setCategory(String _category) { category = _category;}
	public void setTicket(String _ticket){ ticket = _ticket;}
	public void setEnteredDate(String _entereddate){ entereddate = _entereddate;}
	public void setDescription(String _description){ description = _description;}
	public void setAssigned(String _assigned){assigned = _assigned;}
	public void setStatus(String _status){status = _status;}
	public void setResolution(String _resolution){resolution = _resolution;}
	public void setInternal(String _internal){internal = _internal;}
	public void setActive(String _active){ active = _active;}
	public void setEmailSent(String _emailsent){ emailsent = _emailsent;}
	public void setUpdateDate(String _updatedate){ updatedate = _updatedate;}
	public void setTimeSpent(String _timespent){ timespent = _timespent;}
	public void setCCNotified(String _ccnotified){ ccnotified = _ccnotified;}
	public void setLastUpdatedBy(String _lastupdatedby){ lastupdatedby = _lastupdatedby;}
	
	
	
	public void setArchive(String _client, String _site,String _category, String _ticket, String _entereddate,String _description,String _assigned,String _status,String _resolution,String _internal,String _active,String _emailsent,String _updatedate,String _timespent, String _ccnotified,String _lastupdatedby)
	{
		client = _client;
		site = _site;
		category = _category;
		ticket = _ticket;
		entereddate = _entereddate;
		description = _description;
		assigned = _assigned;
		status = _status;
		resolution = _resolution;
		internal = _internal;
		active = _active;
		emailsent = _emailsent;
		updatedate = _updatedate;
		timespent = _timespent;
		ccnotified = _ccnotified;
		lastupdatedby = _lastupdatedby;
	}
	
		
	
	
}
