package NBADemo;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;


public class MyQueueTester {
	private MyQueue<Integer> queue;
	private MyQueue<Integer> queue1;

	@Before
	/** set yp some values before the test */
	public void setUp() {
		queue = new MyQueue<Integer>();
		queue1 = new MyQueue<Integer>(4); // capacity is 4
	}

	@Test
	/** Test isEmpty() */
	public void testisEmpty() {
		assertTrue( queue.isEmpty() );
	}

	@Test
	/** test size() method */
	public void testsize() {
		assertEquals( 0, queue.size() );
		queue.add(new Integer(1));
		assertEquals( 1, queue.size() );
		queue.remove();
		assertEquals( 0, queue.size() );
	}

	@Test
	/** test add() method */
	public void testadd() {
		// test default capacity
		queue.add( new Integer(2) );  
		queue.add( new Integer(3) );
		queue.add( new Integer(4) );
		// now 4 3 2
		assertEquals( new Integer(2), queue.getItem(2) );
		assertEquals( new Integer(3), queue.getItem(1) );
		assertEquals( (Integer) 4, queue.getItem(0) );
		assertEquals( 3, queue.size() );

		// teset queue with capacity = 4
		queue1.add(new Integer(8));
		queue1.add(new Integer(7));
		queue1.add(new Integer(6));
		queue1.add(new Integer(5));
		queue1.add(new Integer(4)); 
		assertEquals( 4, queue1.size() );
		assertEquals( (Integer) 4, queue1.getItem(0) );
		assertEquals( (Integer) 6, queue1.getItem(2));
		assertEquals( (Integer) 7,queue1.getItem(3) ); 


	}

	@Test
	/** test remove() method */
	public void testremove() {
		queue.add( new Integer(2) );
		queue.add( new Integer(3) );
		queue.add( new Integer(4) );
		// now  4 3 2
		assertEquals( (Integer)2, queue.remove() ) ;
		assertEquals( (Integer)3, queue.getItem(1) );
		queue.remove();
		assertEquals( (Integer)4, queue.getItem(0) );
		// { 4 }
		assertEquals( 1, queue.size() );
	}

	@Test
	/** Test exception in remove() method */
	public void testremoveException() {
		try {
			queue.remove();
			fail("There should be an exception");
		}
		catch (NoSuchElementException e) {
			
		}
	}

    /* Run the unit tests in this file. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(MyQueueTester.class);
    }	

}