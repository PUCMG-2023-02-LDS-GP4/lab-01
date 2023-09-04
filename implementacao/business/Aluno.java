package business;

import exceptions.ExcecaoDisciplinaFechada;
import exceptions.ExcecaoDisciplinaNaoExistente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aluno extends Usuario{

    // Declaração de variáveis
    private String nome;
    private String usuario;
    private String senha;
    private int codMatricula;
    private Map<Integer,Disciplina> disciplinas;

    // Construtores

    public Aluno(String nome, String usuario, String senha, int codMatricula) {
        super(nome, usuario, senha);
        this.codMatricula = codMatricula;
        this.disciplinas = new HashMap<Integer,Disciplina>();
    }

    public Aluno(String nome, String usuario, String senha) {
        super(nome, usuario, senha);
        this.disciplinas = new HashMap<Integer,Disciplina>();
    }

    // Getters e Setters

    public int getCodMatricula() {
        return codMatricula;
    }

    public void setCodMatricula(int codMatricula) {
        this.codMatricula = codMatricula;
    }

    public Map<Integer,Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Map<Integer,Disciplina> disciplinas) {
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
            this.disciplinas.put(d.getId(),d);
            d.getAlunos().put(this.getCodMatricula(),this);
        }else{
            throw new ExcecaoDisciplinaFechada();
        }
    }

    /**
     * Função para trancar a matrícula do aluno em alguma disciplina
     * @param id - Identificador da disciplina a ser trancada
     * @throws ExcecaoDisciplinaNaoExistente - exceção responsável por indicar que a disciplina com id informado não existe.
     */

    public void trancarMatricula(int id) throws ExcecaoDisciplinaNaoExistente {
        if(this.getDisciplinas().containsKey(id)){
            this.disciplinas.remove(id);
        }else{
            throw new ExcecaoDisciplinaNaoExistente();
        }
    }

    public List<Disciplina> listarDisciplinas(){
        return this.getDisciplinas().values().stream().toList();
    }

}
