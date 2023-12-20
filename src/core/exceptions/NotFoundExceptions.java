package core.exceptions;

import java.lang.Exception;

public class NotFoundExceptions extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundExceptions(String msg) {
		super("Não foi encotrado : "+ msg);		
	}
}
