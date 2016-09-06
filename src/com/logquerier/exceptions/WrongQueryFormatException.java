package com.logquerier.exceptions;
/**
 * 
 * This exception is thrown in the following cases
 * 1. The first argument is not "QUERY" or "EXIT"
 * 2. The second argument of the query is not a valid IP address
 * 3. The third argument of the query string is either not a 0 or 1
 * 4. The start date/time is after the end date/time
 * 5. The start and end dates do not follow the format(yyyy-MM-dd HH:mm)
 */
public class WrongQueryFormatException extends Exception {
	

	public WrongQueryFormatException(){
        super();
    }

    public WrongQueryFormatException(String message){
        super(message);
    }
    

}
