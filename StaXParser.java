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
	static final String VOICE = "voice";
	static final String KEY = "key";
	static final String ATTRIBUTE = "attribute";
	static final String MODE = "mode";


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
			// Read the XML document
			Part part = null;

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement() && 
						event.asStartElement().getName().toString().equals(PART)){

					while(eventReader.hasNext()){
						event = eventReader.nextEvent();

						if(event.isStartElement() && 
								event.asStartElement().getName().toString().equals(MEASURE)){
							ArrayList<Note> measure = new ArrayList<Note>();
							int counter = 0;
							boolean endAttribute = false;
							String mode = "";
							while(eventReader.hasNext()){
								event = eventReader.nextEvent();

								if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(ATTRIBUTE)){

									while(eventReader.hasNext() && !endAttribute){

										if(event.isEndElement() &&
												event.asEndElement().getName().toString().equals(ATTRIBUTE))
											endAttribute = true;

										if(event.isStartElement()){
											String currentTag = event.asStartElement().getName().toString();

											if(currentTag.equals(KEY)){
												boolean endKey = false;

												while(eventReader.hasNext() && !endKey){

													if(event.isEndElement() &&
															event.asEndElement().getName().toString().equals(KEY))
														endKey = true;

													if(event.isStartElement()){
														currentTag = event.asStartElement().getName().toString();

														if(currentTag.equals(MODE)){

															event = eventReader.nextEvent();

															if(event.isCharacters()){
																String character = event.asCharacters().getData().toString();
																mode = character;
															}
														}
													}
												}
											}
										}
									}
								}

								//New Note
								if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(NOTE)){
									Note current = new Note();
									current.setMode(mode);
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
								}

								if(event.isStartElement() && 
										event.asStartElement().getName().toString().equals(BACKUP)){
										event = eventReader.nextEvent();
										if(event.isCharacters()){
											String character = event.asCharacters().getData().toString();
											int num = Integer.parseInt(character);
											counter = counter - num;
										}

										
									}
								}


							}

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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	} 