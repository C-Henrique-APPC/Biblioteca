package core.usecases.emprestimo;

import core.entidade.Emprestimo;
import core.service.EmprestimoService;

public class RealizarEmprestimoUseCaseImpl implements RealizarEmprestimoUseCase {
	private final EmprestimoService emprestimoRepositoryService;

	public RealizarEmprestimoUseCaseImpl(EmprestimoService emprestimoRepositoryService) {
		this.emprestimoRepositoryService = emprestimoRepositoryService;
	}

	@Override
	public void salvarEmprestimo(Emprestimo emprestimo) {
		if(emprestimoRepositoryService.jaExisti(emprestimo.getId())) {
			throw new RuntimeException("Emprestimo já ");
		}
	}
	
	

}
