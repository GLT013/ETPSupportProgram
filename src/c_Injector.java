
public class c_Injector {
	private boolean Product;						//Product Type
	private int AvgVol;								//Average Daily Volume
	private int InitialSP;							//Initial Setpoint
	private int PrioritySP;							//Priority Setpoint
	private int ShutdownSP;							//Shutdown Setpoint
	private int TimeoutSP;							//Timeout Setpoint
	private float LoadHrs;							//Number of Load Hours between Idle & Initial
	private float ShutdownHrs;						//Number of Load Hours until Shutdown

	c_Injector(){										//Injector Constructor
		Product = true;
		LoadHrs = 1;
		ShutdownHrs = 9;
		AvgVol = 0;
		InitialSP = 0;
		PrioritySP = 0;
		ShutdownSP = 0;
		TimeoutSP = 0;
	}
	
	public boolean getProduct() {					//Product Getter Method
		return Product;
	}
	public void setProduct(boolean product) {		//Product Setter Method
		Product = product;
	}	
	public float getLoadHrs() {						//Load Hours Getter Method
		return LoadHrs;
	}
	public void setLoadHrs(float loadHrs) {			//Load Hours Setter Method
		LoadHrs = loadHrs;
	}
	public float getShutdownHrs() {					//Shutdown Hours Getter Method
		return ShutdownHrs;
	}
	public void setShutdownHrs(float shutdownHrs) {	//Shutdown Hours Setter Method
		ShutdownHrs = shutdownHrs;
	}
	public int getAvgVol() {						//Average Volume Getter Method
		return AvgVol;
	}
	public void setAvgVol(int avgVol) {				//Average Volume Setter Method
		AvgVol = avgVol;
	}
	public int getInitialSP() {						//Initial Setpoint Getter Method
		return InitialSP;
	}
	public int getPrioritySP() {					//Priority Setpoint Getter Method
		return PrioritySP;
	}
	public int getShutdownSP() {					//Shutdown Setpoint Getter Method
		return ShutdownSP;
	}
	public int getTimeoutSP() {						//Timeout Setpoint Getter Method
		return TimeoutSP;
	}	
	public void CalcInitial() {						//Initial Setpoint Calculation Method
		if (LoadHrs == 0) throw new ArithmeticException("LoadHrs ==0");
		InitialSP = (int)(Math.round(AvgVol / (24 / LoadHrs) / 100) * 100);
	}
	public void CalcPriority(byte InjNum) {			//Priority Setpoint Calculation Method
		PrioritySP = InitialSP + (AvgVol / 24) * InjNum;
		PrioritySP += 50;
		PrioritySP = (int)(Math.round(PrioritySP / 100) * 100);
	}
	public void CalcShutdown() {					//Shutdown Setpoint Calculation Method
		if (Product) {
			ShutdownSP = (int)(Math.round((AvgVol / 24) * ShutdownHrs / 100) * 100);
		} else {
			ShutdownSP = (int) (Math.round((AvgVol / 24) * (ShutdownHrs * 1.5) / 100) * 100);
		}
	}
	public void CalcTimeout() {						//Timeout Setpoint Calculation Method
		if (Product) {
			TimeoutSP = (int)((ShutdownHrs * 1.5) + 0.05);
		} else {
			TimeoutSP = (int)((ShutdownHrs * 1.875) + 0.05);
		}
	}
}
