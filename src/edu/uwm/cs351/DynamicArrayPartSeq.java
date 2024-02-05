package edu.uwm.cs351;

import java.util.function.Consumer;

/**
 * A Robot implemented with a dynamic array data structure
 * and providing "Sequence" capabilities.  This is a homework assignment
 * inspired by Main: "Data Structures and Other Objects in Java" Chapter 3
 * (Sequence ADT).
 */
public class DynamicArrayPartSeq implements Robot, Cloneable {
	private static final int INITIAL_CAPACITY = 1;
	
	// Data structure: Do not add or subtract from this:
	private String[] functions;
	private Part[] parts;
	private int size;
	private String function;
	private int currentIndex;
	
	private static Consumer<String> reporter = (s) -> System.out.println("Invariant error: "+ s);
	
	private boolean report(String error) {
		reporter.accept(error);
		return false;
	}

	
	private boolean wellFormed() {
		// XXX: The invariant should be adjusted: no holes allowed
		// and it needs to check currentIndex and function
		
		// 1. The "functions" and "parts" arrays must not be null.
		// TODO
		if (functions == null) return report("functions array is null");
		if (parts == null) return report("parts array is null");
		
		// 2. The "functions" and "parts" arrays are always the same length.
		// TODO
		if (functions.length != parts.length) return report("functions and parts are not of equal length");
		
		// 3. The size cannot be negative or greater than the length of the arrays.
		// TODO
		if (size < 0) return report("The size is negative");
		if (size > functions.length) return report("The size greater than the length of the array");
		
		// 4. None of the first “size” elements of either array can be null. (ie. no holes)
		// TODO
		for (int i = 0; i < size; i++) {
			if (functions[i] == null || parts[i] == null) {
				return report("Data at index " + i + " is null");
			}
		}
	
		// 5. The current index cannot be negative or greater than the size.
		// TODO
		if (currentIndex < 0 ) return report("The current index is negative");
		if (currentIndex > size) return report("The current index greater than the size");
		
		// 6. If the function is not null and the current index is less than the size, then the
		// function array must agree with the field at the current index.
		// TODO
		if (function != null && currentIndex < size && !functions[currentIndex].equals(function)) return report("function array does not agree with the field at the current index.");
		// If no problems discovered, return true
		return true;
	}
	
	/**
	 * Return once the data structure has been updated so that 
	 * the capacity of the arrays is at least the parameter.
	 * If we create new arrays, they will at least twice as long as the existing arrays.
	 * This code assumes the arrays are the same length, and preserves this property.
	 * @param cap capacity desired
	 */
	private void ensureCapacity(int cap) {
		// TODO: Follow activity but update to handle two arrays at once (same length)
		if (cap < 0) {
			throw new IllegalArgumentException("capacity is negative: "
					+ cap);
		}
		if (functions.length < cap || parts.length < cap) {
            int newCap = Math.max(cap, functions.length * 2);
            String[] newFunctions = new String[newCap];
            Part[] newParts = new Part[newCap];
            for (int i = 0; i < size; i++) {
                newFunctions[i] = functions[i];
                newParts[i] = parts[i];
            }
            functions = newFunctions;
            parts = newParts;
        }
	}
	
	private DynamicArrayPartSeq(boolean ignored) {} // do not change this constructor
	
	/**
	 * Create an empty part sequence.
	 */
	public DynamicArrayPartSeq() {
		this(INITIAL_CAPACITY); // call specifying constructor to do the work
		// no assertion required because other constructor asserts it
	}
	
	/**
	 * Create a dynamic array part sequence with the given capacity
	 * (how many parts that can be added without requiring allocation of a new array).
	 * @param cap number of elements to prepare for, cannot be negative
	 */
	public DynamicArrayPartSeq(int cap) {
		// TODO
		if (cap < 0) {
			throw new IllegalArgumentException("capacity is negative: "
					+ cap);
		}
		parts = new Part[cap];
		functions = new String[cap];
		size = 0;
		currentIndex = 0;
		function = null;
		assert wellFormed() : "failed in constructor";
	}
	
	/**
	 * Return the number of elements in the sequence.
	 * @return number of elements in sequence
	 */
	public int size() {
		assert wellFormed() : "failed in size";
		return size; // TODO: very easy
	}
	
	/**
	 * Start running through all parts.
	 */
	public void start() {
		// not asserting invariant before this will be done by the other start
		start(null);
	}
	
