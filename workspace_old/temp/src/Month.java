import java.util.ArrayList;

class Month 
{
	private String month;
	private ArrayList<Double> prcp;
	private int observedDays;
	
	public Month()
	{
		this.month = null;
		this.prcp = new ArrayList();
		this.observedDays = 0;
	}
	
	public void setMonth(String month){
		this.month = month;
	}
	
	public void addPRCP(double prcp)
	{
		this.prcp.add(Double.valueOf(prcp / Calculations.units));
	}
	
	public void addObservedDays(){
		this.observedDays += 1;
	}
	
	public String getMonth(){
		return this.month;
	}
	
	public double getPRCP(int i){
		return ((Double)this.prcp.get(i)).doubleValue();
	}
	public int getObserveredDays(){
		return this.observedDays;
	}
}
