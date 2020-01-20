package Tests;



import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import utils.KML_Logger;
import utils.Point3D;

class KMLLogger_test {
	@Test
	public void KML_Logger() {
		KML_Logger kml= new KML_Logger(24);
        double pos_x = 35.207151268054346;
        double pos_y = 32.10259023385377;
        for (int i = 0; i < 15; i++) {
            Point3D p = new Point3D(pos_x + i, pos_y + i);
                    
                    kml.Place_Mark("0", p.toString());
                   
            }
        
        kml.kmlfinishgame();
    }
	
	 @Test
	    void SaveFile()  {
	        try {
	            BufferedReader kmlin = new BufferedReader(new FileReader("24.kml"));
	            if(kmlin.readLine()==null)
	            	fail();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
