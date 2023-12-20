package app.livro;

import java.util.List;

import core.exceptions.FoundExceptions;
import core.exceptions.NotFoundExceptions;
import core.service.AbstractCrudService;

public class LivroServiceImpl extends AbstractCrudService<Livro, LivroRepositorioImpl, Long> {

	public LivroServiceImpl() {
		super(new LivroRepositorioImpl());
	}

	public LivroServiceImpl(LivroRepositorioImpl repositorio) {
		super(repositorio);
	}

	@Override
	public Livro consulta(Long id) {
		try {
			if (!exist(id)) {
				throw new NotFoundExceptions("Livro " + id);
			}
			Livro cloneL = repositorio.buscar(id);
			return cloneL.clone();
		} catch (NotFoundExceptions e) {
			e.printStackTrace();
			return null;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void alterar(Long id, Livro t) {
		try {
			if (!exist(id)) {
				throw new NotFoundExceptions("Livro " + id);
			}
			repositorio.atualizar(id, t);
		} catch (NotFoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletar(Long id) {
		try {
			if (!exist(id)) {
				throw new NotFoundExceptions("Livro " + id);
			}
			repositorio.deletar(id);
		} catch (NotFoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void inserir(Livro t) {
		try {

			if (exist(t.getId())) {
				throw new FoundExceptions("Livro " + t.getId());
			}
			repositorio.inserir(t);
		} catch (FoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Livro> buscaTudo() {

		return repositorio.getAll();
	}

	@Override
	public Boolean exist(Long id) {

		if (repositorio.get(id) != null) {
			return true;
		}
		return false;
	}

}
