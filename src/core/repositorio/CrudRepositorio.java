package core.repositorio;

import java.util.List;

public interface CrudRepositorio<T, ID > {
	public T buscar(ID id);
	public List<T> buscarTodos();
	
	public void deletar(ID id);
	public void inserir(T t);
	public void atualizar(T t);
	
}
