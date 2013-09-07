
public class Note implements Comparable<Note>{

  private String step = "";
  private int alter = 0;
  private int octave = 0;
  private boolean chord = false;
  private int duration = 0;
  private int position = 0;
  private int voice = 0;
  private String key = "";

  public String getStep() {
    return step;
  }
  public void setStep(String step) {
    this.step = step;
  }
  public int getAlter() {
    return alter;
  }
  public void setAlter(int alter) {
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
  public int getPosition() {
    return position;
  }
  public void setPosition(int position){
    this.position = position;
  }
  public int getVoice() {
    return voice;
  }
  public void setVoice(int voice){
    this.voice = voice;
  }
  public String getKey() {
    return key;
  }
  public void setKey(int key){
    this.key = key;
  }

  public String toString(){
	  return "Chord: " + chord + "\nDuration: " + duration + 
			  "\nStep: " + step + "\nOctave: " + octave + "\nPosition: " + position;
  }
	@Override
	public int compareTo(Note n) {
		if(this.position < n.getPosition()) return -1;
		else if(this.position > n.getPosition()) return 1;
		else return 0;
	}
	public boolean equals(Note n){
		if(this.step.equals(n.getStep()) && this.alter == n.getAlter() 
      && this.octave == n.getOctave() && this.chord == n.getChord() 
      && this.duration == n.getDuration()){
			return true;
		}
		else return false;
	}

}