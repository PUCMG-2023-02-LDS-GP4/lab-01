package business;

import exceptions.ExcecaoDisciplinaCheia;
import exceptions.ExcecaoQuantidadeAlunosExcessiva;

import java.util.*;

public class Disciplina {


    // Declaração de variáveis
    private String nome;
    private int id;
    private Map<Integer,Aluno> alunos;
    private Professor professor;
    private boolean inscricoesAbertas;
    private boolean obrigatoria;

    // Construtor

    public Disciplina(String nome, Map<Integer,Aluno> alunos, Professor professor, boolean inscricoesAbertas, boolean obrigatoria) throws ExcecaoQuantidadeAlunosExcessiva {

        if(alunos.size() > 60){
            throw new ExcecaoQuantidadeAlunosExcessiva();
        }else{
            this.nome = nome;
            this.id = new Random().nextInt(101);
            this.alunos = alunos;
            this.professor = professor;
            this.inscricoesAbertas = inscricoesAbertas;
            this.obrigatoria = obrigatoria;
        }
    }

    public Disciplina(String nome, Professor professor, boolean inscricoesAbertas, boolean obrigatoria) {
        this.nome = nome;
        this.id = new Random().nextInt(101);
        this.alunos = new HashMap<Integer,Aluno>();
        this.professor = professor;
        this.inscricoesAbertas = inscricoesAbertas;
        this.obrigatoria = obrigatoria;
    }

    public Disciplina(String nome, boolean inscricoesAbertas, boolean obrigatoria) {
        this.nome = nome;
        this.id = new Random().nextInt(101);
        this.alunos = new HashMap<Integer,Aluno>();
        this.professor = null;
        this.inscricoesAbertas = inscricoesAbertas;
        this.obrigatoria = obrigatoria;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Aluno> getAlunos() {
        return this.alunos;
    }

    public void setAlunos(Map<Integer,Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isInscricoesAbertas() {
        return inscricoesAbertas;
    }

    public void setInscricoesAbertas(boolean inscricoesAbertas) {
        this.inscricoesAbertas = inscricoesAbertas;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {

        this.obrigatoria = obrigatoria;
    }



    // Metodos

    /** Função responsável por adicionar alunos na lista de alunos da disciplina
    * @param aluno - Aluno a ser adicionado na lista de alunos matriculados na disciplina.
    * @throws ExcecaoDisciplinaCheia - exceção responsável por indicar que o número de alunos máximos permitidos por disciplina foi atingido.
    **/

    public void adicionarAluno(Aluno aluno) throws ExcecaoDisciplinaCheia {
        if(this.alunos.size() < 60){
            this.alunos.put(aluno.getCodMatricula(), aluno);
        }else{
            throw new ExcecaoDisciplinaCheia();
        }
    }

    /** Função auxiliar para indicar se a disciplina possui um professor ou não
     * @return true - caso exista um professor lecionando-a
     * @return false - caso contrário.
     **/

    public boolean possuiProfessor(){
        if(this.professor.getNome() == null){
            return false;
        }
        return true;
    }

}
