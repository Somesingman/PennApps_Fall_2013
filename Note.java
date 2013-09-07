
public class Note implements Comparable<Note>{

  private String pitch = "";
  private String step = "";
  private String alter = "";
  private int octave = 0;
  private boolean chord = false;
  private int duration = 0;

  public String getPitch() {
    return pitch;
  }
  public void setPitch(String pitch) {
    this.pitch = pitch;
  }
  public String getStep() {
    return step;
  }
  public void setStep(String step) {
    this.step = step;
  }
  public String getAlter() {
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