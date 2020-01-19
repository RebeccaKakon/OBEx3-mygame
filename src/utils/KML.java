package utils;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class KML {
   
    private StringBuffer kmlstring;
    
    private int numberofgame;

    public KML(int numberofgame) 
    {
        this.numberofgame = numberofgame;
        kmlstring = new StringBuffer();
        KMLstart();
    }
    

    /**
     * this function initialize the working platform
     * attribute icon to node
     * attribute icon to robot
     * attribute icon to fruit
     * the icon for fruit associated to type -1 (purple) will be different from fruit associated to type 1 (red)
     */
    private void KMLstart()
    {
    	kmlstring.append(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                        "<kml xmlns=\"http://earth.google.com/kml/2.2\">\r\n" +
                        "  <Document>\r\n" +
                        "    <name>" + "Game stage :"+numberofgame + "</name>" +"\r\n"+
                        " <Style id=\"node\">\r\n" +
                        "      <IconStyle>\r\n" +
                        "        <Icon>\r\n" +
                        "          <href>http://maps.google.com/mapfiles/kml/pal3/icon35.png</href>\r\n" +
                        "        </Icon>\r\n" +
                        "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
                        "      </IconStyle>\r\n" +
                        "    </Style>" +
                        " <Style id=\"fruit_-1\">\r\n" +
                        "      <IconStyle>\r\n" +
                        "        <Icon>\r\n" +
                        "          <href>http://maps.google.com/mapfiles/kml/paddle/purple-stars.png</href>\r\n" +
                        "        </Icon>\r\n" +
                        "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
                        "      </IconStyle>\r\n" +
                        "    </Style>" +
                        " <Style id=\"fruit_1\">\r\n" +
                        "      <IconStyle>\r\n" +
                        "        <Icon>\r\n" +
                        "          <href>http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>\r\n" +
                        "        </Icon>\r\n" +
                        "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
                        "      </IconStyle>\r\n" +
                        "    </Style>" +
                        " <Style id=\"robot\">\r\n" +
                        "      <IconStyle>\r\n" +
                        "        <Icon>\r\n" +
                        "          <href>http://maps.google.com/mapfiles/kml/shapes/motorcycling.png></href>\r\n" +
                        "        </Icon>\r\n" +
                        "        <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/>\r\n" +
                        "      </IconStyle>\r\n" +
                        "    </Style>"
        );
    }

    public void Place_Mark(String id, String location)
    {
    	System.out.println("placemark");
        LocalDateTime time = LocalDateTime.now();
        kmlstring.append(" <Placemark>\r\n" +
                        "      <TimeStamp>\r\n" +
                        "        <when>" + time+ "</when>\r\n" +
                        "      </TimeStamp>\r\n" +
                        "      <styleUrl>#" + id + "</styleUrl>\r\n" +
                        "      <Point>\r\n" +
                        "        <coordinates>" + location + "</coordinates>\r\n" +
                        "      </Point>\r\n" +
                        "    </Placemark>\r\n"
        );
    }

    public void kmlfinishgame()
    {
    	System.out.println("finish the game funciton");
    	kmlstring.append("  \r\n</Document>\r\n" +
                "</kml>");
        SaveFile();
    }

    private void SaveFile(){
        try
        {
            File file=new File(numberofgame+".kml");
            PrintWriter pw=new PrintWriter(file);
            pw.write(kmlstring.toString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
