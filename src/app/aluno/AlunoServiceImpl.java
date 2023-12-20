package app.aluno;

import java.util.List;

import core.exceptions.FoundExceptions;
import core.exceptions.NotFoundExceptions;
import core.service.AbstractCrudService;

public class AlunoServiceImpl extends AbstractCrudService<Aluno, AlunoRepositorioImpl, Long> {

	public AlunoServiceImpl() {
		super(new AlunoRepositorioImpl());
	}

	public AlunoServiceImpl(AlunoRepositorioImpl repositorio) {
		super(repositorio);
	}

	@Override
	public Aluno consulta(Long id) {
		try {
			if (!exist(id)) {
				throw new NotFoundExceptions("Livro " + id);
			}
			Aluno cloneL = repositorio.buscar(id);
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
	public void alterar(Long id, Aluno t) {
		try {
			if (!exist(id)) {
				throw new NotFoundExceptions("Aluno " + id);
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
				throw new NotFoundExceptions("Aluno " + id);
			}
			repositorio.deletar(id);
		} catch (NotFoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void inserir(Aluno t) {
		try {

			if (exist(t.getId())) {
				throw new FoundExceptions("Aluno " + t.getId());
			}
			repositorio.inserir(t);
		} catch (FoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Aluno> buscaTudo() {
		return repositorio.buscarTodos();
	}

	@Override
	public Boolean exist(Long id) {
		if (repositorio.get(id) != null) {
			return true;
		}
		return false;
	}

}
