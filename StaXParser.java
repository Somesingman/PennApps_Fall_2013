import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class StaXParser {
  static final String PART = "part";
  static final String MEASURE = "measure";
  static final String NOTE = "note";
  static final String PITCH = "pitch";
  static final String STEP = "step";
  static final String ALTER = "alter";
  static final String OCTAVE = "octave";
  static final String CHORD = "chord";
  static final String TIE = "tie";
  static final String BACKUP =  "backup";
  static final String DURATION = "duration";
  static final String MODE = "mode";

  @SuppressWarnings({ "unchecked", "null" })
  public List<Part> readConfig(String configFile) {
    List<Part> parts = new ArrayList<Part>();
    try {
      // First create a new XMLInputFactory
      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      // Setup a new eventReader
      InputStream in = new FileInputStream(configFile);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
      // Read the XML document
      Part part = null;

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.asStartElement().getName().equals(PART)){

          if (event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            // If we have a item element we create a new item
            if (startElement.getName().getLocalPart() == (PART)) {
              part = new Part();
              

            }
          }
        }

        System.out.println(event);

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
    return null;
  }

} 