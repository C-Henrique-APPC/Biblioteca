package core.service;

import core.entidade.Livro;
import core.entidade.StatusLivro;

public interface LivroService extends CrudService<Livro> {

	public StatusLivro statusLivro(long idLivro);

}
