package com.gwccnet.utilities;

/**
 * This class is implements java.util.Comparator so it may
 * be used in a sort method.  It's indended use it to sort a
 * java.util.List of java.lang.Object[] where each Object Array
 * holds a java.lang.String, java.lang.Number, or, java.util.Date.'
 * All null object will be pushed to the end of the list.
 * If it holds an object that is not one of these or the columns
 * in the list have missmatched types the sorting will not work 
 * correctly.
 * @author: Justin Rosenberg (Rosenberg@us.ibm.com)
 */
public class ComparableArraysComparator implements java.util.Comparator {
	public int column;
/**
 * ListOfStringArraysComparator constructor comment.
 */
public ComparableArraysComparator() {
	   new ComparableArraysComparator(0);
}
/**
 * Insert the method's description here.
 * Creation date: (3/29/2002 10:34:16 AM)
 * @param column int
 */
public ComparableArraysComparator(int column) 
{
	this.column = column;
}
/**
 * Compares its two arguments for order.  Returns a negative integer,
 * zero, or a positive integer as the first argument is less than, equal
 * to, or greater than the second.<p>
 *
 * The implementor must ensure that <tt>sgn(compare(x, y)) ==
 * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
 * implies that <tt>compare(x, y)</tt> must throw an exception if and only
 * if <tt>compare(y, x)</tt> throws an exception.)<p>
 *
 * The implementor must also ensure that the relation is transitive:
 * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
 * <tt>compare(x, z)&gt;0</tt>.<p>
 *
 * Finally, the implementer must ensure that <tt>compare(x, y)==0</tt>
 * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
 * <tt>z</tt>.<p>
 *
 * It is generally the case, but <i>not</i> strictly required that 
 * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
 * any comparator that violates this condition should clearly indicate
 * this fact.  The recommended language is "Note: this comparator
 * imposes orderings that are inconsistent with equals."
 * 
 * @return a negative integer, zero, or a positive integer as the
 * 	       first argument is less than, equal to, or greater than the
 *	       second. 
 * @throws ClassCastException if the arguments' types prevent them from
 * 	       being compared by this Comparator.
 */
public int compare(Object o1, Object o2) 
{
    int diff = 0; //Default is they are equal.
    
    Comparable[] array1 = (Comparable[]) o1;
    Comparable[] array2 = (Comparable[]) o2;

    Comparable object1 = array1[column];
    Comparable object2 = array2[column];

    if(object1 == null && object2 == null)
    {
		diff=0;
    }
    else if(object1 == null && object2 != null) //First object is null and second is not
   	{
	    //Then treat first greater than than to move null to end.
	    diff = 1;
    }
	else if(object1 != null && object2 == null) //Second object is null, but first is not
	{
		//Then treat first as less than
		diff = -1;
	}       
    else
    {
    	diff = object1.compareTo(object2);
    }
    return diff;
}
}
