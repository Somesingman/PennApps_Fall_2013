import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	static final String VOICE = "voice";
	static final String KEY = "key";
	static final String ATTRIBUTES = "attributes";
	static final String MODE = "mode";
	static final String FIFTHS = "fifths";


	@SuppressWarnings({ "unchecked", "null" })
	public void readConfig(String configFile) {
		List<Part> parts = new ArrayList<Part>();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			System.out.println(configFile);
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			FileWriter fw = new FileWriter("newXML.xml");

			BufferedWriter bw = new BufferedWriter(fw);

			// Read the XML document
			Part part = null;
			String mode = "";
			int fifths = 0;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				bw.write(event.toString());
				if (event.isStartElement() && 
						event.asStartElement().getName().toString().equals(PART)){

					while(eventReader.hasNext()){
						event = eventReader.nextEvent();
						//System.out.println("GOES");
						boolean endMeasure = false;
						if(event.isStartElement() && 
								event.asStartElement().getName().toString().equals(MEASURE)){
							//System.out.println("GOES INTO MEASURE");
							ArrayList<Note> measure = new ArrayList<Note>();
							int counter = 0;
							boolean endAttribute = false;

							while(eventReader.hasNext() && !endMeasure){
								event = eventReader.nextEvent();

								if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(ATTRIBUTES)){

									while(eventReader.hasNext() && !endAttribute){

										if(event.isEndElement() &&
												event.asEndElement().getName().toString().equals(ATTRIBUTES)){
											endAttribute = true;
											bw.write(event.toString());
										}
										else if(event.isStartElement()){
											String currentTag = event.asStartElement().getName().toString();
											event = eventReader.nextEvent();
											if(currentTag.equals(KEY)){

												boolean endKey = false;

												while(eventReader.hasNext() && !endKey){

													if(event.isEndElement() &&
															event.asEndElement().getName().toString().equals(KEY)){
														endKey = true;
														bw.write(event.toString());
													}
													if(event.isStartElement()){

														System.out.println("HELLO");
														currentTag = event.asStartElement().getName().toString();

														if(event.isStartElement() &&
																event.asStartElement().getName().toString().equals(FIFTHS)){

															event = eventReader.nextEvent();

															if(event.isCharacters()){
																String character = event.asCharacters().getData().toString();
																int num = Integer.parseInt(character);
																fifths = num;
															}
														}

														else if(event.isStartElement() && 
																event.asStartElement().getName().toString().equals(MODE)){

															event = eventReader.nextEvent();

															if(event.isCharacters()){
																String character = event.asCharacters().getData().toString();
																mode = character;
															}
														}

													}
													if(eventReader.hasNext() && !endKey)
														event = eventReader.nextEvent();
												}
											}
										}
										else
											bw.write(event.toString());
										if(eventReader.hasNext() && !endAttribute)
											event = eventReader.nextEvent();
									}
								}
								else if(event.isEndElement() &&
										event.asEndElement().getName().toString().equals(MEASURE))
									endMeasure = true;

								//New Note
								else if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(NOTE)){
									//System.out.println("Goes in here and mode = " + mode + "\n fifth = " + fifth);
									Note current = new Note();
									current.setMode(mode);
									current.setFifth(fifths);
									boolean endNote = false;
									String currentTag = "";

									while(eventReader.hasNext() && !endNote){

										if(event.isEndElement() &&
												event.asEndElement().getName().toString().equals(NOTE))
											endNote = true;

										if(event.isStartElement()){
											currentTag = event.asStartElement().getName().toString();

											if(currentTag.equals(CHORD))
												current.setChord(true);
										}

										if(event.isCharacters()){
											String character = event.asCharacters().getData().toString();

											//System.out.println(currentTag + " : " + character);
											if(currentTag.equals(STEP) && current.getStep().length()!=1)
												current.setStep(character);

											if(currentTag.equals(DURATION) && current.getDuration() == 0){
												current.setPosition(counter);
												current.setDuration(Integer.parseInt(character));
												counter = counter + current.getDuration();
											}
											if(currentTag.equals(OCTAVE) && current.getOctave() == 0)
												current.setOctave(Integer.parseInt(character));

											if(currentTag.equals(ALTER) && current.getAlter() == 0)
												current.setAlter(Integer.parseInt(character));

											if(currentTag.equals(VOICE) && current.getVoice() == 0)
												current.setVoice(Integer.parseInt(character));
										}
										//System.out.println(event.asCharacters().getData());
										event = eventReader.nextEvent();

									}
									System.out.println(current);
									System.out.println("********");
									measure.add(current);

									//System.out.println(measure.size());
								}
								else if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(BACKUP)){
									event = eventReader.nextEvent();
									if(event.isStartElement() && event.asStartElement().getName().toString().equals(DURATION)){
										event = eventReader.nextEvent();
										if(event.isCharacters()){
											String character = event.asCharacters().getData().toString();
											int num = Integer.parseInt(character);
											counter = counter - num;
										}
									}



								}
								else
									bw.write(event.toString());
							}

						}
						else
							bw.write(event.toString());
					}
				}
				/*
				System.out.println(event);
				System.out.println("Attribute: " + event.isAttribute());
				System.out.println("Character: " + event.isCharacters());
				System.out.println("End Document: " + event.isEndDocument());
				System.out.println("StartElement: " + event.isStartElement());
				System.out.println("EndElement: " + event.isEndElement());
				System.out.println("********************");
				 */
			}

			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
} 