package app.teste;

import app.livro.LivroRepositorioImpl;
import core.entidade.Livro;
import core.entidade.StatusLivro;

public class Teste {
	public static void main(String[] args) {
		LivroRepositorioImpl livro = new LivroRepositorioImpl();
		Livro l = new Livro(1L, "Teste", 2.0, StatusLivro.LIVRE, "Editora", 1, 2, 2);
		Livro l2= new Livro(2L, "Teste 2", 2.0, StatusLivro.LIVRE, "Editora", 1, 4, 2);

		livro.inserir(l);
		livro.inserir(l2);
		
		for(Livro liv : livro.getAll()) {
			System.out.println(liv.toString());
		}
		l2.setEditora("Biruleibe");
		livro.atualizar(l2);
		for(Livro liv : livro.getAll()) {
			System.out.println(liv.toString());
		}
		livro.remove(l.getId());
		for(Livro liv : livro.getAll()) {
			System.out.println(liv.toString());
		}
	}
}
