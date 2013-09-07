public class Note implements Comparable<Note>{

  private String pitch;
  private String step;
  private String alter;
  private String octave;
  private String chord;
  private int duration;

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
  public String getOctave() {
    return octave;
  }
  public void setOctave(String octave) {
    this.octave = octave;
  }
  public String getChord() {
    return chord;
  }
  public void setChord(String chord) {
    this.chord = chord;
  }
  public int getDuration() {
    return duration;
  }
  public void setDuration(int duration) {
    this.duration = duration;
  }
@Override
public int compareTo(Note n) {
	if(this.duration < n.getDuration()) return -1;
	else if(this.duration > n.getDuration()) return 1;
	else return 0;
}
public boolean equals(Note n){
	if(this.pitch.equals(n.getPitch()) &&  this.step.equals(n.getStep())
			&& this.alter.equals(n.getAlter()) && this.octave.equals(n.getOctave())
			&& this.chord.equals(n.getChord()) && this.duration == n.getDuration()){
		return true;
	}
	else return false;
}
	

}