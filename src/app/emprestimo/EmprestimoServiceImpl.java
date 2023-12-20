package app.emprestimo;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import app.aluno.Aluno;
import app.aluno.AlunoServiceImpl;
import app.livro.Livro;
import app.livro.LivroServiceImpl;
import app.tarifa.Tarifa;
import core.exceptions.NotFoundExceptions;
import core.service.EmprestimoService;

public class EmprestimoServiceImpl implements EmprestimoService {
	private final Integer DIAS_EMPRESTIMO_MAX = 5;

	protected Emprestimo emprestimo;
	protected AlunoServiceImpl serviceAluno;
	protected LivroServiceImpl serviceLivro;
	protected EmprestimoRepositorioImpl repositorioEmprestimo;
	protected Tarifa tarifa = new Tarifa();

	public EmprestimoServiceImpl(AlunoServiceImpl alunoServiceImpl, LivroServiceImpl livroServiceImpl,
			EmprestimoRepositorioImpl emprestimoRepositorioImpl) {
		serviceAluno = alunoServiceImpl;
		serviceLivro = livroServiceImpl;
		repositorioEmprestimo = emprestimoRepositorioImpl;
	}

	public void consultarAtraso(Long id) {
		Double valor_atraso = 0.0;
		NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y h:m:s");

		try {
			if (!repositorioEmprestimo.existi(id)) {
				throw new NotFoundExceptions("Emprestimo não existe");
			}
			Emprestimo emprestimo;
			emprestimo = repositorioEmprestimo.get(id);

			if (emprestimo.getStatus().equals(StatusEmprestimo.PENDENTE)) {
				previsaoDevolucao(emprestimo.getDataDoEmprestimo(), DIAS_EMPRESTIMO_MAX);
				valor_atraso = tarifa.calTarifa(emprestimo.getDataDoEmprestimo(), LocalDateTime.now());
			}

			System.out.println("\n===== CONSULTA DE EMPRESITMO EM ATRASO:" + "\nID Emprestimo: " + emprestimo.getId()
					+ "\nData de criacão : " + emprestimo.getDataDoEmprestimo().format(formatter)
					+ "\nPrevisão da devolução: " + emprestimo.getDataDevolucao().format(formatter)
					+ "\nValor a pagar : " + formatoMoeda.format(valor_atraso));

		} catch (NotFoundExceptions e) {
			e.printStackTrace();
		}

	}

	private void previsaoDevolucao(LocalDateTime dataDoEmprestimo, Integer dIAS_EMPRESTIMO_MAX2) {
		emprestimo.setDataDevolucao(dataDoEmprestimo.plusDays(dIAS_EMPRESTIMO_MAX2));
	}

	public void consultarTodos() {
		System.out.println(repositorioEmprestimo.getAll());
	}

	@Override
	public Boolean jaExisti(long idEmprestimo) {

		return repositorioEmprestimo.existi(idEmprestimo) ? true : false;

	}

	@Override
	public void consultar(long idEmprestimo) {
		try {
			if (!repositorioEmprestimo.existi(idEmprestimo)) {
				throw new NotFoundExceptions("Emprestimo não existe");
			}
			System.out.println(repositorioEmprestimo.get(idEmprestimo));
		} catch (NotFoundExceptions e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void criar(Aluno aluno, Livro livro, String data, Long id, StatusEmprestimo status) {
		try {
			if (!serviceAluno.exist(aluno.getId()) && !serviceLivro.exist(livro.getId())) {
				throw new NotFoundExceptions("Aluno ou livro não encontrado");
			}
			emprestimo = new Emprestimo(id, LocalDateTime.parse(data), livro, aluno, status);
			repositorioEmprestimo.inserir(emprestimo);
			System.out.println("Emprestimo do ID " + id + " criado.");
		} catch (NotFoundExceptions e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void alterarStatus(Emprestimo emprestimo, StatusEmprestimo status) {
		try {

			if (!jaExisti(emprestimo.getId())) {
				throw new NotFoundExceptions("Sem registro.");
			}
			emprestimo.setStatus(status);

		} catch (NotFoundExceptions e) {
			e.printStackTrace();
		}
	}

	@Override
	public void finalizar(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		System.out.println("");

	}

}
