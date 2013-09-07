
public class Note implements Comparable<Note>{

  private String step = "";
  private int alter = 0;
  private int octave = 0;
  private boolean chord = false;
  private int duration = 0;
  private int counter = 0;

  public String getStep() {
    return step;
  }
  public void setStep(String step) {
    this.step = step;
  }
  public int getAlter() {
    return alter;
  }
  public void setAlter(String alter) {
    this.alter = alter;
  }
  public int getOctave() {
    return octave;
  }
  public void setOctave(int octave) {
    this.octave = octave;
  }
  public boolean getChord() {
    return chord;
  }
  public void setChord(boolean chord) {
    this.chord = chord;
  }
  public int getDuration() {
    return duration;
  }
  public void setDuration(int duration) {
    this.duration = duration;
  }
  public int getCounter() {
    return counter;
  }
  public void setCounter(int counter){
    this.counter = counter;
  }

  public String toString(){
	  return "Pitch: " + pitch + "\nChord: " + chord + "\nDuration: " + duration + 
			  "\nStep: " + step + "\nOctave: " + octave;
  }
	@Override
	public int compareTo(Note n) {
		if(this.duration < n.getDuration()) return -1;
		else if(this.duration > n.getDuration()) return 1;
		else return 0;
	}
}