	// TODO: A private helper method.  Define once you find yourself
	// doing the same thing three times.
	
	/**
	 * Start running through all parts with the given function.
	 * @param function kind of parts to access, may be null (any part)
	 */
	public void start(String function) {
		// TODO: don't forget to assert the invariant twice: before and after
        assert wellFormed() : "failed before start";
        this.function = function; 
        currentIndex = size; // when currentIndex == size, it doesn't matter what the function variable is
        if (function == null) {
        	if (size > 0) {
        	    currentIndex = 0;
        	} else {
        	    currentIndex = size;
        	}
        }
        else {
	    	for (int i = 0; i < size; i++) {
	    		if (functions[i].equals(function)) {
	    			currentIndex = i;
	    			break;
	    		}
	    	}
        }
        assert wellFormed() : "failed after start" ;
	}
	
	/**
	 * Return whether there is a current element.
	 * @return whether there is a current element
	 */
	public boolean isCurrent() {
		 assert wellFormed() : "invariant broken in isCurrent";
	        if(currentIndex >= 0 &&currentIndex < size) {
	            return true; // TODO (and don't forget to assert invariant)
	        }
	        assert wellFormed() : "invariant broken by isCurrent";
	        return false;
	}
	
	/**
	 * Return the current element.
	 * This method can only be called if there is a current element.
	 * @return the part which is current
	 * @throws IllegalStateException if there is no current element
	 */
	public Part getCurrent() {
		assert wellFormed() : "failed in getCurrent";
        if (!isCurrent()) {
            throw new IllegalStateException("current element does not exist");
        }
		assert wellFormed() : "failed in getCurrent";
        return parts[currentIndex]; // TODO (don't forget to assert the invariant)
	}
	
	/**
	 * Move the cursor to the next part with the current function.
	 * If there is no such part, then after this call, there is no current element.
	 * @throws IllegalStateException 
	 */
	public void advance() {
	    assert wellFormed() : "invariant broken in advance";

	    if (!isCurrent()) {
	        throw new IllegalStateException("No current element to advance");
	    }
	    currentIndex++;

	    // If function is not null and currentIndex is within bounds
	    if (function != null && currentIndex < size) {
	        // Use a for loop to iterate through the elements from currentIndex to size - 1
	        for (; currentIndex < size; currentIndex++) {
	            if (functions[currentIndex].equals(function)) {
	                break; // Exit the loop if the next part with the specified function is found
	            }
	        }
	    }
	    assert wellFormed() : "invariant broken by advance";
	}

	/**
	 * Remove the current element.  Then the next part (of the function)
	 * is made current, or there is no current if there are not more parts
	 * (of the function).
	 * @throws IllegalStateException if there is no current element
	 */
	public void removeCurrent() {
		// TODO: lots to do
	    assert wellFormed() : "invariant broken in removeCurrent";

		if (!isCurrent()) throw new IllegalStateException("no current element");
		for (int i = currentIndex; i < size - 1; i++) {
            functions[i] = functions[i + 1];
            parts[i] = parts[i + 1];
        }
        size--;
        if (!functions[currentIndex].equals(function) && function!= null) {
            // If the current function doesn't match, find the next matching function
            for (int i = currentIndex; i < size; i++) {
                if (functions[i].equals(function)) {
                    currentIndex = i;
                    break;
                }
            }
            // if matching function not found set currenIndex = size;
            if (!functions[currentIndex].equals(function)) {
                currentIndex = size;
            }
        }
	    assert wellFormed() : "invariant broken by removeCurrent";
	}
	
	/**
	 * Add a part before the current element.  If there is no current element,
	 * then add at the beginning.  There must have been a function
	 * defined (See [@link #start(String)}).
	 * @throws IllegalStateException if no function defined, or if the function was null
	 * @param p part to add at this spot, must not be null.
	 */
	public void addBefore(Part p) {
	    assert wellFormed() : "invariant broken in addBefore";

	    // Ensure that a function is defined
	    if (function == null) throw new IllegalStateException("No function defined");

	    // Ensure that the size doesn't exceed the maximum allowed value
	    if (size + 1 > Integer.MAX_VALUE) throw new OutOfMemoryError("Capacity beyond the maximum memory");

	    // Ensure capacity for the new element
	    ensureCapacity(size + 1);

	    // If there is no current element or the current function is different from the specified function,
	    // set the current function to the specified function and add the part at the beginning.
	    if (!isCurrent() || currentIndex == size) {
	        currentIndex = 0;
	    } 
        // If there is a current element and its function matches the specified function,
        // insert the new part before the current element.
        for (int i = size; i > currentIndex; i--) {
            functions[i] = functions[i - 1];
            parts[i] = parts[i - 1];
        }
        functions[currentIndex] = function;
        parts[currentIndex] = p;
	    size++;
	    assert wellFormed() : "invariant broken by addBefore";
	}

