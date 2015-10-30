package com.aldorsolutions.webfdms.util;


   
		   /*
		   * I had to rename add(object) to addObject(object) because the
		   * conflicted with java.util.vector.add(object) which has a boolean
		   * return type in Java2
		   * VarArgs 
		   * Kirk A. Abbott 
		   * 1998/04/20 
		   * 
		   * Permission to use, copy, modify, and distribute this 
		   * software and its documentation for *any* purpose 
		   * and without fee is hereby granted provided that this 
		   * copyright notice appears in all copies. 
		   * 
		   * THE AUTHOR MAKES NO REPRESENTATIONS OR 
		   * WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER 
		   * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
		   * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
		   * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHORS 
		   * AND PUBLISHER SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED 
		   * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING 
		   * THIS SOFTWARE OR ITS DERIVATIVES. 
		   */ 

		   import java.util.Vector;

		   public class VarArgs extends Vector 
		   { 
		   /**
			 * 
			 */
			private static final long serialVersionUID = 4310897434517474109L;
		/** 
		   * A class for implementing a VarArgs like functionality in 
		   * java. The canonical usage is: 
		   * 
		   * VarArgs ap = new VarArgs(); 
		   * ap.add(21).add(2.3467e-09).add("Hello World").add('T').add(25L); 
		   * 
		   */ 
		   private int next = 0; 

		   
		   public VarArgs() 
		   { 
		   super(); 
		   }   
		   public VarArgs(int initSize) 
		   { 
		   super(initSize); 
		   }   
		   /** 
		   * @param c the char to add. 
		   */ 
		   public VarArgs add(char c) 
		   { 
		   addElement(new Character(c)); 
		   return this; 
		   }   
		   /** 
		   * @param d the double to add. 
		   */ 
		   public VarArgs add(double d) 
		   { 
		   addElement(new Double(d)); 
		   return this; 
		   }   
		   /** 
		   * @param i the int to add. All ints are promoted to long. 
		   */ 
		   public VarArgs add(int i) 
		   { 
		   addElement(new Long((long)i)); /* N.B. We create a long ! */ 
		   return this; 
		   }   
		   /** 
		   * @param l the long to add. 
		   */ 
		   public VarArgs add(long l) 
		   { 
		   addElement(new Long(l)); 
		   return this; 
		   }   
		   /** 
		   * @param s the String to add. 
		   */ 
		   public VarArgs add(String s) 
		   { 
		   addElement(s); 
		   return this; 
		   }   
		   /* 
		   * @param o the object to add. 
		   */ 
		   public VarArgs addObject(Object o) 
		   { 
		   addElement(o); 
		   return this; 
		   }   
		   /** 
		   * returns a char. 
		   */ 
		   public char getChar() 
		   { 

		   Character cObj = (Character)nextElement(); 
		   return cObj.charValue(); 
		   }   
		   /** 
		   * returns a double. 
		   */ 
		   public double getDouble() 
		   { 
		   Double dObj = (Double)nextElement(); 
		   return dObj.doubleValue(); 
		   }   
		   /** 
		   * returns a long. Note that integers are never stored. 
		   */ 
		   public long getLong() 
		   { 
		   Long lObj = (Long)nextElement(); 
		   return lObj.longValue(); 
		   }   
		   /** 
		   * returns an Object. 
		   */ 
		   public Object getObject() 
		   { 
		   return nextElement(); 
		   }   
		   /** 
		   * returns a String. 
		   */ 
		   public String getString() 
		   { 
		   String str = (String)nextElement(); 
		   return str; 
		   }   
		   public static void main(String[] args) 
		   { 
		   VarArgs f = new VarArgs(7); 
		   int i = 25; 
		   double d = 50.96; 
		   f.add("This is some stuff").add(i).add("Hello World").add(d); 
		   }   
		   /** 
		   * gets the next element from the varargs list 
		   */ 
		   public Object nextElement() 
		   { 
		   Object result = elementAt(next); 
		   next++; 
		   return result; 
		   }   
		   /** 
		   * resets the varargs list to empty 
		   */ 
		   public void reset() 
		   { 
		   setSize(0); 
		   next = 0; 
		   }   
}
