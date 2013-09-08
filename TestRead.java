import java.util.List;

public class TestRead {
  public static void main(String args[]) {
    StaXParser read = new StaXParser();
    read.readConfig("MusicXML.xml");
  }
}
