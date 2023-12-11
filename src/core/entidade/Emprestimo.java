package core.entidade;

import java.time.LocalDateTime;

public class Emprestimo implements CrudId<Long> {
	Long id;
	LocalDateTime dataDoEmprestimo;
	Livro livroEmprestado;
	Aluno alunoResponsalve;
	StatusEmprestimo status;

	public Emprestimo(Long id, LocalDateTime dataDoEmprestimo, Livro livroEmprestado, Aluno alunoResponsalve,
			StatusEmprestimo status) {
		this.id = id;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.livroEmprestado = livroEmprestado;
		this.alunoResponsalve = alunoResponsalve;
		this.status = status;
	}

	public LocalDateTime getDataDoEmprestimo() {
		return dataDoEmprestimo;
	}

	public Livro getLivroEmprestado() {
		return livroEmprestado;
	}

	public Aluno getAlunoResponsalve() {
		return alunoResponsalve;
	}

	public StatusEmprestimo getStatus() {
		return status;
	}

	@Override
	public Long getId() {
		return this.id;
	}

}
