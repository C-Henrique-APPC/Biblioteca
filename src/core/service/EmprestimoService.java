package core.service;

import core.entidade.Emprestimo;

public interface EmprestimoService {
	public Boolean jaExisti(long idEmprestimo);
	public void consultar(long idEmprestimo);
	
	public void criar(Emprestimo emprestimo);
	public void alterarStatus(Emprestimo emprestimo);
	public void finalizar(Emprestimo emprestimo);
	
}
