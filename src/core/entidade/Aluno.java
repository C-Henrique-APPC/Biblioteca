package core.entidade;

public class Aluno implements CrudId<Long>{
	Long matricula;
	String nome;

	public Aluno(Long matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
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
