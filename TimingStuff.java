import java.util.ArrayList;
import java.util.Collections;


public class TimingStuff {
	
	public static ArrayList<Note> getThreePositions(ArrayList<Note> measure, Note note) {
		//we are getting 3 different positions, all the notes in those positions
		Collections.sort(measure);
		int index = measure.indexOf(note);
		int notePosition = note.getPosition();
		
		ArrayList<Note> atSamePosition = new ArrayList<Note>();
		ArrayList<Note> atNextPosition = new ArrayList<Note>();
		ArrayList<Note> atPrevPosition = new ArrayList<Note>();
		
		int count = index + 1;
		//Get all notes at the same position, forwards
		//NOTE: won't be in order, shouldn't matter though since they are all at the same position
		while(measure.get(count).getPosition() == notePosition && count < measure.size()){
			atSamePosition.add(measure.get(count));
			count++;
		}
		//get all notes at the next position
		if(count < measure.size()){
			int nextPosition = measure.get(count).getPosition();
			while(measure.get(count).getPosition() == nextPosition && count < measure.size()){
				atNextPosition.add(measure.get(count));
				count++;
			}
		}
		//get all notes at the same position, backwards
		count = index - 1;
		while(measure.get(count).getPosition() == notePosition && count >= 0){
			atSamePosition.add(measure.get(count));
			count--;
		}
		//get all notes at the previous position
		if(count > 0){
			int prevPosition = measure.get(count).getPosition();
			while(measure.get(count).getPosition() == prevPosition && count >= 0){
				atPrevPosition.add(measure.get(count));
				count--;
			}
		}
		
		ArrayList<Note> finalList = new ArrayList<Note>();
		finalList.addAll(atSamePosition);
		finalList.addAll(atPrevPosition);
		finalList.addAll(atNextPosition);
		return finalList;
		
	}
	//doesn't account for going out of the current measure... don't know if we need to do that
	
}
