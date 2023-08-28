package business;

import exceptions.ExcecaoDisciplinaFechada;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario{

    // Declaração de variáveis
    private String nome;
    private String usuario;
    private String senha;
    private int codMatricula;
    private List<Disciplina> disciplinas;

    // Construtores

    public Aluno(String nome, String usuario, String senha, int codMatricula) {
        super(nome, usuario, senha);
        this.codMatricula = codMatricula;
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public Aluno(String nome, String usuario, String senha) {
        super(nome, usuario, senha);

    }

    // Getters e Setters

    public int getCodMatricula() {
        return codMatricula;
    }

    public void setCodMatricula(int codMatricula) {
        this.codMatricula = codMatricula;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    // Metodos

    /**
     * Função responsável por matricular um aluno na disciplina
     * @param d - Disciplina a ser adicionada no curriculo do aluno
     * @throws ExcecaoDisciplinaFechada - exceção responsável por indicar que a disciplina não está com inscrições abertas.
     */

    public void matricular(Disciplina d) throws ExcecaoDisciplinaFechada {
        if(d.isInscricoesAbertas()){
            this.disciplinas.add(d);
            d.getAlunos().add(this);
        }else{
            throw new ExcecaoDisciplinaFechada();
        }
    }

}
