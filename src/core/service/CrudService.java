package core.service;

import java.util.List;

public interface CrudService<T, ID> {
	public T consulta(ID id);
	public void alterar(ID id, T t);
	public void deletar(ID id);
	public void inserir(T t);
	public List<T> buscaTudo();
	public Boolean exist(ID id);
}
