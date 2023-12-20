package core.exceptions;

public class FoundExceptions extends Exception{

	public FoundExceptions(String msg) {
		super("Registro encontrado: "+msg);
	}
}
