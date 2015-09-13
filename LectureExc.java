import jxl.*;
import jxl.Workbook;
import jxl.read.biff.*;
import java.io.File;
import java.io.IOException;
import jxl.write.WriteException;
import java.util.Locale;
class LectureExc{
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, WriteException, BiffException{
		// TODO Auto-generated method stub
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File("E:\\2semestre\\java\\test\\memoire.xls"));
			
			Sheet sheet = workbook.getSheet(0);
			
			Cell b1 = sheet.getCell(0,0); 
			
			Cell c1 = sheet.getCell(16,2);
			
			String contenuA1= b1.getContents();
			String contenuA5 =c1.getContents();
			
			System.out.println(contenuA1);
		 	System.out.println(contenuA5);
		} 
		catch (BiffException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if(workbook!=null){
				/* On ferme le worbook pour libérer la mémoire */
				workbook.close(); 
			}
		}
        }
}
