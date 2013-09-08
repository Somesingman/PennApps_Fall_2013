import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class TestDominant {

	@Test
	public void testDominant1() {
		Converter converter = new Converter();
		String key = "C";
		ArrayList<Note> notes = new ArrayList<Note>();
		Note g = new Note();
		g.setStep("G");
		Note b = new Note();
		b.setStep("B");
		Note d = new Note();
		d.setStep("D");
		notes.add(g);
		notes.add(b);
		notes.add(d);
		assertTrue(converter.isDominant(notes,key));
	}

	@Test
	public void testDominant2() {
		Converter converter = new Converter();
		String key = "A";
		ArrayList<Note> notes = new ArrayList<Note>();
		Note g = new Note();
		g.setStep("E");
		Note b = new Note();
		b.setStep("G#");
		Note d = new Note();
		d.setStep("E");
		notes.add(g);
		notes.add(b);
		notes.add(d);
		assertTrue(converter.isDominant(notes,key));
	}

}
