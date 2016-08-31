/*    */ import java.util.ArrayList;
/*    */ 
/*    */ class Year
/*    */ {
/*    */   private String year;
/*    */   private ArrayList<Double> prcp;
/*    */   private int observedDays;
/*    */   
/*    */   public Year()
/*    */   {
/* 22 */     this.year = null;
/* 23 */     this.prcp = new ArrayList();
/* 24 */     this.observedDays = 0;
/*    */   }
/*    */   
/*    */   public void setYear(String year) {
/* 28 */     this.year = year;
/*    */   }

/*    */   public void addPRCP(double prcp)
/*    */   {
/* 35 */     this.prcp.add(Double.valueOf(prcp / Calculations.units));
/*    */   }
/*    */   
/*    */   public void addObservedDays() {
/* 39 */     this.observedDays += 1;
/*    */   }
/*    */   
/*    */   public String getYear() {
/* 43 */     return this.year;
/*    */   }
/*    */   
/*    */   public double getPRCP(int i) {
/* 47 */     return ((Double)this.prcp.get(i)).doubleValue();
/*    */   }
/*    */   
/* 50 */   public int getObserveredDays() { 
				return this.observedDays; }
/*    */ }


/* Location:              /Users/Yi/Downloads/RunOff_DiffSoils.jar!/Year.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */