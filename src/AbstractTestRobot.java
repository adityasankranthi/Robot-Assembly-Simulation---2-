
import java.util.function.Supplier;

import edu.uwm.cs.junit.LockedTestCase;
import edu.uwm.cs351.Part;
import edu.uwm.cs351.Robot;

public abstract class AbstractTestRobot extends LockedTestCase {
	
	protected AbstractTestRobot() {
		super("AbstractTestRobot");
	}
	
	protected AbstractTestRobot(String infoName) {
		super(infoName);
	}
	
	protected void assertException(Class<? extends Throwable> c, Runnable r) {
		try {
			r.run();
			assertFalse("Exception should have been thrown",true);
		} catch (Throwable ex) {
			assertTrue("should throw exception of " + c + ", not of " + ex.getClass(), c.isInstance(ex));
		}
	}
	
	protected Robot r; // the robot.
	
	/**
	 * Initialize a new empty robot.
	 */
	protected abstract void initRobot();

	@Override // implementation
	protected void setUp() {
		initRobot();
	}

	
	// tests of add for every combination
	public void test01() {
		assertException(NullPointerException.class, () -> r.addPart(null, new Part()));
	}
	
	public void test02() {
		assertException(NullPointerException.class, () -> r.addPart("arm", null));
	}
	
	public void test03() {
		assertException(NullPointerException.class, () -> r.addPart(null, null));
	}
	
	public void test04() {
		assertTrue(r.addPart("arm", new Part()));
	}
	
	public void test05() {
		assertTrue(r.addPart("leg", new Part()));
	}
	
