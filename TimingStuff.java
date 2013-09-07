import java.util.ArrayList;
import java.util.Collections;


public class TimingStuff {
	
	/*
	 * Given a measure and a note, gets the up to n notes (in sorted order) that occurred
	 * prior to the given note.
	 * Does NOT take into account previous measure
	 */
	
	//TODO: grab all the notes that are at the same time - depending on the order, may or may
	//not be before or after note
	//HOW TO DO IT: while n > 0, grab the note AFTER, put at end, n--
	public static ArrayList<Note> getMostRecentNotes (ArrayList<Note> measure, Note note, int n){
		Collections.sort(measure);
		int index = measure.indexOf(note);
		
		ArrayList<Note> answer = new ArrayList<Note>();
		
		int i = index - n;
		if(i < 0) i = 0;
		while(i < n){
			answer.add(measure.get(i));			
			i++;
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
