import static org.junit.Assert.*;

import org.junit.Test;


public class TestConverter {

	@Test
	public void testConverter1() {
		Converter c = new Converter();
		Note n = new Note();
		n.setOctave(3);
		n.setStep("C");
		n.setAlter(0);
		Note result = c.convert(n, "Db", "toMin", null);
		
		assertTrue(result.getStep().equals("B"));
		assertTrue(result.getAlter() == 0);
		assertTrue(result.getOctave() == 2);		
	}
	@Test
	public void testConverter2() {
		Converter c = new Converter();
		Note n = new Note();
		n.setOctave(3);
		n.setStep("F");
		n.setAlter(0);
		Note result = c.convert(n, "C#", "toMin", null);
		
		assertTrue(result.getStep().equals("E"));
		assertTrue(result.getAlter() == 0);
		assertTrue(result.getOctave() == 3);
		
	}
	@Test
	public void testConverter3() {
		Converter c = new Converter();
		Note n = new Note();
		n.setOctave(3);
		n.setStep("A");
		n.setAlter(-1);
		Note result = c.convert(n, "B", "toMin", null);
		
		assertTrue(result.getStep().equals("G"));
		assertTrue(result.getAlter() == 0);
		assertTrue(result.getOctave() == 3);
		
	}
	@Test
	public void testConverter4() {
		Converter c = new Converter();
		Note n = new Note();
		n.setOctave(3);
		n.setStep("D");
		n.setAlter(1);
		Note result = c.convert(n, "B", "toMin", null);
		
		assertTrue(result.getStep().equals("D"));
		assertTrue(result.getAlter() == 0);
		assertTrue(result.getOctave() == 3);
		
	}
	@Test
	public void testConverter5() {
		Converter c = new Converter();
		Note n = new Note();
		n.setOctave(3);
		n.setStep("G");
		n.setAlter(0);
		Note result = c.convert(n, "A#", "toMin", null);
		
		assertTrue(result.getStep().equals("G"));
		assertTrue(result.getAlter() == -1);
		assertTrue(result.getOctave() == 3);
		
	}

}
