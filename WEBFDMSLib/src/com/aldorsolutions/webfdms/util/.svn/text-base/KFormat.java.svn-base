package com.aldorsolutions.webfdms.util;

		   /* 
		   * Gary Cornell and Cay S. Horstmann, Core Java (Book/CD-ROM) 
		   * Published By SunSoft Press/Prentice-Hall 
		   * Copyright (C) 1996 Sun Microsystems Inc. 
		   * All Rights Reserved. ISBN 0-13-596891-7 
		   * 
		   * Permission to use, copy, modify, and distribute this 
		   * software and its documentation for NON-COMMERCIAL purposes 
		   * and without fee is hereby granted provided that this 
		   * copyright notice appears in all copies. 
		   * 
		   * THE AUTHORS AND PUBLISHER MAKE NO REPRESENTATIONS OR 
		   * WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER 
		   * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
		   * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
		   * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHORS 
		   * AND PUBLISHER SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED 
		   * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING 
		   * THIS SOFTWARE OR ITS DERIVATIVES. 
		   */ 

		   /** 
		   * A class for formatting numbers that follows printf conventions. 
		   * Also implements C-like atoi and atof functions 
		   * @version 1.03 25 Oct 1997 
		   * @author Cay Horstmann 
		   */ 

		   /* 
		   * Modified by Kirk A. Abbott to accept variable arguement 
		   * list. 
		   * April 26, 1998. 
		   */ 

		   // package corejava; 

		   import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

		   public class KFormat 

		   { /** 
		   * Formats the number following printf conventions. 
		   * @param s the format string following printf conventions 
		   * The string has a prefix, a format code and a suffix. The prefix and suffix 
		   * become part of the formatted output. The format code directs the 
		   * formatting of the (single) parameter to be formatted. The code has the 
		   * following structure 
		   * <ul> 
		   * <li> a % (required) 
		   * <li> a modifier (optional) 
		   * <dl> 
		   * <dt> + <dd> forces display of + for positive numbers 
		   * <dt> 0 <dd> show leading zeroes 
		   * <dt> - <dd> align left in the field 
		   * <dt> space <dd> prepend a space in front of positive numbers 
		   * <dt> # <dd> use "alternate" format. Add 0 or 0x for octal or hexadecimal numbers. Don't suppress trailing zeroes in
		   general floating point format. 
		   * </dl> 
		   * <li> an integer denoting field width (optional) 
		   * <li> a period followed by an integer denoting precision (optional) 
		   * <li> a format descriptor (required) 
		   * <dl> 
		   * <dt>f <dd> floating point number in fixed format 
		   * <dt>e, E <dd> floating point number in exponential notation (scientific format). The E format results in an uppercase E
		   for the exponent (1.14130E+003), the e format in a lowercase e. 
		   * <dt>g, G <dd> floating point number in general format (fixed format for small numbers, exponential format for large
		   numbers). Trailing zeroes are suppressed. The G format results in an uppercase E for the exponent (if any), the g format in
		   a lowercase e. 
		   * <dt>d, i <dd> integer in decimal 
		   * <dt>x <dd> integer in hexadecimal 
		   * <dt>o <dd> integer in octal 
		   * <dt>s <dd> string 
		   * <dt>c <dd> character 
		   * </dl> 
		   * </ul> 
		   * @exception IllegalArgumentException if bad format 
		   */ 
		   private int width; 
		   private int precision; 
		   private StringBuffer pre = new StringBuffer(32); 
		   private boolean leading_zeroes; 
		   private boolean show_plus; 
		   private boolean alternate; 
		   private boolean show_space; 
		   private boolean left_align; 
		   private char fmt; // one of cdeEfgGiosxXos 

		   
		   public KFormat() { ; }   
		   /** 
		   * Converts a string of digits to an double 
		   * @param s a string 
		   */ 

		   public static double atof(String s) 
		   { 
		   int i = 0; 
		   int sign = 1; 
		   double r = 0; // integer part 
		   double p = 1; // exponent of fractional part 
		   int state = 0; // 0 = int part, 1 = frac part 

		   while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++; 
		   if (i < s.length() && s.charAt(i) == '-') { sign = -1; i++; } 
		   else if (i < s.length() && s.charAt(i) == '+') { i++; } 
		   while (i < s.length()) { 
		   char ch = s.charAt(i); 
		   if ('0' <= ch && ch <= '9') { 
		   if (state == 0) 
		   r = r * 10 + ch - '0'; 
		   else if (state == 1) { 
		   p = p / 10; 
		   r = r + p * (ch - '0'); 
		   } 
		   } 
		   else if (ch == '.') { 
		   if (state == 0) state = 1; 
		   else return sign * r; 
		   } 
		   else if (ch == 'e' || ch == 'E') { 
		   long e = (int)parseLong(s.substring(i + 1), 10); 
		   return sign * r * Math.pow(10, e); 
		   } 
		   else return sign * r; 
		   i++; 
		   } 
		   return sign * r; 
		   }   
		   /** 
		   * Converts a string of digits (decimal, octal or hex) to an integer 
		   * @param s a string 
		   * @return the numeric value of the prefix of s representing a 
		   * base 10 integer 
		   */ 
		   public static int atoi(String s) 
		   { 
		   return (int)atol(s); 
		   }   
		   /** 
		   * Converts a string of digits (decimal, octal or hex) to a long integer 
		   * @param s a string 
		   * @return the numeric value of the prefix of s representing 
		   * a base 10 integer 
		   */ 
		   public static long atol(String s) 
		   { 
		   int i = 0; 
		   int len = s.length(); 
		   while (i < len && Character.isWhitespace(s.charAt(i))) 
		   i++; 
		   if (i < len && s.charAt(i) == '0') { 
		   if (i + 1 < len && (s.charAt(i + 1) == 'x' || 
		   s.charAt(i + 1) == 'X')) 
		   return parseLong(s.substring(i + 2), 16); 
		   else return parseLong(s, 8); 
		   } 
		   else return parseLong(s, 10); 
		   }   
		   private static String convert(long x, int n, int m, String d) 
		   { 
		   if (x == 0) return "0"; 
		   StringBuffer r = new StringBuffer(32); /* not sure what size */ 
		   while (x != 0) { 
		   r.append(d.charAt((int)(x & m))); 
		   x = x >>> n; 
		   } 
		   r.reverse(); 
		   return r.toString(); 
		   }   
		   /* 
		   * This can be optimized by using a string buffer. 
		   */ 
		   private String exp_format(double d) 
		   { 
		   String f = ""; 
		   int e = 0; 
		   double dd = d; 
		   double factor = 1; 
		   if (d != 0) { 
		   while (dd > 10) { e++; factor /= 10; dd = dd / 10; } 
		   while (dd < 1) { e--; factor *= 10; dd = dd * 10; } 
		   } 
		   if ((fmt == 'g' || fmt == 'G') && e >= -4 && e < precision) 
		   return fixed_format(d); 

		   d = d * factor; 
		   f = f + fixed_format(d); 

		   if (fmt == 'e' || fmt == 'g') 
		   f = f + "e"; 
		   else 
		   f = f + "E"; 

		   String p = "000"; 
		   if (e >= 0) { 
		   f = f + "+"; 
		   p = p + e; 
		   } 
		   else { 
		   f = f + "-"; 
		   p = p + (-e); 
		   } 

		   return f + p.substring(p.length() - 3, p.length()); 
		   }   
		   /* 
		   * This can be optimized by using a string buffer. 
		   */ 
		   private String fixed_format(double d) 
		   { 
		   boolean removeTrailing 
		   = (fmt == 'G' || fmt == 'g') && !alternate; 
		   // remove trailing zeroes and decimal point 

		   if (d > 0x7FFFFFFFFFFFFFFFL) return exp_format(d); 
		   if (precision == 0) 
		   return (long)(d + 0.5) + (removeTrailing ? "" : "."); 

		   long whole = (long)d; 
		   double fr = d - whole; // fractional part 
		   if (fr >= 1 || fr < 0) return exp_format(d); 

		   double factor = 1; 
		   String leading_zeroes = ""; 
		   for (int i = 1; i <= precision && factor <= 0x7FFFFFFFFFFFFFFFL; i++) { 
		   factor *= 10; 
		   leading_zeroes = leading_zeroes + "0"; 
		   } 
		   long l = (long) (factor * fr + 0.5); 
		   if (l >= factor) { l = 0; whole++; } // CSH 10-25-97 

		   String z = leading_zeroes + l; 
		   z = "." + z.substring(z.length() - precision, z.length()); 

		   if (removeTrailing) { 
		   int t = z.length() - 1; 
		   while (t >= 0 && z.charAt(t) == '0') t--; 
		   if (t >= 0 && z.charAt(t) == '.') t--; 
		   z = z.substring(0, t + 1); 
		   } 

		   return whole + z; 
		   }   
		   /** 
		   * Formats a character into a string (like sprintf in C) 
		   * @param x the value to format 
		   * @return the formatted string 
		   */ 
		   public String form(char c) 
		   { 
		   if (fmt != 'c') 
		   throw new java.lang.IllegalArgumentException(); 

		   String r = "" + c; 
		   return pad(r); 
		   }   
		   /** 
		   * Formats a double into a string (like sprintf in C) 
		   * @param x the number to format 
		   * @return the formatted string 
		   * @exception IllegalArgumentException if bad argument 
		   */ 
		   public String form(double x) 
		   { 
		   String r; 
		   if (precision < 0) precision = 6; 
		   int s = 1; 
		   if (x < 0) { x = -x; s = -1; } 
		   if (fmt == 'f') 
		   r = fixed_format(x); 
		   else if (fmt == 'e' || fmt == 'E' || fmt == 'g' || fmt == 'G') 
		   r = exp_format(x); 
		   else throw new java.lang.IllegalArgumentException(); 

		   return pad(sign(s, r)); 
		   }   
		   /** 
		   * Formats a long integer into a string (like sprintf in C) 
		   * @param x the number to format 
		   * @return the formatted string 
		   */ 
		   public String form(long x) 
		   { 
		   String r; 
		   int s = 0; 
		   if (fmt == 'd' || fmt == 'i') { 
		   if (x < 0) { 
		   r = ("" + x).substring(1); 
		   s = -1; 
		   } 
		   else { 
		   r = "" + x; 
		   s = 1; 
		   } 
		   } 
		   else if (fmt == 'o') 
		   r = convert(x, 3, 7, "01234567"); 
		   else if (fmt == 'x') 
		   r = convert(x, 4, 15, "0123456789abcdef"); 
		   else if (fmt == 'X') 
		   r = convert(x, 4, 15, "0123456789ABCDEF"); 
		   else throw new java.lang.IllegalArgumentException(); 

		   return pad(sign(s, r)); 
		   }   
		   /** 
		   * Formats a string into a larger string (like sprintf in C) 
		   * @param x the value to format 
		   * @return the formatted string 
		   */ 
		   public String form(String s) 
		   { 
		   if (fmt != 's') 
		   throw new java.lang.IllegalArgumentException(); 
		   if (precision >= 0) s = s.substring(0, precision); 
		   return pad(s); 
		   }   
		   /** 
		   * This is non standard as far as the C standard is concerned. 
		   * But may be useful. 
		   */ 
		   public static String formatString(String fmt, VarArgs ap) 
		   { 
		   KFormat format = new KFormat(); 
		   StringBuffer result = format.parseFormat(fmt, ap); 
		   return result.toString(); 
		   }   
		   /* 
		   * fprintf -- Uses a PrintStream. 
		   * prints a formatted number following printf conventions 
		   * @param s a PrintWriter, fmt the format string 
		   * @param ap a list of things to print 
		   */ 
		   public static int fprintf(java.io.PrintStream s, String fmt, VarArgs ap) 
		   { 
		   KFormat format = new KFormat(); 
		   StringBuffer result = format.parseFormat(fmt, ap); 
		   s.print(result); 
		   return result.length(); 
		   }   
		   public static int fprintf(java.io.PrintWriter s, String fmt, char x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.fprintf(s, fmt, ap); 
		   }   
		   public static int fprintf(java.io.PrintWriter s, String fmt, double x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.fprintf(s, fmt, ap); 
		   }   
		   public static int fprintf(java.io.PrintWriter s, String fmt, int x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.fprintf(s, fmt, ap); 
		   }   
		   public static int fprintf(java.io.PrintWriter s, String fmt, long x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.fprintf(s, fmt, ap); 
		   }   
		   /** 
		   * fprintf -- Uses a PrintWriter. 
		   * Under jdk1.1 PrintWriter is the preferred way of printing 
		   * a text/unicode stream rather than PrintStream. 
		   * 
		   * prints a formatted number following printf conventions 
		   * @param s a PrintWriter, fmt the format string 
		   * @param x a list of things to print 
		   */ 
		   public static int fprintf(java.io.PrintWriter s, String fmt, VarArgs ap) 
		   { 
		   KFormat format = new KFormat(); 
		   StringBuffer result = format.parseFormat(fmt, ap); 
		   s.print(result); 
		   return result.length(); 
		   }   
		   public static int fprintf(java.io.PrintWriter s, String fmt, String x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.fprintf(s, fmt, ap); 
		   }   
		   /** 
		   * a test stub for the format class 
		   */ 
		   public static void main(String[] a) 
		   { 
		   double x = 1.23456789012; 
		   double y = 123; 
		   double z = 1.2345e30; 
		   double w = 1.02; 
		   double u = 1.234e-5; 
		   int d = 0xCAFE; 

		   char c = 'K'; 
		   long l = 3456789L; 
		   String str = "Hello World"; 

		   KFormat.printf("x = |%f|\n", x); 
		   KFormat.printf("u = |%20f|\n", u); 
		   KFormat.printf("x = |% .5f|\n", x); 
		   KFormat.printf("w = |%20.5f|\n", w); 
		   KFormat.printf("x = |%020.5f|\n", x); 
		   KFormat.printf("x = |%+20.5f|\n", x); 
		   KFormat.printf("x = |%+020.5f|\n", x); 
		   KFormat.printf("x = |% 020.5f|\n", x); 
		   KFormat.printf("y = |%#+20.5f|\n", y); 
		   KFormat.printf("y = |%-+20.5f|\n", y); 
		   KFormat.printf("z = |%20.5f|\n", z); 

		   KFormat.printf("x = |%e|\n", x); 
		   KFormat.printf("u = |%20e|\n", u); 
		   KFormat.printf("x = |% .5e|\n", x); 
		   KFormat.printf("w = |%20.5e|\n", w); 
		   KFormat.printf("x = |%020.5e|\n", x); 
		   KFormat.printf("x = |%+20.5e|\n", x); 
		   KFormat.printf("x = |%+020.5e|\n", x); 
		   KFormat.printf("x = |% 020.5e|\n", x); 
		   KFormat.printf("y = |%#+20.5e|\n", y); 
		   KFormat.printf("y = |%-+20.5e|\n", y); 

		   KFormat.printf("x = |%g|\n", x); 
		   KFormat.printf("z = |%g|\n", z); 
		   KFormat.printf("w = |%g|\n", w); 
		   KFormat.printf("u = |%g|\n", u); 
		   KFormat.printf("y = |%.2g|\n", y); 
		   KFormat.printf("y = |%#.2g|\n", y); 

		   KFormat.printf("d = |%d|\n", d); 
		   KFormat.printf("d = |%20d|\n", d); 
		   KFormat.printf("d = |%020d|\n", d); 
		   KFormat.printf("d = |%+20d|\n", d); 
		   KFormat.printf("d = |% 020d|\n", d); 
		   KFormat.printf("d = |%-20d|\n", d); 
		   KFormat.printf("d = |%20.8d|\n", d); 
		   KFormat.printf("d = |%x|\n", d); 
		   KFormat.printf("d = |%20X|\n", d); 
		   KFormat.printf("d = |%#20x|\n", d); 
		   KFormat.printf("d = |%020X|\n", d); 
		   KFormat.printf("d = |%20.8x|\n", d); 
		   KFormat.printf("d = |%o|\n", d); 
		   KFormat.printf("d = |%020o|\n", d); 
		   KFormat.printf("d = |%#20o|\n", d); 
		   KFormat.printf("d = |%#020o|\n", d); 
		   KFormat.printf("d = |%20.12o|\n", d); 

		   KFormat.printf("s = |%-20s|\n", "Hello"); 
		   KFormat.printf("s = |%-20c|\n", '!'); 

		   // regression test to confirm fix of reported bugs 

		   KFormat.printf("|%i|\n", Long.MIN_VALUE); 

		   KFormat.printf("|%6.2e|\n", 0.0); 
		   KFormat.printf("|%6.2g|\n", 0.0); 

		   KFormat.printf("|%6.2f|\n", 9.99); 
		   KFormat.printf("|%6.2f|\n", 9.999); 

		   KFormat.printf("|%6.0f|\n", 9.999); 

		   // 
		   // quick test of the VarArgs features using printf 
		   // 
		   VarArgs ap = new VarArgs(); 
		   ap = new VarArgs(); 
		   KFormat.fprintf(System.out, "%g %20.8e %20.5e\n", 
		   ap.add(x).add(y).add(z)); 

		   KFormat.fprintf(System.out, "str = %s, c = %c, d = %d, l = %d\n", 
		   ap.add(str).add(c).add(d).add(l)); 

		   // quick & dirty VarArgs creation. Not recommended for loops. 
		   KFormat.fprintf(System.out, "one = %d, two = %d", 
		   (new VarArgs(2)).add(1).add(2)); 

		   // 
		   // Test the fprintf printwriter code. 
		   // 
		   try { 
		   PrintWriter out = 
		   new PrintWriter(new BufferedWriter(new FileWriter("foo.txt"))); 
		   KFormat.fprintf(out, "str = %s\tc = %c\td = %d\tl = %d\n", 
		   ap.add(str).add(c).add(d).add(l)); 
		   out.close(); 
		   } catch (IOException e) { 
		   System.out.println(e); 
		   }
		   // Test sprintf
		   StringBuffer dest = new StringBuffer();
		   ap = new VarArgs();
		   ap.add(x);
		   KFormat.sprintf(dest, "x = |%12.2f|", ap);
		   System.out.println("\nvalue of dest="+dest+"<end of value>");
		   }   
		   /* 
		   * FIX FIX FIX -- an oppurtunity for using a stringbuffer here. 
		   */ 
		   private String pad(String r) 
		   { 
		   String p = repeat(' ', width - r.length()); 
		   if (left_align) { 
		   pre.append(r); 
		   pre.append(p); 
		   return pre.toString(); 
		   } 
		   else { 
		   pre.append(p); 
		   pre.append(r); 
		   return pre.toString(); 
		   } 
		   }   
		   public StringBuffer parseFormat(String s, VarArgs ap) 
		   { 
		   StringBuffer result = new StringBuffer(128); 

		   int length = s.length(); 
		   int parse_state = 0; 
		   // 0 = prefix, 1 = flags, 2 = width, 3 = precision, 
		   // 4 = format, 5 = end 
		   int i = 0; 

		   while (i < length) { 
		   parse_state = 0; 
		   resetMachine(); 
		   while (parse_state == 0) { 
		   if (i >= length) parse_state = 5; 
		   else if (s.charAt(i) == '%') { 
		   if (i < length - 1) { 
		   if (s.charAt(i + 1) == '%') { 
		   pre.append('%'); 
		   i++; 
		   } 
		   else 
		   parse_state = 1; 
		   } 
		   else throw new java.lang.IllegalArgumentException(); 
		   } 
		   else 
		   pre.append(s.charAt(i)); 
		   i++; 
		   } 
		   while (parse_state == 1) { 
		   if (i >= length) parse_state = 5; 
		   else if (s.charAt(i) == ' ') show_space = true; 
		   else if (s.charAt(i) == '-') left_align = true; 
		   else if (s.charAt(i) == '+') show_plus = true; 
		   else if (s.charAt(i) == '0') leading_zeroes = true; 
		   else if (s.charAt(i) == '#') alternate = true; 
		   else { parse_state = 2; i--; } 
		   i++; 
		   } 
		   while (parse_state == 2) { 
		   if (i >= length) parse_state = 5; 
		   else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') { 
		   width = width * 10 + s.charAt(i) - '0'; 
		   i++; 
		   } 
		   else if (s.charAt(i) == '.') { 
		   parse_state = 3; 
		   precision = 0; 
		   i++; 
		   } 
		   else 
		   parse_state = 4; 
		   } 
		   while (parse_state == 3) { 
		   if (i >= length) parse_state = 5; 
		   else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') { 
		   precision = precision * 10 + s.charAt(i) - '0'; 
		   i++; 
		   } 
		   else 
		   parse_state = 4; 
		   } 
		   if (parse_state == 4) { 
		   if (i >= length) parse_state = 5; 
		   else fmt = s.charAt(i); 
		   i++; 
		   } 

		   /* 
		   * Finished parsing. Pre and fmt should be set up 
		   * Now apply the formatting to the args. 
		   */ 
		   String tmpStr = null; 
		   switch (fmt) { 
		   case 'c': 
		   tmpStr = form(ap.getChar()); 
		   break; 
		   case 'i': case 'd': 
		   tmpStr = form(ap.getLong()); /* note that we return a 
		   * long. */ 
		   break; 
		   case 'l': case 'L': 
		   tmpStr = form(ap.getLong()); 
		   break; 
		   case 'f': 
		   case 'g': case 'G': 
		   case 'e': case 'E': 
		   tmpStr = form(ap.getDouble()); 
		   break; 
		   case 's': 
		   tmpStr = form(ap.getString()); 
		   break; 
		   case 'o': 
		   case 'x': case 'X': 
		   tmpStr = form(ap.getLong()); 
		   break; 
		   default: 
		   /* we saw a malformed % or we are at the end 
		   * of the string without seeing a %. Copy whatever 
		   * is in 'pre' to the output verbatim */ 
		   tmpStr = pre.toString(); 
		   break; 
		   } // switch 

		   if (tmpStr != null) 
		   result.append(tmpStr); 
		   } 

		   /* 
		   * Finished building our expanded stringbuffer. 
		   * Reset our varargs & return. 
		   */ 
		   ap.reset(); 
		   return result; 
		   }   
		   private static long parseLong(String s, int base) 
		   { 
		   int i = 0; 
		   int sign = 1; 
		   long r = 0; 
		   int len = s.length(); 

		   while (i < len && Character.isWhitespace(s.charAt(i))) 
		   i++; 
		   if (i < len && s.charAt(i) == '-') { sign = -1; i++; } 
		   else if (i < len && s.charAt(i) == '+') { i++; } 
		   while (i < len) { 
		   char ch = s.charAt(i); 
		   if ('0' <= ch && ch < '0' + base) 
		   r = r * base + ch - '0'; 
		   else if ('A' <= ch && ch < 'A' + base - 10) 
		   r = r * base + ch - 'A' + 10 ; 
		   else if ('a' <= ch && ch < 'a' + base - 10) 
		   r = r * base + ch - 'a' + 10 ; 
		   else 
		   return r * sign; 
		   i++; 
		   } 
		   return r * sign; 
		   }   
		   /** 
		   * printf 
		   * prints a formatted number following printf conventions 
		   * @param s a PrintStream 
		   * @param fmt the format string 
		   * @param x the character to print 
		   */ 
		   public static int printf(String fmt, char x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.printf(fmt, ap); 
		   }   
		   /** 
		   * prints a formatted number following printf conventions 
		   * @param fmt the format string 
		   * @param x the double to print 
		   */ 
		   public static int printf(String fmt, double x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.printf(fmt, ap); 
		   }   
		   /** 
		   * prints a formatted number following printf conventions 
		   * @param fmt the format string 
		   * @param x the int to print 
		   */ 
		   public static int printf(String fmt, int x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.printf(fmt, ap); 
		   }   
		   /** 
		   * prints a formatted number following printf conventions 
		   * @param s a PrintStream 
		   * @param fmt the format string 
		   * @param x the long to print 
		   */ 
		   public static int printf(String fmt, long x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.printf(fmt, ap); 
		   }   
		   /** 
		   * printf 
		   * prints formatted text & numbers following printf conventions 
		   * @param fmt the format string 
		   * @param ap the variable length list of things to print 
		   */ 
		   public static int printf(String fmt, VarArgs ap) 
		   { 
		   KFormat format = new KFormat(); 
		   StringBuffer result = format.parseFormat(fmt, ap); 
		   System.out.print(result); 
		   return result.length(); 
		   }   
		   /** 
		   * printf 
		   * prints a formatted string following printf conventions 
		   * @param s a PrintStream, fmt the format string 
		   * @param x a string to print 
		   */ 
		   public static int printf(String fmt, String x) 
		   { 
		   VarArgs ap = new VarArgs(1); 
		   ap.add(x); 
		   return KFormat.printf(fmt, ap); 
		   }   
		   private static String repeat(char c, int n) 
		   { 
		   if (n <= 0) return ""; 
		   StringBuffer s = new StringBuffer(n); 
		   for (int i = 0; i < n; i++) 
		   s.append(c); 
		   return s.toString(); 
		   }   
		   private void resetMachine() 
		   { 
		   width = 0; 
		   precision = -1; 
		   pre.setLength(0); 
		   leading_zeroes = false; 
		   show_plus = false; 
		   alternate = false; 
		   show_space = false; 
		   left_align = false; 
		   fmt = ' '; 
		   }   
		   private String sign(int s, String r) 
		   { 
		   String p = ""; 
		   if (s < 0) p = "-"; 
		   else if (s > 0) { 
		   if (show_plus) p = "+"; 
		   else if (show_space) p = " "; 
		   } 
		   else { 
		   if (fmt == 'o' && alternate && r.length() > 0 
		   && r.charAt(0) != '0') 
		   p = "0"; 
		   else if (fmt == 'x' && alternate) 
		   p = "0x"; 
		   else if (fmt == 'X' && alternate) 
		   p = "0X"; 
		   } 
		   int w = 0; 
		   if (leading_zeroes) 
		   w = width; 
		   else if ((fmt == 'd' || fmt == 'i' || fmt == 'x' || 
		   fmt == 'X' || fmt == 'o') && precision > 0) 
		   w = precision; 

		   return p + repeat('0', w - p.length() - r.length()) + r; 
		   }   
		   /** 
		   * sprintf 
		   * prints formatted text & numbers following printf conventions 
		   * into to provided buffer. It will *append* to the buffer which 
		   * is somewhat different semantics than sprintf. This sprintf is 
		   * a vast improvement over stdlib sprintf as it allows formatting 
		   * text into a variable size buffer. 
		   * 
		   * @dest the given string buffer. 
		   * @param fmt the format string 
		   * @param ap the variable length list of things to print 
		   */ 
		   public static int sprintf(StringBuffer dest, String fmt, VarArgs ap) 
		   { 
		   KFormat format = new KFormat(); 
		   StringBuffer result = format.parseFormat(fmt, ap); 
		   dest.append(result.toString()); 
		   return result.length(); 
		   }   
}
