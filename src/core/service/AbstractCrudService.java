package core.service;

public abstract class AbstractCrudService<T, R, ID> implements CrudService<T, ID> {
	protected R repositorio;

	public AbstractCrudService(R repositorio) {
		this.repositorio = repositorio;
	}

	public R getRepositorio() {
		return repositorio;
	}
	
	
	
}
