package Lepric.Tools;

import Lepric.Interfaceses.IGetInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class PrintTo implements IGetInfo{
    Map<String, Object[]> data = new HashMap<>();
    private String Directory=".";
    
    public void Put(String wordData,String extension) throws IOException{
        data.put(String.valueOf((double)(data.size()+1)), new Object[] {(double)(data.size()+1), wordData+extension,"Kelime sayısı : "+wordData.length(),});
    }
    public void Print(String Directory) throws IOException{
        this.Directory=Directory;    
        Excel(data);     
    }
    public void Print(Map<String, Object[]> data){
        Excel(data);     
    }
    
    private void Excel(Map<String, Object[]> data){
        System.out.println("Yazdırmaya başlıyorum");     
           HSSFWorkbook workbook = new HSSFWorkbook();
           HSSFSheet sheet = workbook.createSheet("Alınabilir Alan Adları");
           
            //Basliklar icin bicim nesnesini olustur
           HSSFCellStyle headerStyle = workbook.createCellStyle();
          
           //Basliklari Hazirla
           Row headerRow = sheet.createRow(0);
           Cell hindex = headerRow.createCell(0);
           hindex.setCellValue("Adet");

           Cell hdomain = headerRow.createCell(1);
           hdomain.setCellValue("Alan adı");

           Cell havailability = headerRow.createCell(2);
           havailability.setCellValue("Kelime sayısı");
           
           
           
           hindex.setCellStyle(headerStyle);
           hdomain.setCellStyle(headerStyle);
           havailability.setCellStyle(headerStyle);
                   
            Set<String> keyset = data.keySet();
            int rownum = 1;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);

                    if(obj instanceof Date) 

                        cell.setCellValue((Date)obj);

                    else if(obj instanceof Boolean)

                        cell.setCellValue((Boolean)obj);

                    else if(obj instanceof String)

                        cell.setCellValue((String)obj);

                    else if(obj instanceof Double)

                        cell.setCellValue((Double)obj);

                }
            }
            try {

                FileOutputStream out = new FileOutputStream(new File(Directory+"/Alınabilir domain listesi.xls"));
                workbook.write(out);
                out.close();
                System.out.println("Excel yazıldı..");
            }   catch (FileNotFoundException e) {
                    System.err.println(e);
            }   catch (IOException e) {
                    System.err.println(e);
            }
 
    }
    
    
    private void Txt(){
        try {
            FileWriter filewriter = new FileWriter(this.Directory);
            PrintWriter printWriter = new PrintWriter(filewriter);
            
        } catch (IOException ex) {
            Logger.getLogger(PrintTo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void Database(){
        System.out.println("Not yet Supported");
    }

    @Override
    public void PrintInfo() {
        System.out.println("Directory(Eğer nokta ise bulunduğu konum anlamına gelir): "+Directory);
    }
    
}
    
        








   

