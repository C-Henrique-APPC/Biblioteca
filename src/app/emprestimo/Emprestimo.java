package app.emprestimo;

import java.time.LocalDateTime;

import app.aluno.Aluno;
import app.livro.Livro;
import app.tarifa.Tarifa;
import core.util.CrudId;

public class Emprestimo implements CrudId<Long> {
	Long id;
	LocalDateTime dataDoEmprestimo;
	LocalDateTime dataDevolucao;
	Livro livroEmprestado;
	Aluno alunoResponsalve;
	StatusEmprestimo status;
	Double tarifa;

	public Emprestimo(Long id, LocalDateTime dataDoEmprestimo, Livro livroEmprestado, Aluno alunoResponsalve,
			StatusEmprestimo status) {
		this.id = id;
		this.dataDoEmprestimo = dataDoEmprestimo;
		this.livroEmprestado = livroEmprestado;
		this.alunoResponsalve = alunoResponsalve;
		this.status = status;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
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

	public void setStatus(StatusEmprestimo status) {
		this.status = status;
	}
	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	@Override
	public String toString() {
		return "Emprestimo{" +
				"id=" + id +
				", dataDoEmprestimo=" + dataDoEmprestimo +
				", dataDevolucao=" + dataDevolucao +
				", livroEmprestado=" + livroEmprestado +
				", alunoResponsalve=" + alunoResponsalve +
				", status=" + status +
				'}';
	}

}