	public void test06() {
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test07() {
		r.addPart("arm", new Part());
		assertTrue(r.addPart("arm", new Part()));
	}
	
	public void test08() {
		r.addPart("leg", new Part());
		assertTrue(r.addPart("leg", new Part()));
	}
	
	public void test09() {
		r.addPart("arm", new Part());
		assertTrue(r.addPart("leg", new Part()));
	}
	
	public void test10() {
		r.addPart("arm", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test11() {
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test12() {
		r.addPart("arm", new Part());
		r.addPart("arm", new Part());
		assertTrue(r.addPart("leg", new Part()));
		
	}
	
	public void test13() {
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("leg", new Part()));
	}
	
	public void test14() {
		r.addPart("arm", new Part());
		r.addPart("arm", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test15() {
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test16() {
		r.addPart("leg", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test17() {
		r.addPart("arm", new Part());
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("leg", new Part()));
	}
	
	public void test18() {
		r.addPart("arm", new Part());
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test19() {
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	public void test20() {
		r.addPart("arm", new Part());
		r.addPart("arm", new Part());
		r.addPart("leg", new Part());
		r.addPart("leg", new Part());
		assertTrue(r.addPart("head", new Part()));
	}
	
	
	// tests of getPart
	public void test21() {
		r.addPart("arm", new Part());
		assertException(IllegalArgumentException.class, () -> r.getPart(null, -1));
	}
	
	public void test22() {
		assertNull(r.getPart("arm", 0));
	}
	
	public void test23() {
		assertNull(r.getPart("leg", 0));
	}
	
	public void test24() {
		assertNull(r.getPart("head", 0));
	}
	
	public void test25() {
		assertNull(r.getPart(null, 0));
	}
	
	public void test26() {
		Part p1 = new Part();
		
		r.addPart("arm", p1);
		assertNull(r.getPart("leg", 0));
	}
	
	public void test27() {
		Part p1 = new Part();
		
		r.addPart("arm", p1);
		assertNull(r.getPart("head", 0));
	}
	
	public void test28() {
		Part p1 = new Part();
		
		r.addPart("leg", p1);
		assertNull(r.getPart("arm", 0));
	}
	
	public void test29() {
		Part p1 = new Part();
		
		r.addPart("leg", p1);
		assertNull(r.getPart("head", 0));
	}
	
	public void test30() {
		Part p1 = new Part();
		
		r.addPart("head", p1);
		assertNull(r.getPart("arm", 0));
	}
	
	public void test31() {
		Part p1 = new Part();
		
		r.addPart("head", p1);
		assertNull(r.getPart("leg", 0));
	}
	
	public void test32() {
		Part p1 = new Part();
		
		r.addPart("arm", p1);
		assertEquals(p1, r.getPart(null, 0));
	}
	
	public void test33() {
		Part p1 = new Part();
		
		r.addPart("leg", p1);
		assertEquals(p1, r.getPart(null, 0));
	}
	
	public void test34() {
		Part p1 = new Part();
		
		r.addPart("head", p1);
		assertEquals(p1, r.getPart(null, 0));
	}
	
	public void test35() {
		Part p1 = new Part();
		
		r.addPart("arm", p1);
		assertNull(r.getPart(null, 1));
	}
	
	public void test36() {
		Part p1 = new Part();
		
		r.addPart("leg", p1);
		assertNull(r.getPart(null, 1));
	}
	
	public void test37() {
		Part p1 = new Part();
		
		r.addPart("head", p1);
		assertNull(r.getPart(null, 1));
	}
	
	public void test38() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart(new String("arm"), p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p1, r.getPart(new String("arm"), 0));
	}
	
	public void test39() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart(new String("arm"), p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p2, r.getPart(new String("arm"), 1));
	}
	
	public void test40() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart(new String("leg"), p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p3, r.getPart(new String("leg"), 0));
	}
	
	public void test41() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p4, r.getPart("leg", 1));
	}
	
	public void test42() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertNull(r.getPart("iFad", 0));
		assertEquals(p5, r.getPart(new String("head"), 0));
	}
	
	public void test43() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p1, r.getPart(null, 0));
	}
	
	public void test44() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p2, r.getPart(null, 1));
	}
	
	public void test45() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p3, r.getPart(null, 2));
	}
	
	public void test46() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p4, r.getPart(null, 3));
	}
	
	public void test47() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p5, r.getPart(null, 4));
	}
	
	
	// tests of removePart
	public void test48() {
		assertNull(r.removePart(null));
	}
	
	public void test49() {
		assertNull(r.removePart("arm"));
	}
	
	public void test50() {
		assertNull(r.removePart("leg"));
	}
	
	public void test51() {
		assertNull(r.removePart("head"));
	}
	
	public void test52() {
		Part p1 = new Part();
		r.addPart("arm", p1);
		assertEquals(p1, r.removePart("arm"));
	}
	
	public void test53() {
		Part p1 = new Part();
		r.addPart("leg", p1);
		assertEquals(p1, r.removePart("leg"));
	}
	
	public void test54() {
		Part p1 = new Part();
		r.addPart("head", p1);
		assertEquals(p1, r.removePart("head"));
	}
	
	public void test55() {
		Part p1 = new Part();
		r.addPart("arm", p1);
		assertNull(r.removePart("leg"));
	}
	
	public void test56() {
		Part p1 = new Part();
		r.addPart("arm", p1);
		assertNull(r.removePart("head"));
	}
	
	public void test57() {
		Part p1 = new Part();
		r.addPart("leg", p1);
		assertNull(r.removePart("arm"));
	}
	
	public void test58() {
		Part p1 = new Part();
		r.addPart("leg", p1);
		assertNull(r.removePart("head"));
	}
	
	public void test59() {
		Part p1 = new Part();
		r.addPart("head", p1);
		assertNull(r.removePart("arm"));
	}
	
	public void test60() {
		Part p1 = new Part();
		r.addPart("head", p1);
		assertNull(r.removePart("leg"));
	}
	
	// Since there is no size field, tests 61-66 check that the part was actually removed.
	// In the tests where removePart's passing parameter is null, the tests also ensure that
	// the removePart method was correctly written so as not to crash or throw an exception.
	public void test61() {
		Part p1 = new Part();
		r.addPart("arm", p1);
		r.removePart("arm");
		assertNull(r.removePart("arm"));
	}
	
	public void test62() {
		Part p1 = new Part();
		r.addPart("leg", p1);
		r.removePart("leg");
		assertNull(r.removePart("leg"));
	}
	
	public void test63() {
		Part p1 = new Part();
		r.addPart("head", p1);
		r.removePart("head");
		assertNull(r.removePart("head"));
	}
	
	public void test64() {
		Part p1 = new Part();
		r.addPart("arm", p1);
		r.removePart("arm");
		assertNull(r.removePart(null));
	}
	
	public void test65() {
		Part p1 = new Part();
		r.addPart("leg", p1);
		r.removePart("leg");
		assertNull(r.removePart(null));
	}
	
	public void test66() {
		Part p1 = new Part();
		r.addPart("head", p1);
		r.removePart("head");
		assertNull(r.removePart(null));
	}
	
	public void test67() {
		Part p1 = new Part();
		Part p2 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		
		r.removePart("arm");
		assertEquals(p2, r.removePart("arm"));
	}
	
	public void test68() {
		Part p1 = new Part();
		Part p2 = new Part();
		r.addPart("leg", p1);
		r.addPart("leg", p2);
		
		r.removePart("leg");
		assertEquals(p2, r.removePart("leg"));
	}
	
	// Note: keep in mind the precedence rules in FixedRobot in order to
	// ensure that we get the same result from ArrayRobot. This will be tested
	// further in the respective concrete subclasses as results can differ
	// with other implementations/order of added parts with respect to type.
	
	public void test69() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p1, r.removePart("arm"));
	}
	
	public void test70() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		r.removePart("arm");
		assertEquals(p2, r.removePart("arm"));
	}
	
	public void test71() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p3, r.removePart("leg"));
	}
	
	public void test72() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		r.removePart("leg");
		assertEquals(p4, r.removePart("leg"));
	}
	
	public void test73() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p5, r.removePart("head"));
	}
	
	public void test74() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		assertEquals(p1, r.removePart(null));
	}
	
	public void test75() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		r.removePart(null);
		assertEquals(p2, r.removePart(null));
	}
	
	public void test76() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		r.removePart(null);
		r.removePart(null);
		assertEquals(p3, r.removePart(null));
	}
	
	public void test77() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		for(int i = 0; i < 3; ++i) {
			r.removePart(null);
		}
		assertEquals(p4, r.removePart(null));
	}
	
	public void test78() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.addPart("leg", p3);
		r.addPart("leg", p4);
		r.addPart("head", p5);
		
		for(int i = 0; i <= 3; ++i) {
			r.removePart(null);
		}
		assertEquals(p5, r.removePart(null));
	}
	
	public void test79() { // mix-small (Part A)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("leg", p2);
		r.addPart("arm", p3);
		
		r.removePart("arm"); // will always remove the first part of that type added
		assertEquals(p3, r.removePart("arm"));
	}
	
	public void test80() { // mix-small (Part B)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("leg", p2);
		r.addPart("arm", p3);
		
		r.removePart("arm");
		r.removePart("arm");
		assertEquals(p2, r.removePart(null)); // tests ArrayRobot bug fix
	}
	
	public void test81() { // mix-big (Part A)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("leg", p2);
		r.addPart("arm", p1);
		r.addPart("head", p4);
		r.addPart("arm", p5);
		r.addPart("leg", p3);
		
		assertEquals(p4, r.removePart("head"));
	}
	
	public void test82() { // mix-big (Part B)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("leg", p2);
		r.addPart("arm", p1);
		r.addPart("head", p4);
		r.addPart("arm", p5);
		r.addPart("leg", p3);
		
		r.removePart("head");
		assertEquals(p1, r.removePart("arm"));
	}
	
	public void test83() { // mix-big (Part C)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("leg", p2);
		r.addPart("arm", p1);
		r.addPart("head", p4);
		r.addPart("arm", p5);
		r.addPart("leg", p3);
		
		r.removePart("head");
		r.removePart("arm");
		assertEquals(p2, r.removePart("leg"));
	}
	
	public void test84() { // mix-big (Part D)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("leg", p2);
		r.addPart("arm", p1);
		r.addPart("head", p4);
		r.addPart("arm", p5);
		r.addPart("leg", p3);
		
		r.removePart("head");
		r.removePart("arm");
		r.removePart("leg");
		assertEquals(p3, r.removePart("leg"));
	}
	
	public void test85() { // mix-big (Part E)
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		Part p4 = new Part();
		Part p5 = new Part();
		
		r.addPart("leg", p2);
		r.addPart("arm", p1);
		r.addPart("head", p4);
		r.addPart("arm", p5);
		r.addPart("leg", p3);
		
		r.removePart("head");
		r.removePart("arm");
		r.removePart("leg");
		r.removePart("leg");
		assertEquals(p5, r.removePart(null));
	}
	
	public void test86() {
		Part p1 = new Part();
		Part p2 = new Part();
		Part p3 = new Part();
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.removePart("arm");
		assertTrue(r.addPart("arm", p3));
	}
	
	protected String asString(Supplier<?> supp) {
		try {
			return "" + supp.get();
		} catch(RuntimeException ex) {
			return ex.getClass().getSimpleName();
		}
	}
	
	protected String asString(Runnable r) {
		return asString(() -> { r.run(); return "void"; });
	}

	public void test87() {
		Part p1 = new Part("#1");
		Part p2 = new Part("#2");
		Part p3 = new Part("#3");
		
		r.addPart("arm", p1);
		r.addPart("arm", p2);
		r.removePart("arm");
		r.addPart("arm", p3);
		// Changed from Homework #1!
		// asString makes a string of the argument, null gives "null"
		// but if an exception is thrown, we get the class name of the exception
		assertEquals(Ts(2117932122), asString(() -> r.getPart(null, 0)));
	}
	
	public void test88() {
		Part p1 = new Part("#1");
		Part p2 = new Part("#2");
		Part p3 = new Part("#3");
		
		r.addPart("leg", p1);
		r.addPart("arm", p2);
		r.addPart("arm", p3);
		r.removePart("arm");
		assertEquals(p3, r.getPart("arm", 0));
	}
	
	public void test89() {
		Part p1 = new Part("#1");
		Part p2 = new Part("#2");
		r.addPart("arm", p1);
		r.addPart("leg", p2);
		r.removePart(null);
		assertNull(r.getPart(null, 1));
	}
}
