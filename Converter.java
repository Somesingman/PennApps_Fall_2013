import java.util.Arrays;
import java.util.List;


public class Converter {
	
	//parallel
	List<String> notes;
	int[] noteValues;
	
	
	public Converter(){
		notes = Arrays.asList("C", "C#", "Db", "D", "D#", "Eb", "E", "F", "F#", "Gb", "G", "G#",
								"Ab", "A", "A#", "Bb", "B");
		noteValues = new int[]{0,   1,    1,    2,    3,    3,   4,   5,    6,   6,    7,   8,
								 8,    9,   10,   10,   11};
		//Add 4 for the III
		//Subtract 3 for the VI
		//MOD 12
		
	}
	
	public Note convert(Note input, String key, String toMaj_or_toMin, boolean changeVII){
		if(toMaj_or_toMin.equals("toMin")){
			int index = notes.indexOf(key);
			int keyNumber = noteValues[index];
			
			int III = (keyNumber + 4) % 12;
			int VI = (keyNumber - 3) % 12;
			int VII = (keyNumber - 1) % 12;
			
			int inputNumber = (noteValues[notes.indexOf(input.getStep())] + input.getAlter()) % 12;
			
			if(inputNumber == III || inputNumber == VI || (inputNumber == VII && changeVII)){
				//if we aren't changing note name, just alter -1
				if(input.getAlter() != -1 && !input.getStep().equals("C")
										  && !input.getStep().equals("F")) 
				{
					input.setAlter(input.getAlter() - 1);
				}
				//if we are changing note name
				else {
					inputNumber--;
					
				}
			}
			
		}
		
		
		//change
		return null;
	}
	
	
	
}