	/**
	 * Add a part before the current element.  If there is no current element,
	 * then add at the beginning.  There must have been a function
	 * defined (See [@link #start(String)}).
	 * @throws IllegalStateException if no function defined, or if the function was null
	 * @param p part to add at this spot, must not be null.
	 */
	public void addAfter(Part p) {
		// TODO: (remember the invariant!)
		assert wellFormed() : "invariant broken in addAfter";
	    if (function == null) throw new IllegalStateException("No function defined");
		if (size+1 > Integer.MAX_VALUE) throw new OutOfMemoryError("capacity beyond the maximum memory");
		ensureCapacity(size + 1);
		if (isCurrent()) {
		    currentIndex += 1;
		} else {
		    currentIndex = size;
		}
	    for (int i = size; i > currentIndex; i--) {
	        functions[i] = functions[i - 1];
	        parts[i] = parts[i - 1];
	    }
	    functions[currentIndex] = function;
	    parts[currentIndex] = p;
	    size++;
        assert wellFormed() : "invariant broken by addAfter";
	}
	
	@Override // decorate
	public DynamicArrayPartSeq clone() {
		assert wellFormed() : "invariant broken in clone";
		DynamicArrayPartSeq result;
		try {
			result = (DynamicArrayPartSeq) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("forgot to implement cloneable?");
		}
		// TODO: extra work
		result.currentIndex = this.currentIndex;
		result.function = this.function;
		result.size = this.size;
        result.functions = new String[result.size];
        result.parts = new Part[result.size];
		for (int i = 0; i< this.size; i++) {
			result.functions[i] = this.functions[i];
			result.parts[i] = this.parts[i];
		}
		assert result.wellFormed() : "invariant broken in result of clone";
		assert wellFormed() : "invariant broken by clone";
		return result;
	}
	
	@Override // required
	public boolean addPart(String function, Part part) {
		assert wellFormed() : "invariant broken in addPart";
		// TODO: mainly do the work with public methods
		assert wellFormed() : "invariant broken by addPart";
		return true;
	}

	@Override // required
	public Part removePart(String function) {
		assert wellFormed() : "invariant broken in removePart";
		Part result = null;
		// TODO: mainly do the work with public methods
		assert wellFormed() : "invariant broken by removePart";
		return result;
	}

	@Override // required
	public Part getPart(String function, int index) {
		assert wellFormed() : "invariant broken in getPart";
		// TODO: use public methods
		return null;
	}

	/**
	 * Class to use for testing purposes.
	 * Clients shouldn't use this class.
	 * Implementors shouldn't change it.
	 * XXX: Need updating!
	 */
	public static class Spy {
		/**
		 * Return the sink for invariant error messages
		 * @return current reporter
		 */
		public Consumer<String> getReporter() {
			return reporter;
		}

		/**
		 * Change the sink for invariant error messages.
		 * @param r where to send invariant error messages.
		 */
		public void setReporter(Consumer<String> r) {
			reporter = r;
		}

		/**
		 * Create a testing instance of a dynamic array robot with the given
		 * data structure.
		 * @param f the array of functions
		 * @param p the array of parts
		 * @param s the size
		 * @param func the string started with, null initially.
		 * @param i the current index
		 * @return a new testing dynamic array robot with this data structure.
		 */
		public DynamicArrayPartSeq newInstance(String[] f, Part[] p, int s, String func, int i) {
			DynamicArrayPartSeq result = new DynamicArrayPartSeq(false);
			result.functions = f;
			result.parts = p;
			result.size = s;
			result.function = func;
			result.currentIndex = i;
			return result;
		}
		
		/**
		 * Check the invariant on the given dynamic array robot.
		 * @param r robot to check, must not be null
		 * @return whether the invariant is computed as true
		 */
		public boolean wellFormed(DynamicArrayPartSeq r) {
			return r.wellFormed();
		}
	}
}

