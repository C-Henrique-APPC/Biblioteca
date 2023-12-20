package app.aluno;

import core.util.CrudId;

public class Aluno implements CrudId<Long>, Cloneable{
	Long matricula;
	String nome;

	public Aluno(Long matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}
	public Aluno clone() throws CloneNotSupportedException {

		return (Aluno) super.clone();
	}
	public Long getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public Long getId() {
		return this.matricula;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + "]";
	}
	
	
}
