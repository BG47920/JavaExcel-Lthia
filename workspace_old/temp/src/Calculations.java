 import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
import java.util.Arrays;

/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;

public class Calculations
{
   private String station;
   private String stationName;
   public static int units = 1;
   
   private static boolean isUnitsSelected = false;
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
		Calculations.units = 1;Calculations.units = 1;
		calc.initSArrayInch1();
		Calculations.isUnitsSelected = true;
         //Calculations.units = 1;
		 //Calculations.this.initSArrayInch1();
         //Calculations.isUnitsSelected = true;
       }
     });
     JButton inches100th = new JButton("100th inch");
     inches100th.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		Calculations.units = 1;
		calc.initSArrayInch100();
		Calculations.isUnitsSelected = true;
         //Calculations.units = 100;
         //Calculations.this.initSArrayInch100();
         //Calculations.isUnitsSelected = true;
       }
     });
     JButton millimeters10th = new JButton("10th millimeter");
     millimeters10th.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
		Calculations.units = 1;
		calc.initSArrayMillimeter10();
		Calculations.isUnitsSelected = true;
         //Calculations.units = 10;
         //Calculations.this.initSArrayMillimeter10();
         //Calculations.isUnitsSelected = true;
       }
     });
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
  
     long startTime = System.currentTimeMillis();
     
     ArrayList<Year> years = new ArrayList();
     
     File folder = new File(".");
     
     File[] listOfFiles = folder.listFiles();
     String[] csvFilePaths = new String[listOfFiles.length];
     String[] csvFileNames = new String[listOfFiles.length];
     int counter = 0;
     
     for (int i = 0; i < listOfFiles.length; i++) {
       File file = listOfFiles[i];
       if ((file.isFile())&& (file.getName().endsWith(".csv"))) {
         years = calc.readData(file.getAbsolutePath());
         csvFilePaths[counter] = file.getAbsolutePath();
         csvFileNames[counter] = file.toString();
       
         calc.generateOutput(file.getName(), null, years);
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
     FileWriter write = null;
     
     try
     {
       System.out.println("filename: " + filename);
        
       CreateDir("Results");
       write = new FileWriter("Results//" + filename.substring(0, filename.indexOf(".")) + "q.txt");
     } catch (FileNotFoundException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     PrintWriter outf = new PrintWriter(write);
       
     outf.printf("%s\t", new Object[] { filename.substring(filename.indexOf("//") + 1, filename.indexOf(".")) });
     outf.printf("%s\t", new Object[] { this.station });
     outf.printf("%s\t", new Object[] { this.stationName });
     outf.printf("%s\t", new Object[] { "Version 1.0" });
     outf.printf("ObservedYears:%d", new Object[] { Integer.valueOf(years.size()) });
     outf.printf("\tDataUnits:mm%n", new Object[0]);
     outf.printf("CNvalue", new Object[0]);
     outf.print("\t\t");
     
     for (int i = 0; i < this.cnArray.length; i++)
     {
       if (this.cnArray[i] < 10.0D) {
         outf.printf("CN00%.0f", new Object[] { Double.valueOf(this.cnArray[i]) });
         outf.print("\t");
       }
       
       if ((this.cnArray[i] >= 10.0D) && (this.cnArray[i] < 100.0D)) {
         outf.printf("CN0%.0f", new Object[] { Double.valueOf(this.cnArray[i]) });
         outf.print("\t");
       }
       
       if ((this.cnArray[i] >= 100.0D) && (this.cnArray[i] < 1000.0D)) {
         outf.printf("CN%.0f", new Object[] { Double.valueOf(this.cnArray[i]) });
         outf.print("\t");
       }
     }
     
     outf.println(); 
     for (int i = 0; i < years.size(); i++) {
       outf.printf("Year: %.0f", new Object[] { Double.valueOf(Double.parseDouble(years.get(i).getYear())) });
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
         
         if (Double.isNaN(sum)) {
           outf.printf("%8.2f", new Object[] { Double.valueOf(0.0D) });
         } else {
           outf.printf("%8.2f", new Object[] { Double.valueOf(sum) });
         }
       }
       outf.println();
     }
    
     try
     {
       write.close();
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     outf.close();
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
 }


/* Location:              /Users/Yi/Downloads/RunOff_DiffSoils.jar!/Calculations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */