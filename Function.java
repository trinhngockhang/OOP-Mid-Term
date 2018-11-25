import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Function {
	public static ArrayList readFile(String filename,String level) {
	
		ArrayList<String[]> lines = new ArrayList<String[]>();
		try {
	    	   //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
	    	   FileInputStream fis = new FileInputStream(filename);
	    	   DataInputStream dis = new DataInputStream(fis);

	    	   //Bước 2: Đọc dữ liệu
	    	   StringBuffer inputLine = new StringBuffer();
	            String tmp;  
	            while ((tmp = dis.readLine()) != null) {
	            	
	            	String[] arr = new String[3];
	            	int firstKarma = tmp.indexOf(",");
	            	arr[0] =  tmp.substring(0, firstKarma);
	            	if(arr[0].equals(level)) {
	            		int secondKarma = tmp.indexOf(",",firstKarma+1);
		            	arr[1] =  tmp.substring(firstKarma + 1, secondKarma);
		            	arr[2] = tmp.substring(secondKarma + 1, tmp.length());
		                lines.add(arr);
	            	}
	            	
	            }
	    	   //Bước 3: Đóng luồng
	    	   fis.close();
	    	   dis.close();
	    	   return lines;
	    	  } catch (IOException ex) {
	    	    ex.printStackTrace();
	    	  }	
		return lines;
	}
	
	public static void appendText(String filename,String text) {
		FileOutputStream file = null;
        DataOutputStream data = null;
        try {
            file = new FileOutputStream(filename,true);
            data = new DataOutputStream(file);
           // data.;
           
            data.writeUTF(text);
            data.flush();
            data.close();
            file.close();
        } catch (IOException ex) {
 
        } 
	}
	
}
