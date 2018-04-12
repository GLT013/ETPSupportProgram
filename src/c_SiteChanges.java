
public class c_SiteChanges {
	
	private String ChangeDate;
	private String MOCNum;
	private String RequestedBy;
	private String ChangedBy;
	private String Change;
	private boolean inj;
	private boolean pgm;
	private boolean hmi;
	private boolean reporting;
	private boolean safety;
	private boolean sampling;
	private boolean supply;
	private boolean system;
	private boolean other;
	private int rowID;
	
	
	//Get functions
	public String getChangeDate() { return this.ChangeDate; }
	public String getMOCNum() { return this.MOCNum; }
	public String getRequestedBy() { return this.RequestedBy; }
	public String getChangedBy() { return this.ChangedBy; }
	public String getChange() { return this.Change; }
	public boolean getinj() { return this.inj; }
	public boolean getpgm() { return this.pgm; }
	public boolean gethmi() { return this.hmi; }
	public boolean getreporting() { return this.reporting; }
	public boolean getsafety() { return this.safety; }
	public boolean getsupply() { return this.supply; }	
	public boolean getsampling() { return this.sampling; }
	public boolean getsystem() { return this.system; }
	public boolean getother() { return this.other; }
	public int getRowID() { return this.rowID; }
	
	
	
	//Set Functions
	public void setChangeDate(String ChangeDate){ this.ChangeDate = ChangeDate;}
	public void setMOCNum(String MOCNum){ this.MOCNum = MOCNum;}
	public void setRequestedBy(String RequestedBy){ this.RequestedBy = RequestedBy;}
	public void setChangedBy(String ChangedBy){ this.ChangedBy = ChangedBy;}
	public void setChange(String Change){ this.Change = Change;}
	public void setinj(boolean inj){ this.inj = inj;}
	public void setpgm(boolean pgm){ this.pgm = pgm;}
	public void sethmi(boolean hmi){ this.hmi = hmi;}	
	public void setreporting(boolean reporting){ this.reporting = reporting;}
	public void setsafety(boolean safety){ this.safety = safety;}
	public void setsupply(boolean supply){ this.supply = supply;}
	public void setsampling(boolean sampling){ this.sampling = sampling;}
	public void setsystem(boolean system){ this.system = system;}
	public void setother(boolean other){ this.other = other;}
	public void setRowID(int rowID){ this.rowID = rowID;}
	
	/*
	@Override
	public String toString(){
		return this.client + " " + this.site + " (" + this.siteid + ")";
}
	
	public String getName()
	{
		return this.client + " " + this.site + " (" + this.siteid + ")";		
		
	}
	*/
	
	public void updateSiteChanges(String ChangeDate,String MOCNum,String RequestedBy,String ChangedBy,String Change,boolean inj,boolean pgm,
			boolean hmi,boolean reporting,boolean safety,boolean sampling,boolean supply,boolean system,boolean other, int rowID)		
	{
		this.ChangeDate = ChangeDate;
		this.MOCNum = MOCNum;
		this.RequestedBy = RequestedBy;
		this.ChangedBy = ChangedBy;
		this.Change = Change;
		this.inj = inj;
		this.pgm = pgm;
		this.hmi = hmi;
		this.reporting = reporting;
		this.safety = safety;
		this.sampling = sampling;
		this.system = system;
		this.other = other;
		this.rowID = rowID;
	}
	

}
