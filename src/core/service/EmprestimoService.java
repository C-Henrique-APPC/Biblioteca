package core.service;

import app.aluno.Aluno;
import app.emprestimo.Emprestimo;
import app.emprestimo.StatusEmprestimo;
import app.livro.Livro;

public interface EmprestimoService {
	public Boolean jaExisti(long idEmprestimo);
	public void consultar(long idEmprestimo);
	
	public void criar(Aluno aluno, Livro livro, String data, Long id , StatusEmprestimo status);
	public void alterarStatus(Emprestimo emprestimo, StatusEmprestimo status);
	public void finalizar(Emprestimo emprestimo);
	
}
