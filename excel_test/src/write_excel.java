import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class write_excel {
    public static void main(String[] args) 
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("EventLthial_2016.xlsx"));
 
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
    		Row row = sheet.getRow(3);
    		
    		Cell cell = row.getCell(9); 
    	
    		if (cell!=null) {
    		    switch (evaluator.evaluateInCell(cell).getCellType()) {
    		        case Cell.CELL_TYPE_BOOLEAN:
    		            System.out.println(cell.getBooleanCellValue());
    		            break;
    		        case Cell.CELL_TYPE_NUMERIC:
    		            System.out.println((int)cell.getNumericCellValue()+"hahaha");
    		            break;
    		        case Cell.CELL_TYPE_STRING:
    		            System.out.println(cell.getStringCellValue());
    		            break;
    		        case Cell.CELL_TYPE_BLANK:
    		            break;
    		        case Cell.CELL_TYPE_ERROR:
    		            System.out.println(cell.getErrorCellValue());
    		            break;
    	
    		        // CELL_TYPE_FORMULA will never occur
    		        case Cell.CELL_TYPE_FORMULA:
    		            break;
   			    }
   			}
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
}
