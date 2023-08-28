package business;

import exceptions.ExcecaoDisciplinaExistente;
import exceptions.ExcecaoDisciplinaNaoExistente;

import java.util.List;
import java.util.Set;

public class Curso {

    // Declaração de variáveis
    private String nome;
    private int creditos;
    private List<Disciplina> disciplinas;

    // Construtores

    public Curso(String nome, int creditos, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = disciplinas;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    // Metódos

    /** Função auxiliar para verificar se uma disciplina com o nome fornecido como parâmetro já existe na grade.
      * @param  nome - Nome da disciplina a ser verificada.
      * @return true - Se já existe uma disciplina com o nome fornecido na grade do curso.
      * @return false - Caso contrário.
     **/

    public boolean disciplinaJaExistente(String nome){
        for(Disciplina disciplina : this.getDisciplinas() ){
            if(disciplina.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    /** Função responsável por adicionar uma disciplina na grade curricular do curso.
      * @param nome - Nome da disciplina.
      * @param professor - Professor que irá lecionar a disciplina.
      * @param inscricoesAbertas - Binário que define se é possivel a inscrição de novos alunos na disciplina.
      * @param obrigatoria - Binário para definir se a disciplina será obrigatoria ou optativa.
      * @throws ExcecaoDisciplinaExistente - Exceção para indicar que já existe uma disciplina com o nome dado por parâmetro.
     **/
    public void adicionarDisciplina(String nome, Professor professor, boolean inscricoesAbertas, boolean obrigatoria) throws ExcecaoDisciplinaExistente {
        boolean disciplinaExiste = false;
        for(Disciplina d : this.disciplinas){
            if(d.getNome().equals(nome)){
                disciplinaExiste = true;
            }
        }
        if(disciplinaExiste){
            throw new ExcecaoDisciplinaExistente();
        } else{
            this.disciplinas.add(new Disciplina(nome, professor, inscricoesAbertas, obrigatoria));
        }

    }

    /**
     * Função responsável por retirar uma disciplina da grade curricular do curso
     * @param d - disciplina a ser removida.
     * @throws ExcecaoDisciplinaNaoExistente - exceção responsável por indicar que a disciplina passada por parâmetro não existe na grade curricular do curso.
     */
    public void removerDisciplina(Disciplina d) throws ExcecaoDisciplinaNaoExistente {
        if(disciplinaJaExistente(d.getNome())){
           this.disciplinas.remove(d);
        }else{
            throw new ExcecaoDisciplinaNaoExistente();
        }
    }


}
