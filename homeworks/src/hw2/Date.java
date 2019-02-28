package hw2;
/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
	private int month;
	private int day;
	private int year;
  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  if (isValidDate(month,day,year) == true) {
		  this.month = month;
		  this.day = day;
		  this.year = year;
		  return;
	  } else {
		  System.out.println("The date is not valid!");
		  System.exit(0);
	  }
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  
/* first try, could pass the test, but could not check if the string is validT_T
  public Date(String s) {
	  int first_find = 0;
	  int second_find = 0;
	  String m,d,y;
	  first_find = s.indexOf("/");				//index of the first "/"
	  second_find = s.indexOf("/",3);			//search from the second character
	  
	  m = s.substring(0,first_find);
	  d = s.substring(first_find+1,second_find);
	  y = s.substring(second_find+1);
	  month = Integer.parseInt(m);
	  day = Integer.parseInt(d);
	  year = Integer.parseInt(y);  
  }
*/
  	// another way, split the string: a very good idea...
  public Date(String s) {
    String[] mdy = s.split("/");
    if(mdy.length != 3 || !mdy[0].matches("[0-9]+") || !mdy[1].matches("[0-9]+") || !mdy[2].matches("[0-9]+")) {
    	System.out.println("the date string input is not valid!");
    	System.exit(0);
    } 
    
    month = Integer.parseInt(mdy[0]);
    day = Integer.parseInt(mdy[1]);
    year = Integer.parseInt(mdy[2]);			//check if it is a valid date, use the isValidDate method.
    
    if(isValidDate(month,day,year) == false) {
    	System.out.println("the date string input is not valid!");
    	System.exit(0);
    }
    
    this.month = month;
    this.day = day;
    this.year = year;
    	
  }
  
  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    if(year % 4 == 0) {
    	if(year % 100 == 0) {
    		if(year % 400 == 0) {
    			return true;
    			}
    		return false;
    	}
    	return true;
    } else {
    	return false;
    }
  }
  
  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    switch (month) {
    case 2:
    	if(isLeapYear(year) == true) {
    		return 29;
    	} else {
    		return 28;
    	}
    case 4:
    case 6:
    case 9:
    case 11:
    	return 30;
    default:
    	return 31;
    }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if(year < 1) {
    	return false;
    } else {
    	if (month>=1 && month<=12) {
    		if (day>=1 && day<= daysInMonth(month, year)) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    String m = String.valueOf(this.month);     //convert integer into String
    String d = String.valueOf(this.day);
    String y = String.valueOf(this.year);
    return (m + "/" + d +"/" + y );
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    if(this.year < d.year) {
    	return true;
    } else {
    	if(this.year > d.year) {
    		return false;
    	} else {									//equal year
    		if(this.month < d.month) {
    			return true;
    		} else {
    			if(this.month > d.month) {
    				return false;
    			} else {							//equal month
    				if(this.day < d.day) {
    					return true;
    				} else {
    					return false;
    				}
    			}
    		}
    	}
    }
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if (this.month == d.month && this.day == d.day && this.year == d.year) {
    	return false;
    } else {
    	if(isBefore(d) == true) {
    		return false;
    	} else {
    		return true;
    	}
    }
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int days = 0;
	  for(int i=1; i<this.month; i++) {
		  days = days + daysInMonth(i,this.year); 
	  }
	  days = days + this.day;
	  return days;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
	    int sumofdays = 0;
	    int numofleapyears = 0;
		int d_sumofdays = 0;
		int d_numofleapyears =0;
	    for(int j=1; j<this.year; j++) {
	    	if(isLeapYear(j) == true) {
	    		numofleapyears++;
	    	}
	    }
		for(int j=1; j<d.year; j++) {
	    	if(isLeapYear(j) == true) {
	    		d_numofleapyears++;
	    	}
	    }

	    sumofdays = numofleapyears + (this.year - 1)*365 + this.dayInYear();					//calculate the sum of all days before the date.
	    d_sumofdays = d_numofleapyears + (d.year - 1)*365 + d.dayInYear();

		return (sumofdays - d_sumofdays);
		
	  }
  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */
    System.out.println("\nTesting isLeapYear function.");
    Date d6 = new Date("1/1/2100");
    Date d7 = new Date("1/1/2000");
    System.out.println("The year of " + d6 + " is a leap year:" + isLeapYear(d6.year));
    System.out.println("The year of " + d7 + " is a leap year:" + isLeapYear(d7.year));
    
    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));    
  }
}
