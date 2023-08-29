package business;

import exceptions.ExcecaoDisciplinaExistente;
import exceptions.ExcecaoDisciplinaNaoExistente;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Curso {

    // Declaração de variáveis
    private String nome;
    private int id;
    private int creditos;
    private Map<Integer,Disciplina> disciplinas;

    // Construtores

    public Curso(String nome, int creditos, Map<Integer, Disciplina> disciplinas) {
        this.nome = nome;
        this.id = new Random().nextInt(101) + 100;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Map<Integer,Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Map<Integer,Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    // Metódos

    /** Função auxiliar para verificar se uma disciplina com o nome fornecido como parâmetro já existe na grade.
      * @param  id - Identificador da disciplina a ser verificada.
      * @return true - Se já existe uma disciplina com o nome fornecido na grade do curso.
      * @return false - Caso contrário.
     **/

    public boolean disciplinaJaExistente(int id){
        if(this.disciplinas.containsKey(id)) {
            return true;
        }
        return false;
    }

    /** Função responsável por adicionar uma disciplina na grade curricular do curso.
      * @param nome - Nome da disciplina.
      * @param professor - Professor que irá lecionar a disciplina.
      * @param inscricoesAbertas - Binário que define se é possivel a inscrição de novos alunos na disciplina.
      * @param obrigatoria - Binário para definir se a disciplina será obrigatoria ou optativa.
      * @throws ExcecaoDisciplinaExistente - Exceção para indicar que já existe uma disciplina a ID cadastrada.
     **/
    public void adicionarDisciplina(String nome, Professor professor, boolean inscricoesAbertas, boolean obrigatoria) throws ExcecaoDisciplinaExistente {
            Disciplina novaDisciplina = new Disciplina(nome, professor, inscricoesAbertas, obrigatoria);
            if(disciplinaJaExistente(novaDisciplina.getId())){
                throw new ExcecaoDisciplinaExistente();
            } else{
                this.disciplinas.put(novaDisciplina.getId(), novaDisciplina);
            }

    }

    /**
     * Função responsável por retirar uma disciplina da grade curricular do curso
     * @param id - disciplina a ser removida.
     * @throws ExcecaoDisciplinaNaoExistente - exceção responsável por indicar que a disciplina passada por parâmetro não existe na grade curricular do curso.
     */
    public void removerDisciplina(int id) throws ExcecaoDisciplinaNaoExistente {
        if(disciplinaJaExistente(id)){
           this.disciplinas.remove(id);
        }else{
            throw new ExcecaoDisciplinaNaoExistente();
        }
    }


}
