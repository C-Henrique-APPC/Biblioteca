package core.repositorio;

import java.util.List;

import core.util.CrudId;

public abstract class AbstractCrudRepositorio<T extends CrudId<ID>, ID > extends AbstractRepositorio<T, ID> implements CrudRepositorio<T, ID> {

	
	@Override
	public void inserir(T t) {
		add(t.getId(), t);		
	}
	@Override
	public T buscar(ID id) {
		return get(id);
	}
	@Override
	public void atualizar(ID id, T t) {
		update(id, t);
	}
	@Override
	public void deletar(ID id) {
		remove(id);
	}
	@Override
	public List<T> buscarTodos() {
		return getAll() ;
	}
	@Override
	public Boolean existi(ID id) {
		return exists(id);
	}
	
}
