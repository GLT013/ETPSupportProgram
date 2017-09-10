
public class c_Equipment {
	
	private String project;
	private String project_number;
	private String description;
	private String vendor;
	private String vendor_quote;
	private String cost;
	private String quantity;
	private String comments;
	private String order_submitted;
	private String order_submitted_date;
	private String order_received;
	private String order_received_date;
	private String po_code;
	private String po_submitted_date;
	private String shipper;
	private String tracking_number;
	private String part_number;
	private String po_submitted;
		
	//Get functions
	public String getproject() { return project; }
	public String getprojectnumber() { return project_number; }
	public String getdescription() { return description;}
	public String getvendor(){ return vendor;}
	public String getvendor_quote(){ return vendor_quote;}
	public String getcost(){ return cost;}
	public String getquantity(){ return quantity;}
	public String getcomments(){ return comments;}
	public String getordersubmitted(){ return order_submitted;}
	public String getordersubmitteddate(){ return order_submitted_date;}
	public String getorderreceived(){ return order_received;}
	public String getorderreceiveddate(){ return order_received_date;}
	public String getpocode(){ return po_code;}
	public String getposubmitteddate(){ return po_submitted_date;}
	public String getshipper(){ return shipper;}
	public String gettrackingnumber(){ return tracking_number;}
	public String getpartnumber(){ return part_number;}
	public String getposubmitted(){ return po_submitted;}
	
	//Set functions		
	public void setproject(String proj) { project = proj; }
	public void setprojectnumber(String projnum) { project_number = projnum; }
	public void setdescription(String desc) { description = desc;}
	public void setvendor(String vend){ vendor = vend;}
	public void setvendor_quote(String vendq){ vendor_quote = vendq;}
	public void setcost(String cst){ cost = cst;}
	public void setquantity(String quant){quantity = quant;}
	public void setcomments(String comm){comments = comm;}
	public void setordersubmitted(String ordsub){order_submitted = ordsub;}
	public void setordersubmitteddate(String ordsubdate){order_submitted_date = ordsubdate;}
	public void setorderreceived(String ordrec){ order_received = ordrec;}
	public void setorderreceiveddate(String ordrecdate){ order_received_date = ordrecdate;}
	public void setpocode(String po){ po_code = po;}
	public void setposubmitteddate(String posubdate){ po_submitted_date = posubdate;}
	public void setshipper(String ship){ shipper = ship;}
	public void settrackingnumber(String track){ tracking_number = track;}
	public void setpartnumber(String part){ part_number= part;}
	public void setposubmitted(String posub){ po_submitted= posub;}
	
	
	public c_Equipment(String proj, String part,String projnum, String desc, String vend,String vendq,String cst,String quant,String comm,String ordsub,String ordsubdate,String ordrec,String ordrecdate,String po,String posub, String posubdate,String ship,String track)
	{
		project = proj;
		project_number = projnum;
		part_number = part;
		description = desc;
		vendor = vend;
		vendor_quote = vendq;
		cost = cst;
		quantity = quant;
		comments = comm;
		order_submitted = ordsub;
		order_submitted_date = ordsubdate;
		order_received = ordrec;
		order_received_date = ordrecdate;
		po_code = po;
		po_submitted = posub;
		po_submitted_date = posubdate;
		shipper = ship;
		tracking_number = track;
	}
	
	
	public c_Equipment(String proj)
	{
		project = proj;		
		
	}
	
	public c_Equipment(String proj, String partnum)
	{
		project = proj;
		part_number = partnum;		
		
	}
	
	
@Override
public String toString(){
		
		if(this.getproject() != null)
		{
		String fullproject = new String(this.getproject());
		return fullproject;
		}
		
		else 
		{
			return this.getproject();
		}
		
		
	}
	
	
	
	
}
