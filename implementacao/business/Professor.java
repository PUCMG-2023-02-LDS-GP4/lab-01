package business;

import exceptions.ExcecaoDisciplinaComProfessor;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario{

    // Declaração de variáveis

    private List<Disciplina> disciplinasLecionadas;

    // Construtores
    public Professor(String nome, String usuario, String senha, List<Disciplina> disciplinasLecionadas) {
        super(nome, usuario, senha);
        this.disciplinasLecionadas = disciplinasLecionadas;
    }

    public Professor(String nome, String usuario, String senha) {
        super(nome, usuario, senha);
        this.disciplinasLecionadas = new ArrayList<Disciplina>();
    }

    // Getters e Setters

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void setDisciplinasLecionadas(List<Disciplina> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }

    // Metódos

    /** Função responsável por adicionar uma disciplina à lista de disciplinadas lecionadas do professor.
     * @param  d - Disciplina a ser lecionada.
     * @throws ExcecaoDisciplinaComProfessor - exceção responsável por indicar que existe um professor lecionando a disciplina em questão.
     **/
    public void lecionar(Disciplina d) throws ExcecaoDisciplinaComProfessor {
        if(!d.possuiProfessor()){
            this.disciplinasLecionadas.add(d);
        } else{
            throw new ExcecaoDisciplinaComProfessor();
        }

    }
}
