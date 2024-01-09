package app.teste;

import java.time.LocalDateTime;

import app.aluno.Aluno;
import app.aluno.AlunoServiceImpl;
import app.emprestimo.EmprestimoRepositorioImpl;
import app.emprestimo.EmprestimoServiceImpl;
import app.livro.Livro;
import app.livro.LivroServiceImpl;
import core.util.Imprimir;

public class Teste {
	public static LivroServiceImpl serviceImpl = new LivroServiceImpl();
	public static AlunoServiceImpl alunoServiceImpl =  new AlunoServiceImpl();
	public static EmprestimoRepositorioImpl emprestimoRepositorioImpl = new EmprestimoRepositorioImpl();
	public static EmprestimoServiceImpl emprestimoService = new EmprestimoServiceImpl(alunoServiceImpl,serviceImpl,emprestimoRepositorioImpl);

	public static void main(String[] args) {
		Livro l1 = new Livro(1L, "Teste", 2.0, 5D,  "Editora", 1, 2, 2);
		Livro l3 = new Livro(2L, "Teste 3", 2.3, 1D, "Editora", 1, 1, 4);
		Livro l2 = new Livro(3L, "Teste 2", 2.0, 3D, "Editora", 1, 4, 2);
		
		serviceImpl.inserir(l1);
		serviceImpl.inserir(l2);
		serviceImpl.inserir(l3);
		
		Aluno aluno1 = new Aluno(1L, "Juninho");
		Aluno aluno2 = new Aluno(2L, "Karlinho");

		alunoServiceImpl.inserir(aluno1);
		alunoServiceImpl.inserir(aluno2);
		
		System.out.println(alunoServiceImpl.consulta(1L));
	
		emprestimoService.criar(aluno1, l2, LocalDateTime.of(2023,12,12,5,20).toString(), 1L);
		emprestimoService.criar(aluno1, l2, LocalDateTime.of(2023,12,12,5,20).toString(), 2L);
		emprestimoService.criar(aluno2, l3, LocalDateTime.now().toString(), 3L);
		//emprestimoService.consultar(1L);
		//emprestimoService.consultarTodos();
		
		/*emprestimoService.consultarAtraso(2L);
		emprestimoService.alterarStatus(1L, StatusEmprestimo.FINALIZADO);
		*/
		emprestimoService.consultarAtraso(1L);
		emprestimoService.consultarTodos();
		//emprestimoService.validarEmprestimos();
		emprestimoService.finalizar(emprestimoService.carregarEmprestimo(1L));
		emprestimoService.consultar(1L);
		emprestimoService.realizarPg(emprestimoService.carregarEmprestimo(1L));
		emprestimoService.finalizar(emprestimoService.carregarEmprestimo(1L));
		emprestimoService.consultar(1L);

	}
}

