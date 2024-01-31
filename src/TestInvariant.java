import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.uwm.cs351.DynamicArrayPartSeq;
import edu.uwm.cs351.Part;
import junit.framework.TestCase;

public class TestInvariant extends TestCase {
	
	protected DynamicArrayPartSeq.Spy spy;
	protected int reports;
	protected DynamicArrayPartSeq r;
	
	protected void assertReporting(boolean expected, Supplier<Boolean> test) {
		reports = 0;
		Consumer<String> savedReporter = spy.getReporter();
		try {
			spy.setReporter((String message) -> {
				++reports;
				if (message == null || message.trim().isEmpty()) {
					assertFalse("Uninformative report is not acceptable", true);
				}
				if (expected) {
					assertFalse("Reported error incorrectly: " + message, true);
				}
			});
			assertEquals(expected, test.get().booleanValue());
			if (!expected) {
				assertEquals("Expected exactly one invariant error to be reported", 1, reports);
			}
			spy.setReporter(null);
		} finally {
			spy.setReporter(savedReporter);
		}
	}
	
	protected void assertWellFormed(boolean expected, DynamicArrayPartSeq r) {
		assertReporting(expected, () -> spy.wellFormed(r));
	}

	@Override // implementation
	protected void setUp() {
		spy = new DynamicArrayPartSeq.Spy();
	}
	
	// tests of null arrays
	public void testA0() {
		r = spy.newInstance(null, null, 0, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testA1() {
		Part[] p = new Part[1];
		p[0] = new Part();
		r = spy.newInstance(null, p, 1, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testA2() {
		String[] f = new String[1];
		f[0] = "arm";
		r = spy.newInstance(f, null, 1, null, 0);
		assertWellFormed(false, r);
	}
	
	// tests of same length for both arrays
	public void testB0() {
		String[] f = new String[0];
		Part[] p = new Part[0];
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testB1() {
		String[] f = new String[6];
		Part[] p = new Part[6];
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testB2() {
		String[] f = new String[6];
		Part[] p = new Part[5];
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testB3() {
		String[] f = new String[5];
		Part[] p = new Part[6];
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(false, r);
	}
	
	// tests of size's range
	public void testC0() {
		String[] f = new String[0];
		Part[] p = new Part[0];
		r = spy.newInstance(f, p, -1, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testC1() {
		String[] f = new String[0];
		Part[] p = new Part[0];
		r = spy.newInstance(f, p, 1, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testC2() {
		String[] f = new String[0];
		Part[] p = new Part[0];
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(true, r);
	}
	
	// tests of holes in the arrays
	public void testD0() { // hole at beginning / wrong size (nothing added)
		String[] f = new String[6];
		Part[] p = new Part[6];
		r = spy.newInstance(f, p, 1, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testD1() {
		String[] f = new String[6];
		Part[] p = new Part[6];
		f[0] = "head";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testD2() { // hole at beginning + size wrong (what is added is cutoff)
		String[] f = new String[6];
		Part[] p = new Part[6];
		f[1] = "head";
		p[1] = new Part();
		r = spy.newInstance(f, p, 1, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testD3() { // hole at beginning (no cutoff)
		String[] f = new String[6];
		Part[] p = new Part[6];
		f[1] = "head";
		p[1] = new Part();
		r = spy.newInstance(f, p, 2, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testD4() {
		String[] f = new String[6];
		Part[] p = new Part[6];
		f[0] = "head";
		p[0] = new Part();
		f[1] = "arm";
		p[1] = new Part();
		r = spy.newInstance(f, p, 2, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testD5() { // hole in middle
		String[] f = new String[6];
		Part[] p = new Part[6];
		f[0] = "head";
		p[0] = new Part();
		f[2] = "arm";
		p[2] = new Part();
		r = spy.newInstance(f, p, 3, null, 0);
		assertWellFormed(false, r);
	}
	
	public void testD6() { // cutoff, but still true
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "head";
		p[0] = new Part();
		r = spy.newInstance(f, p, 0, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testD7() { // hole at end / size is wrong
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		f[1] = "body";
		p[1] = new Part();
		r = spy.newInstance(f, p, 3, null, 0);
		assertWellFormed(false, r);
	}
	
	// tests of currentIndex
	public void testE0() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "arm";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, null, -1);
		assertWellFormed(false, r);
	}
	
	public void testE1() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, null, 0);
		assertWellFormed(true, r);
	}
	
	public void testE2() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "arm";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, null, 1);
		assertWellFormed(true, r);
	}
	
	public void testE3() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "head";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, null, 2);
		assertWellFormed(false, r);
	}
	
	// tests of function variable
	public void testF0() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "arm";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, "arm", 0);
		assertWellFormed(true, r);
	}
	
	public void testF1() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		r = spy.newInstance(f, p, 1, "head", 0);
		assertWellFormed(false, r);
	}
	
	public void testF2() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		f[1] = "arm";
		p[1] = new Part();
		f[2] = "arm";
		p[2] = new Part();
		r = spy.newInstance(f, p, 3, "arm", 0);
		assertWellFormed(false, r);
	}
	
	public void testF3() {
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		f[1] = "arm";
		p[1] = new Part();
		f[2] = "arm";
		p[2] = new Part();
		r = spy.newInstance(f, p, 3, "arm", 2);
		assertWellFormed(true, r);
	}
	
	public void testF4() { // when currentIndex == size, it doesn't matter what the function variable is
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		f[1] = "arm";
		p[1] = new Part();
		f[2] = "arm";
		p[2] = new Part();
		r = spy.newInstance(f, p, 3, "arm", 3);
		assertWellFormed(true, r);
	}
	
	public void testF5() { // when currentIndex == size, it doesn't matter what the function variable is
		String[] f = new String[5];
		Part[] p = new Part[5];
		f[0] = "leg";
		p[0] = new Part();
		f[1] = "arm";
		p[1] = new Part();
		f[2] = "arm";
		p[2] = new Part();
		r = spy.newInstance(f, p, 3, "leg", 3);
		assertWellFormed(true, r);
	}
}

