package app.emprestimo;

import app.aluno.Aluno;
import app.aluno.AlunoServiceImpl;
import app.livro.Livro;
import app.livro.LivroServiceImpl;
import app.tarifa.Tarifa;
import core.exceptions.NotFoundExceptions;
import core.service.EmprestimoService;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class EmprestimoServiceImpl implements EmprestimoService {
    private final Integer DIAS_EMPRESTIMO_MAX = 5;
    protected AlunoServiceImpl alunoService = new AlunoServiceImpl();
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y h:mm:s");

        try {
            if (!repositorioEmprestimo.existi(id)) {
                throw new NotFoundExceptions("Emprestimo não existe");
            }
            Emprestimo emprestimo;
            emprestimo = repositorioEmprestimo.get(id);

            if (!emprestimo.getStatus().equals(StatusEmprestimo.PENDENTE)) {
                System.out.println("Emprestimo em questão está como " + emprestimo.getStatus());
                return;
            }

            emprestimo.setDataDevolucao(previsaoDevolucao(emprestimo.getDataDoEmprestimo(), DIAS_EMPRESTIMO_MAX));
            valor_atraso = tarifa.calTarifa(emprestimo.getDataDoEmprestimo(), LocalDateTime.now());
            emprestimo.setTarifa(valor_atraso);
            System.out.println("\n===== CONSULTA DE EMPRESITMO EM ATRASO:" + "\nID Emprestimo: " + emprestimo.getId()
                    + "\nData de criacão : " + emprestimo.getDataDoEmprestimo().format(formatter)
                    + "\nPrevisão da devolução: " + emprestimo.getDataDevolucao().format(formatter)
                    + "\nValor a pagar : " + (valor_atraso == null ? "0" : formatoMoeda.format(valor_atraso)));

        } catch (NotFoundExceptions e) {
            e.printStackTrace();
        }

    }

    public LocalDateTime previsaoDevolucao(LocalDateTime dataDoEmprestimo, Integer dIAS_EMPRESTIMO_MAX2) {
        return dataDoEmprestimo.plusDays(dIAS_EMPRESTIMO_MAX2);
    }

    public void consultarTodos() {
        validarEmprestimos();
        System.out.println(repositorioEmprestimo.getAll());
    }

    public void validarEmprestimos() {
        for (Emprestimo emprest : repositorioEmprestimo.getAll()) {
            verifiDataEmpres(emprest);
        }

    }

    public void verifiDataEmpres(Emprestimo emprestimo) {
        LocalDateTime dataPlusDays = emprestimo.getDataDoEmprestimo().plusDays(this.DIAS_EMPRESTIMO_MAX);
        if (LocalDateTime.now().compareTo(dataPlusDays) > 0) {
            alterarStatus(emprestimo.getId(), StatusEmprestimo.PENDENTE);
        }
    }


    @Override
    public Boolean jaExisti(long idEmprestimo) {

        return repositorioEmprestimo.existi(idEmprestimo) ? true : false;

    }

    @Override
    public void consultar(long idEmprestimo) {
        Emprestimo empConsultado;
        try {
            if (!repositorioEmprestimo.existi(idEmprestimo)) {
                throw new NotFoundExceptions("Emprestimo não existe");
            }
            empConsultado = repositorioEmprestimo.get(idEmprestimo);
            verifiDataEmpres(empConsultado);
            System.out.println(empConsultado);
        } catch (NotFoundExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void criar(Aluno aluno, Livro livro, String data, Long id) {
        if (jaExisti(id)) {
            System.out.println("Emprestimo existente");
            return;
        }
        try {
            if (!serviceAluno.exist(aluno.getId()) && !serviceLivro.exist(livro.getId())) {
                throw new NotFoundExceptions("Aluno ou livro não encontrado");
            }

            if (verEmpreAlunoPend(aluno.getId())) {
                System.out.println("Aluno tem pendencias de emprestimo");
                return;
            }
            if(livro.getQuantidadeD().compareTo(0.0) < 1){
                System.out.println("Sem estoque para o Livro do ID "+livro.getId());
                return;
            }
            emprestimo = new Emprestimo(id, LocalDateTime.parse(data), livro, aluno, StatusEmprestimo.ATIVO);
            repositorioEmprestimo.inserir(emprestimo);
            livro.setQuantidadeD(livro.getQuantidadeD() - 1 );
            serviceLivro.alterar(livro.getId(), livro);
            System.out.println("Emprestimo do ID " + id + " criado.");


        } catch (NotFoundExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    public Boolean verEmpreAlunoPend(Long id) {
        for (Emprestimo emprestimo : repositorioEmprestimo.getAll()) {
            if (emprestimo.alunoResponsalve.getId() == id) {
                verifiDataEmpres(emprestimo);
                if (emprestimo.status.equals(StatusEmprestimo.PENDENTE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void alterarStatus(Long id, StatusEmprestimo status) {
        try {

            if (!jaExisti(id)) {
                throw new NotFoundExceptions("Sem registro.");
            }
            if (status.equals(StatusEmprestimo.FINALIZADO)) {
                emprestimo.setStatus(status);

            }
            if (status.equals(StatusEmprestimo.PENDENTE)) {
                emprestimo.setStatus(status);

            }
            if (status.equals(StatusEmprestimo.ATIVO)) {
                emprestimo.setStatus(status);

            }

        } catch (NotFoundExceptions e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalizar(Emprestimo emprestimo) {

        Emprestimo empreFinalizado = repositorioEmprestimo.get(emprestimo.getId());
        /*LocalDateTime dataPlusDays = emprestimo.getDataDoEmprestimo().plusDays(this.DIAS_EMPRESTIMO_MAX);
        if (LocalDateTime.now().compareTo(dataPlusDays) > 0) {
            System.out.println("Realize o pagamento antes de finalizar");
            return;
        }*/
        if(!empreFinalizado.getTarifa().equals(0.0)){
            System.out.println("Emprestimo com tarifa em conta, por favor pague o que deve!");
            return;
        }
        Livro livroFinalizado = serviceLivro.consulta(empreFinalizado.livroEmprestado.getId());

        empreFinalizado.setStatus(StatusEmprestimo.FINALIZADO);

        livroFinalizado.setQuantidadeD(livroFinalizado.getQuantidadeD() + 1);
        empreFinalizado.setDataDevolucao(LocalDateTime.now());

        System.out.println("Emprestimo "+empreFinalizado.getId()+" finalizado com sucesso");



    }

    @Override
    public Emprestimo carregarEmprestimo(Long id) {
        return repositorioEmprestimo.get(id);

    }

    @Override
    public void realizarPg(Emprestimo emprestimo) {
        emprestimo.setTarifa(0.0);
        System.out.println("Emprestimo pago!");
    }

}
