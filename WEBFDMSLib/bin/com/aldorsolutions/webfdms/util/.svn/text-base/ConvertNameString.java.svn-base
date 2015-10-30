package com.aldorsolutions.webfdms.util;

/**
 * Split Full Name String into first, middle, last
 * Creation date: (11/1/2002 3:25:20 PM)
 * @author: 
 */
public class ConvertNameString {
	
	public final static String PARSEFML = new String("FML");
	public final static String PARSEFLM = new String("FLM");
	public final static String PARSELFM = new String("LFM");
	public final static String PARSELMF = new String("LMF");
	public final static String PARSEFL = new String("FL");
	public final static String PARSELF = new String("LF");
	public final static String PARSEF = new String("F");
	public final static String PARSEL = new String("L");
	public final static String PARSEM = new String("M");
	
	public static String firstName = new String();
	public static String middleName = new String();
	public static String lastName = new String();
	
	public ConvertNameString() {
	    super();
	}
	public ConvertNameString(String parseOrder, String parseString) {
	    this();
	    splitName(parseOrder, parseString);
	}
	/**
	 * Returns the First Name from the Full Name String.
	 */
	public String getFirstName() {
	   return firstName;
	}
	/**
	 * Returns the Last Name from the Full Name String.
	 */
	public String getLastName() {
	   return lastName;
	}
	/**
	 * Returns the Middle Name from the Full Name String.
	 */
	public String getMiddleName() {
	   return middleName;
	}
	/**
	 * Sets the First Name from the String.
	 */
	public void setFirstName(String parseFirst) {
	   firstName = parseFirst;
	}
	/**
	 * Returns the Last Name from the Full Name String.
	 */
	public void setLastName(String parseLast) {
	   lastName = parseLast;
	}
	/**
	 * Sets the Middle Name from the String.
	 */
	public void setMiddleName(String parseMiddle) {
	   middleName = parseMiddle;
	}
	public void splitName(String parseOrder, String parseString) {
		setFirstName("");
		setMiddleName("");
		setLastName("");
	    if (parseString == null) {
	       return;
	    }
	    if (parseOrder == null) {
		   parseOrder = PARSEFML;
	    }
	    
	    java.util.StringTokenizer names = new java.util.StringTokenizer(parseString);

	    int nameCount = names.countTokens();
	    switch(nameCount) {
	       case 0:
	         break;
	       case 1:
	       	  setFirstName(names.nextToken());
	   	      //if (parseOrder.equals(PARSEF)) {setFirstName(names.nextToken());}
	   	      //if (parseOrder.equals(PARSEM)) {setMiddleName(names.nextToken());}
	   	      //if (parseOrder.equals(PARSEL)) {setLastName(names.nextToken());}
	   	      break;
	       case 2:
	   	      if (parseOrder.equals(PARSEFL)) {
		   	     setFirstName(names.nextToken());
		   	     setLastName(names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSELF)) {
		   	     setLastName(names.nextToken());
		   	     setFirstName(names.nextToken());
		      }   
	   	      break;
	       case 3:
	   	      if (parseOrder.equals(PARSEFML)) {
		   	      setFirstName(names.nextToken());
		   	      setMiddleName(names.nextToken());
		   	      setLastName(names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSEFLM)) {
		   	      setFirstName(names.nextToken());
		   	      setLastName(names.nextToken());
		   	      setMiddleName(names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSELFM)) {
		   	      setLastName(names.nextToken());
		   	      setFirstName(names.nextToken());
		   	      setMiddleName(names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSELMF)) {
		   	      setLastName(names.nextToken());
		   	      setMiddleName(names.nextToken());
		   	      setFirstName(names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSEFL)) {
		   	      setFirstName(names.nextToken());
		   	      setLastName(names.nextToken()+" "+names.nextToken());
		      }
	   	      if (parseOrder.equals(PARSELF)) {
		   	      setLastName(names.nextToken());
		   	      setFirstName(names.nextToken()+" "+names.nextToken());
		      }
	   	      break;
	       default:
	       	  // if more than three names then regardless of parse format
	       	  // put first token in "first name" and remaining tokens
	       	  // in "last name".
	          StringBuffer lastname = new StringBuffer();
		      boolean addspace = false;
			  if (nameCount>0){
				  setFirstName(names.nextToken());
			  }
		      while (names.hasMoreTokens()) {
		         if (addspace) {
			        lastname.append(" ");
		         } else {
			        addspace = true;
		         }
		         lastname.append(names.nextToken());
		      }
		      setLastName(lastname.toString());
	    }
 
   }                  
}
