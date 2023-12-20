package app.teste;

import java.time.LocalDateTime;

import app.aluno.Aluno;
import app.aluno.AlunoServiceImpl;
import app.emprestimo.EmprestimoRepositorioImpl;
import app.emprestimo.EmprestimoServiceImpl;
import app.emprestimo.StatusEmprestimo;
import app.livro.Livro;
import app.livro.LivroServiceImpl;
import app.livro.StatusLivro;

public class Teste {
	public static LivroServiceImpl serviceImpl = new LivroServiceImpl();
	public static AlunoServiceImpl alunoServiceImpl =  new AlunoServiceImpl();
	public static EmprestimoRepositorioImpl emprestimoRepositorioImpl = new EmprestimoRepositorioImpl();
	public static EmprestimoServiceImpl emprestimoService = new EmprestimoServiceImpl(alunoServiceImpl,serviceImpl,emprestimoRepositorioImpl);

	public static void main(String[] args) {
		Livro l = new Livro(1L, "Teste", 2.0, StatusLivro.LIVRE, "Editora", 1, 2, 2);
		Livro l3 = new Livro(2L, "Teste 3", 2.3, StatusLivro.LIVRE, "Editora", 1, 1, 4);
		Livro l2 = new Livro(3L, "Teste 2", 2.0, StatusLivro.ATRASO, "Editora", 1, 4, 2);
		
		serviceImpl.inserir(l);
		serviceImpl.inserir(l2);
		serviceImpl.inserir(l3);
		
		Aluno aluno1 = new Aluno(1L, "Juninho");
		Aluno aluno2 = new Aluno(2L, "Juninho");
	
		alunoServiceImpl.inserir(aluno1);
		alunoServiceImpl.inserir(aluno2);
		
		System.out.println(alunoServiceImpl.consulta(1l));
	
		emprestimoService.criar(aluno1, l2, LocalDateTime.of(2023,12,12,5,20).toString(), 1L, StatusEmprestimo.PENDENTE);
		emprestimoService.consultar(1L);
		emprestimoService.consultarTodos();
		
		emprestimoService.consultarAtraso(1L);

	}
}

