package core.usecases.livro;

import java.util.List;

import core.entidade.Livro;
import core.service.LivroService;

public class LivroUseCaseImpl implements LivroUseCase {

	private final LivroService service;

	public LivroUseCaseImpl(LivroService livroRepositoryService) {
		this.service = livroRepositoryService;
	}

	@Override
	public Livro consulta(long id) {

		return service.consulta(id);
	}

	@Override
	public void alterar(Livro livro) {
		service.alterar(livro);
	}

	@Override
	public void deletar(long id) {

		service.deletar(id);

	}

	@Override
	public List<Livro> gerar() {

		return gerar();
	}

}
