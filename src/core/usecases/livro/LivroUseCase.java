package core.usecases.livro;

import java.util.List;

import core.entidade.Livro;
import core.usecases.CrudUseCase;

public interface LivroUseCase extends CrudUseCase<Livro> {
	public List<Livro> gerar();
}
