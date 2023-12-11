package core.service;

import java.util.List;

public interface CrudService<T> {
	public T consulta(long id);
	public void alterar(T t);
	public void deletar(long id);
	public void inserir(T t);
	public List<T> buscaTudo();
	public Boolean exist(long id);
}
