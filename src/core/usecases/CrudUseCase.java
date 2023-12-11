package core.usecases;

public interface CrudUseCase<T> {
	public T consulta(long id);
	public void alterar(T t);
	public void deletar(long id);
	
	//public Boolean existe
}
