import java.util.ArrayList;
import java.util.Collections;


public class TimingStuff {
	
	/*
	 * Given a measure and a note, gets the up to n notes (in sorted order) that occurred
	 * prior to the given note.
	 * Does NOT take into account previous measure
	 */
	public static ArrayList<Note> getMostRecentNotes (ArrayList<Note> measure, Note note, int n){
		Collections.sort(measure);
		int index = measure.indexOf(note);
		
		//see if there are any ties
		int count = 0;
		while(note.getPosition() == measure.get(index+count+1).getPosition()){
			count++;
		}
		
		n -= count;
		
		ArrayList<Note> answer = new ArrayList<Note>();
		
		int i = index - n;
		if(i < 0) i = 0;
		while(i < n){
			answer.add(measure.get(i));			
			i++;
		}
		
		for(int j = 0; j < count; j++){
			answer.add(measure.get(index + j + 1));
		}
		
		return answer;		
	}
	
	/*
	 * Given a measure and a note, gets the up to n notes (in sorted order) that occurred
	 * prior to the given note.
	 * TAKES PREVIOUS MEASURE INTO ACCOUNT
	 */
	public static ArrayList<Note> getMostRecentNotes (ArrayList<Note> measure,
										ArrayList<Note> previousMeasure, Note note, int n){
		Collections.sort(previousMeasure);
		ArrayList<Note> notesFromMeasure = getMostRecentNotes(measure, note, n);
		if(notesFromMeasure.size() == n) return notesFromMeasure;
		
		int numToAdd = n - notesFromMeasure.size();
		int index = previousMeasure.size() - numToAdd;
		if(index < 0) index = 0;
		ArrayList<Note> answer = new ArrayList<Note>();
		while(index < previousMeasure.size()){
			answer.add(previousMeasure.get(index));
			index++;
		}
		int measureSize = measure.size();
		for(int i = 0; i < measureSize; i++){
			answer.add(measure.get(i));
		}
		return answer;
	}
	
}
