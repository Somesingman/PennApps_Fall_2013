import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Converter {
	
	//parallel
	List<String> notes;
	int[] noteValues;
	
	HashMap<Integer, String> majorKeyFromFifth;
	HashMap<Integer, String> minorKeyFromFifth; 
	
	public Converter(){
		notes = Arrays.asList("C", "C#", "Db", "D", "D#", "Eb", "E", "F", "F#", "Gb", "G", "G#",
								"Ab", "A", "A#", "Bb", "B", "Cb");
		noteValues = new int[]{0,   1,    1,    2,    3,    3,   4,   5,    6,   6,    7,   8,
								 8,    9,   10,   10,   11 , 11};
		//Add 4 for the III
		//Subtract 3 for the VI
		//MOD 12
		
		majorKeyFromFifth = new HashMap<Integer, String>();
		majorKeyFromFifth.put(-7, "Cb");
		majorKeyFromFifth.put(-6, "Gb");
		majorKeyFromFifth.put(-5, "Db");
		majorKeyFromFifth.put(-4, "Ab");
		majorKeyFromFifth.put(-3, "Eb");
		majorKeyFromFifth.put(-2, "Bb");
		majorKeyFromFifth.put(-1, "F");
		majorKeyFromFifth.put(0, "C");
		majorKeyFromFifth.put(1, "G");
		majorKeyFromFifth.put(2, "D");
		majorKeyFromFifth.put(3, "A");
		majorKeyFromFifth.put(4, "E");
		majorKeyFromFifth.put(5, "B");
		majorKeyFromFifth.put(6, "F#");
		majorKeyFromFifth.put(7, "C#");
		
		minorKeyFromFifth = new HashMap<Integer, String>();
		minorKeyFromFifth.put(-7, "Ab");
		minorKeyFromFifth.put(-6, "Eb");
		minorKeyFromFifth.put(-5, "Bb");
		minorKeyFromFifth.put(-4, "F");
		minorKeyFromFifth.put(-3, "C");
		minorKeyFromFifth.put(-2, "G");
		minorKeyFromFifth.put(-1, "D");
		minorKeyFromFifth.put(0, "A");
		minorKeyFromFifth.put(1, "E");
		minorKeyFromFifth.put(2, "B");
		minorKeyFromFifth.put(3, "F#");
		minorKeyFromFifth.put(4, "C#");
		minorKeyFromFifth.put(5, "G#");
		minorKeyFromFifth.put(6, "D#");
		minorKeyFromFifth.put(7, "A#");
	}
	
	/*
	 * Based on a key, if you're going major->minor or the other way around, and if you change
	 * the VII, takes in a note and returns what the note should be changed to (if anything)
	 */
	public Note convert(Note input, String key, String toMaj_or_toMin, ArrayList<Note> measure){
		if(toMaj_or_toMin.equals("toMin")){
			int index = notes.indexOf(key);
			int keyNumber = noteValues[index];
			
			int III = (keyNumber + 4) % 12;
			int VI = (keyNumber - 3) % 12;
			int VII = (keyNumber - 1) % 12;
			
			int inputNumber = (noteValues[notes.indexOf(input.getStep())] + input.getAlter()) % 12;
			
			if(inputNumber == III || inputNumber == VI || (inputNumber == VII && isDominant(measure,key))){
				//if we aren't changing note name, just alter -1
				if(input.getAlter() != -1 && !input.getStep().equals("C")
										  && !input.getStep().equals("F")) 
				{
					input.setAlter(input.getAlter() - 1);
				}
				//if we are changing note name
				else if(!input.getStep().equals("C") && !input.getStep().equals("F")){
					//step alter octave
					//if it is Db, Eb, Gb, Ab, or Bb
					if(		inputNumber == 1 || 
							inputNumber == 3 || 
							inputNumber == 6 || 
							inputNumber == 8 || 
							inputNumber == 10){
						input.setAlter(0);
						input.setStep(getNoteFromNumber(inputNumber - 1));
					}
				}
				else if(input.getStep().equals("F")){
					input.setStep("E");
					input.setAlter(0);
				}
				//it's a C
				//TODO: Figure out what the hell to do with Cb
				else {
					input.setStep("B");
					input.setAlter(0);
					input.setOctave(input.getOctave() - 1);
				}
			}
			
		}
		
		return input;
	}
	
	/*
	 * Figures out the key
	 */
	public String whatKey (String mode, int fifths){
		if(mode == "major"){
			return majorKeyFromFifth.get(fifths);
		}
		else {
			return minorKeyFromFifth.get(fifths);
		}
	}
	
	//Does not guarantee picking between # and b when they are the same note
	private String getNoteFromNumber(int n){
		int index = 0;
		n = n % 12; //sanitize input
		while(n != noteValues[index]) index++;
		return notes.get(index);
	}
	
	public boolean isDominant(ArrayList<Note> noteList, String key) {
		int tonic = noteValues[notes.indexOf(key)];
		int dominant = (tonic + 7) % 12;
		//List<Integer> tonicMembers = Arrays.asList(tonic,(tonic+4)%12,(tonic+7)%12);
		List<Integer> dominantMembers = Arrays.asList(dominant,(dominant+4)%12,(dominant+7)%12,(dominant+10)%12);
		int otherScore = 0;
		int dominantScore = 0;
		//Iterate through notes list twice: one to calculate tonicScore, one to calculate dominant
		//If dominatnScore > tonicScore, return true, else false
		for (Note n : noteList) {
			int value = noteValues[notes.indexOf(n.getStep())];
			if (dominantMembers.contains(value)) {
				dominantScore++;
			}
			else { otherScore++; }
		}
		if (dominantScore > otherScore) {
			return true;
		}
		else { return false; }
	}
}
