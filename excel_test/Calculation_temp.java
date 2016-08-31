 import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Calculation_temp
{
   private String station;
   private String stationName;
   public static int units = 1;
   
   private static boolean isUnitsSelected = false;
   
   //boolean for choosing month or year output
   private static boolean monthOutput = false;
   private static boolean yearOutput = false;
   
   private final double[] cnArray = { 0.0D, 30.0D, 31.0D, 32.0D, 33.0D, 34.0D, 35.0D, 36.0D, 37.0D, 38.0D, 39.0D, 40.0D, 41.0D, 42.0D, 43.0D, 44.0D, 45.0D, 46.0D, 47.0D, 48.0D, 49.0D, 50.0D, 51.0D, 52.0D, 53.0D, 54.0D, 55.0D, 56.0D, 57.0D, 58.0D, 59.0D, 60.0D, 61.0D, 62.0D, 63.0D, 64.0D, 65.0D, 66.0D, 67.0D, 68.0D, 69.0D, 70.0D, 71.0D, 72.0D, 73.0D, 74.0D, 75.0D, 76.0D, 77.0D, 78.0D, 79.0D, 80.0D, 81.0D, 82.0D, 83.0D, 84.0D, 85.0D, 86.0D, 87.0D, 88.0D, 89.0D, 90.0D, 91.0D, 92.0D, 93.0D, 94.0D, 95.0D, 96.0D, 97.0D, 98.0D, 99.0D, 100.0D }; private double[] cnDry = new double[this.cnArray.length]; private double[] cnWet = new double[this.cnArray.length]; private double[] sArray = new double[this.cnArray.length];
    
   void initSArrayInch1()
   {
     for (int i = 0; i < this.sArray.length; i++) {
       this.sArray[i] = (1000.0D / this.cnArray[i] - 10.0D);
     }
   }
   
   void initSArrayInch100()
   {
     for (int i = 0; i < this.sArray.length; i++) {
       this.sArray[i] = (100000.0D / this.cnArray[i] - 1000.0D);
     }
   }
   
   void initSArrayMillimeter1()
   {
     for (int i = 0; i < this.sArray.length; i++) {
       this.sArray[i] = (2540.0D / this.cnArray[i] - 25.4D);
     }
   }   

   void initSArrayMillimeter10()
   {
     for (int i = 0; i < this.sArray.length; i++) {
       this.sArray[i] = (25400.0D / this.cnArray[i] - 254.0D);
     }
   }
   
   public static void main(String[] args)
   {
     final Calculations calc = new Calculations();
     
     JFrame frame = new JFrame("Runoff");
     frame.setDefaultCloseOperation(3);
     
     JButton millimeters = new JButton("millimeter");
     millimeters.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
		Calculations.units = 1;
		calc.initSArrayMillimeter1();
		Calculations.isUnitsSelected = true;
	}
     });

     JButton inches = new JButton("inch");
     inches.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		Calculations.units = 1;
		calc.initSArrayInch1();
		Calculations.isUnitsSelected = true;
       }
     });
     JButton inches100th = new JButton("100th inch");
     inches100th.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		Calculations.units = 100;
		calc.initSArrayInch100();
		Calculations.isUnitsSelected = true;
       }
     });
     JButton millimeters10th = new JButton("10th millimeter");
     millimeters10th.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
		Calculations.units = 10;
		calc.initSArrayMillimeter10();
		Calculations.isUnitsSelected = true;
         //Calculations.units = 10;
         //Calculations.this.initSArrayMillimeter10();
         //Calculations.isUnitsSelected = true;
       }
     });
     
     //add new button for choosing year or month
     //!!!!!!!
     //!!!!!!!!!
     JButton yearButton = new JButton("Year Output");
     yearButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
			Calculations.yearOutput = true;
	       }
     });
     JButton monthButton = new JButton("Month Output");
     monthButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
			Calculations.monthOutput = true;
	       }
     });
     //!!!!!!!!!
     
     
     JLabel directions = new JLabel("Please select the input data's metric system");
     JLabel emptyLabel = new JLabel();
     frame.setLayout(new GridLayout(3, 2));
     frame.add(directions);
     frame.add(emptyLabel);
     frame.add(millimeters);
     frame.add(inches);
     frame.add(inches100th);
     frame.add(millimeters10th);
     frame.pack();
     frame.setVisible(true);
     
     while (!isUnitsSelected) {
       System.out.println("select units");
     }
     
     
     millimeters.setVisible(false);
     inches.setVisible(false);
     inches100th.setVisible(false);
     millimeters10th.setVisible(false);
     directions.setText("Please wait.. results in \"Results\" folder");
     
     
     directions.setVisible(false);
     emptyLabel.setVisible(false);
     millimeters.setVisible(false);
     inches.setVisible(false);
     inches100th.setVisible(false);
     millimeters10th.setVisible(false);
     frame.remove(directions);
     frame.remove(emptyLabel);
     frame.remove(millimeters);
     frame.remove(inches);
     frame.remove(inches100th);
     frame.remove(millimeters10th);
     
     
     frame.setLayout(new GridLayout(2, 2));
     frame.add(emptyLabel);
     frame.add(yearButton);
     frame.add(monthButton);

     
     while (!yearOutput && !monthOutput) {
         System.out.println("select Output type");
       }
  
     yearButton.setVisible(false);
     monthButton.setVisible(false);
     directions.setText("Please wait.. results in \"Results\" folder");
     
  
     long startTime = System.currentTimeMillis();
     
     ArrayList<Year> years = new ArrayList();
     //added new list for Month
     ArrayList<Month> months = new ArrayList();
     
     File folder = new File(".");
     
     File[] listOfFiles = folder.listFiles();
     String[] csvFilePaths = new String[listOfFiles.length];
     String[] csvFileNames = new String[listOfFiles.length];
     int counter = 0;
     
     for (int i = 0; i < listOfFiles.length; i++) {
       File file = listOfFiles[i];
       if ((file.isFile())&& (file.getName().endsWith(".csv"))) {
    	   if(yearOutput){
    		   years = calc.readData(file.getAbsolutePath());
    	   }else{
    	      months = calc.readData_month(file.getAbsolutePath());
       }
    	 
         csvFilePaths[counter] = file.getAbsolutePath();
         csvFileNames[counter] = file.toString();
       
         if(yearOutput){
        	 calc.generateOutput(file.getName(), null, years); 
         }else{
        	 calc.generateOutput_month(file.getName(), null, months);
         }
         counter++;
       }
     }
    
     long estimatedTime = System.currentTimeMillis() - startTime;
     
     System.out.println("Running time : " + estimatedTime + " seconds");
     
     frame.dispatchEvent(new WindowEvent(frame, 201));
   }
   
 
   ArrayList<Year> readData(String filename)
   {
     ArrayList<Year> years = new ArrayList();
     BufferedReader br = null;
     String line = "";
    
     try
     {
       br = new BufferedReader(new FileReader(filename));
       
       line = br.readLine();
       String[] columnHeaders = line.split(",");
        
       line = br.readLine();
       String[] dataConstants = line.split(",");
       int dateIndex = 0;
       int prcpIndex = 0;
       Year year = new Year();
 
       for (int i = 0; i < dataConstants.length; i++) {
         if (columnHeaders[i].toLowerCase().equals("station")) {
           this.station = dataConstants[i];
         } else if (columnHeaders[i].toLowerCase().equals("station_name")) {
           this.stationName = dataConstants[i];
         } else if (columnHeaders[i].toLowerCase().equals("date")) {
           dateIndex = i;
           year.setYear(dataConstants[i].substring(0, 4));
         } else if (columnHeaders[i].toLowerCase().equals("prcp")) {
           prcpIndex = i;
           year.addPRCP(Double.parseDouble(dataConstants[i]));
           year.addObservedDays();
         }
       }
      while ((line = br.readLine()) != null)
       {
         String[] tokens = line.split(",");
         if (!tokens[dateIndex].substring(0, 4).equals(year.getYear())) {
					//at this point the information for last year is got, move to next year
					//this code works because the year is list from oldest to now
           years.add(year);
           year = new Year();
           year.setYear(tokens[dateIndex].substring(0, 4));
         }
         year.addPRCP(Double.parseDouble(tokens[prcpIndex]));
         year.addObservedDays();
       }
       
       years.add(year);
       
       br.close();
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     
 
     return years;
   }  
 
   void generateOutput(String filename, String filePath, ArrayList<Year> years)
   {
	 //Blank workbook
	   XSSFWorkbook workbook = new XSSFWorkbook(); 
	   
	   //Create a blank sheet
	   XSSFSheet sheet = workbook.createSheet("Rain fall Output");
    
	   //create map to store first row elements
	   Map<String, Object[]> data = new TreeMap<String, Object[]>();
	   data.put("1", new Object[] { filename.substring(filename.indexOf("\\") + 1, filename.indexOf(".")) });
	   data.put("2", new Object[] { this.station });
	   data.put("3", new Object[] { this.stationName });
	   data.put("4", new Object[] { "Version 1.0" });
	   data.put("5", new Object[] { "ObservedYears:" + Integer.valueOf(years.size()) });
	   data.put("6", new Object[] { "DataUnits:mm" });
	   
	   Set<String> keyset = data.keySet();
	   Row row = sheet.createRow(0);
	   int cellnum = 0;
	   for(String key: keyset){
		   Object [] objArr = data.get(key);
		   for(Object obj : objArr){
			   Cell cell = row.createCell(cellnum++);
			   cell.setCellValue((String)obj);
		   }
	   }
	   
	   //create row for CNVALUE
	   Row row_cnvalue = sheet.createRow(1);
	   int cellnum_cn = 0;
	   for(int i =0; i< this.cnArray.length; i++){
		   Cell cell = row_cnvalue.createCell(cellnum_cn++);
		   if(this.cnArray[i] < 10.0D){
			   cell.setCellValue("CN00" + Double.valueOf(this.cnArray[i]));
		   }
		   if((this.cnArray[i] >= 10.0D) && (this.cnArray[i] < 100.0D)){
			   cell.setCellValue("CN0" + Double.valueOf(this.cnArray[i]));
		   }
		   if((this.cnArray[i] >= 100.0D) && (this.cnArray[i] < 1000.0D)){
			   cell.setCellValue("CN" + Double.valueOf(this.cnArray[i]));
		   }
	   }
	   
	   //create row for every year's value
	   int row_year = 2;
	   int row_average_num = 2;
	   for (int i=0; i<years.size(); i++){
		   Row row_for_year = sheet.createRow(row_year+i);
		   int cellnum_year = 0;
		   Cell cell = row_for_year.createCell(cellnum_year++);
		   cell.setCellValue("Year: "+Double.valueOf(Double.parseDouble(years.get(i).getYear())));
		   for (int j = 0; j < this.cnArray.length; j++)
	       {
	         ArrayList<Double> qValues = new ArrayList();
	         for (int k = 0; k < years.get(i).getObserveredDays(); k++) {
	           if (years.get(i).getPRCP(k) >= 0.2D * this.sArray[j])
	           {
	             qValues.add(Double.valueOf(Math.pow(years.get(i).getPRCP(k) - 0.2D * this.sArray[j], 2.0D) / (years.get(i).getPRCP(k) + 0.8D * this.sArray[j])));
	           }
	         }
	         double sum = 0.0D;
	         
	         for (int k = 0; k < qValues.size(); k++) {
	           sum += qValues.get(k).doubleValue();
	         }
	         //After this part of codes, output the year's CN value
	         if(Double.isNaN(sum)) {
	  		   Cell cell_year = row_for_year.createCell(cellnum_year++);
			   cell_year.setCellValue(Double.valueOf(0.0D));
	         } else {
	  		   Cell cell_year = row_for_year.createCell(cellnum_year++);
	  		   cell_year.setCellValue(Double.valueOf(sum));
	         }
	       }
		   row_average_num = row_year+i;
	   }
	   
	   //Create one more row which contains the average CN values
	   //Need to know : 1. how many years need to be included
	   //				2. how many columns depend on cnArray
	   //				3. First two rows are not used
	   
	   row_average_num = row_average_num+1;
	   Row row_average = sheet.createRow(row_average_num);
	   Cell cell_avg_fir = row_average.createCell(0);
	   cell_avg_fir.setCellValue("Average CN Value");
	   for (int i =1; i<this.cnArray.length+1; i++){
		   Cell avg_cn = row_average.createCell(i);
		   String formula=String.format("SUM(%s%d:%s%d)/%d",CellReference.convertNumToColString(i), 3, CellReference.convertNumToColString(i), row_average_num, years.size());
		   avg_cn.setCellFormula(formula);
	   }
	   
	   
	   try 
	   {
		   //Write the workbook in file system
		   CreateDir("Results");
		   FileOutputStream out = new FileOutputStream(new File("Results//" + "howtodoinjava_demo.xlsx"));
		   workbook.write(out);
		   out.close();
		   System.out.println("howtodoinjava.xlsx written successfully on disk.");
	   }
	   catch (Exception e) 
	   {
		   e.printStackTrace();
	   }	
   }
	 
   void CreateDir(String dirAddress)
   {
     File theDir = new File(dirAddress);
      
     if (!theDir.exists()) {
       System.out.println("creating directory: " + dirAddress);
       boolean result = false;
       try
       {
         theDir.mkdir();
         result = true;
       }
       catch (SecurityException localSecurityException) {}       
       if (result) {
         System.out.println("DIR created");
       }
     }
   }
   
   void initializeCNdry()
   {
     int i = 0;
     for (i = 0; i < this.cnArray.length; i++) {
       this.cnDry[i] = (0.39D * this.cnArray[i] * Math.exp(0.009D * this.cnArray[i]));
     }
   }
   
   void initializeCNwet()
   {
     int i = 0;
     for (i = 0; i < this.cnArray.length; i++) {
       this.cnWet[i] = (1.95D * this.cnArray[i] * Math.exp(-0.00663D * this.cnArray[i]));
     }
   }
   
   
   //added function by Yiyi Chen
   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
   //added generate output for month!!!!!!!
   //!!!!!!!!!!!!!!!
   void generateOutput_month(String filename, String filePath, ArrayList<Month> months)
   {
	 //Blank workbook
	   XSSFWorkbook workbook = new XSSFWorkbook(); 
	   
	   //Create a blank sheet
	   XSSFSheet sheet = workbook.createSheet("Rain fall Output");
    
	   //create map to store first row elements
	   Map<String, Object[]> data = new TreeMap<String, Object[]>();
	   data.put("1", new Object[] { filename.substring(filename.indexOf("\\") + 1, filename.indexOf(".")) });
	   data.put("2", new Object[] { this.station });
	   data.put("3", new Object[] { this.stationName });
	   data.put("4", new Object[] { "Version 1.0" });
	   data.put("5", new Object[] { "ObservedYears:" + Integer.valueOf(months.size()) });
	   data.put("6", new Object[] { "DataUnits:mm" });
	   
	   Set<String> keyset = data.keySet();
	   Row row = sheet.createRow(0);
	   int cellnum = 0;
	   for(String key: keyset){
		   Object [] objArr = data.get(key);
		   for(Object obj : objArr){
			   Cell cell = row.createCell(cellnum++);
			   cell.setCellValue((String)obj);
		   }
	   }
	   
	   //create row for CNVALUE
	   Row row_cnvalue = sheet.createRow(1);
	   int cellnum_cn = 0;
	   for(int i =0; i< this.cnArray.length; i++){
		   Cell cell = row_cnvalue.createCell(cellnum_cn++);
		   if(this.cnArray[i] < 10.0D){
			   cell.setCellValue("CN00" + Double.valueOf(this.cnArray[i]));
		   }
		   if((this.cnArray[i] >= 10.0D) && (this.cnArray[i] < 100.0D)){
			   cell.setCellValue("CN0" + Double.valueOf(this.cnArray[i]));
		   }
		   if((this.cnArray[i] >= 100.0D) && (this.cnArray[i] < 1000.0D)){
			   cell.setCellValue("CN" + Double.valueOf(this.cnArray[i]));
		   }
	   }
	   
	   //create row for every year's value
	   int row_month = 2;
	   int row_average_num = 2;
	   for (int i=0; i<months.size(); i++){
		   Row row_for_month = sheet.createRow(row_month+i);
		   int cellnum_month = 0;
		   Cell cell = row_for_month.createCell(cellnum_month++);
		   cell.setCellValue("Month: "+Double.valueOf(Double.parseDouble(months.get(i).getMonth())));
		   for (int j = 0; j < this.cnArray.length; j++)
	       {
	         ArrayList<Double> qValues = new ArrayList();
	         for (int k = 0; k < months.get(i).getObserveredDays(); k++) {
	           if (months.get(i).getPRCP(k) >= 0.2D * this.sArray[j])
	           {
	             qValues.add(Double.valueOf(Math.pow(months.get(i).getPRCP(k) - 0.2D * this.sArray[j], 2.0D) / (months.get(i).getPRCP(k) + 0.8D * this.sArray[j])));
	           }
	         }
	         double sum = 0.0D;
	         
	         for (int k = 0; k < qValues.size(); k++) {
	           sum += qValues.get(k).doubleValue();
	         }
	         //After this part of codes, output the year's CN value
	         if(Double.isNaN(sum)) {
	  		   Cell cell_year = row_for_month.createCell(cellnum_month++);
			   cell_year.setCellValue(Double.valueOf(0.0D));
	         } else {
	  		   Cell cell_year = row_for_month.createCell(cellnum_month++);
	  		   cell_year.setCellValue(Double.valueOf(sum));
	         }
	       }
		   row_average_num = row_month+i;
	   }
	   
	   //Create one more row which contains the average CN values
	   //Need to know : 1. how many years need to be included
	   //				2. how many columns depend on cnArray
	   //				3. First two rows are not used
	   
	   row_average_num = row_average_num+1;
	   Row row_average = sheet.createRow(row_average_num);
	   Cell cell_avg_fir = row_average.createCell(0);
	   cell_avg_fir.setCellValue("Average CN Value");
	   for (int i =1; i<this.cnArray.length+1; i++){
		   Cell avg_cn = row_average.createCell(i);
		   String formula=String.format("SUM(%s%d:%s%d)/%d",CellReference.convertNumToColString(i), 3, CellReference.convertNumToColString(i), row_average_num, months.size());
		   avg_cn.setCellFormula(formula);
	   }
	   
	   
	   try 
	   {
		   //Write the workbook in file system
		   CreateDir("Results");
		   FileOutputStream out = new FileOutputStream(new File("Results//" + "howtodoinjava_demo.xlsx"));
		   workbook.write(out);
		   out.close();
		   System.out.println("howtodoinjava.xlsx written successfully on disk.");
	   }
	   catch (Exception e) 
	   {
		   e.printStackTrace();
	   }	
   }
   
   //added read data for month!!!!!!!
   //!!!!!!!!!!!!!!!
   ArrayList<Month> readData_month(String filename)
   {
        ArrayList<Month> months = new ArrayList();
        BufferedReader br = null;
        String line = "";
       try
        {
          br = new BufferedReader(new FileReader(filename));
          
          line = br.readLine();
          String[] columnHeaders = line.split(",");

          line = br.readLine();
          String[] dataConstants = line.split(",");
          int dateIndex = 0;
          int prcpIndex = 0;
          Month month = new Month();
          
          for (int i = 0; i < dataConstants.length; i++) {
            if (columnHeaders[i].toLowerCase().equals("station")) {
              this.station = dataConstants[i];
            } else if (columnHeaders[i].toLowerCase().equals("station_name")) {
              this.stationName = dataConstants[i];
            } else if (columnHeaders[i].toLowerCase().equals("date")) {
              dateIndex = i;
              month.setMonth(dataConstants[i].substring(0, 6));
            } else if (columnHeaders[i].toLowerCase().equals("prcp")) {
              prcpIndex = i;
              month.addPRCP(Double.parseDouble(dataConstants[i]));
              month.addObservedDays();
            }
          }
           
          while ((line = br.readLine()) != null)
          {
            String[] tokens = line.split(",");
            if (!tokens[dateIndex].substring(0, 6).equals(month.getMonth())) {
   					//at this point the information for last year is got, move to next year
   					//this code works because the year is list from oldest to now
              months.add(month);
              month = new Month();
              month.setMonth(tokens[dateIndex].substring(0, 6));
              System.out.println(dataConstants[dateIndex].substring(0, 6));
            }
            month.addPRCP(Double.parseDouble(tokens[prcpIndex]));
            month.addObservedDays();
          }
         
          months.add(month);
          
          br.close();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
         
        return months;
      }
 }


/* Location:              /Users/Yi/Downloads/RunOff_DiffSoils.jar!/Calculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */