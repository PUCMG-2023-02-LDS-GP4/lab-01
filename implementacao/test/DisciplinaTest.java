package test;

import business.Aluno;
import business.Curso;
import business.Disciplina;
import business.Professor;
import exceptions.ExcecaoDisciplinaCheia;
import exceptions.ExcecaoQuantidadeAlunosExcessiva;
import exceptions.ExcecaoDisciplinaComProfessor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DisciplinaTest {

    private Disciplina disciplina;
    private Aluno aluno;
    private Professor professor;

    @Before
    public void setUp() {
        Curso curso = new Curso("Nome do Curso", 60, null);
        professor = new Professor("Nome do Professor", "usuario_prof", "senha_prof");
        disciplina = new Disciplina("Nome da Disciplina", curso, professor, true, false);
        aluno = new Aluno("Nome do Aluno", "usuario_aluno", "senha_aluno", 12345);
    }

    /**
     * Testa a adição de um aluno à disciplina.
     * Verifica se o aluno é adicionado corretamente à lista de alunos.
     *
     * @throws ExcecaoDisciplinaCheia Se a disciplina estiver cheia.
     */
    @Test
    public void testAdicionarAluno() throws ExcecaoDisciplinaCheia {
        disciplina.adicionarAluno(aluno);
        assertTrue(disciplina.getAlunos().containsKey(aluno.getCodMatricula()));
    }

    /**
     * Testa a adição de um aluno a uma disciplina cheia.
     * Deve lançar uma exceção {@link ExcecaoDisciplinaCheia}.
     *
     * @throws ExcecaoDisciplinaCheia Se a disciplina estiver cheia.
     */
    @Test(expected = ExcecaoDisciplinaCheia.class)
    public void testAdicionarAlunoDisciplinaCheia() throws ExcecaoDisciplinaCheia {
        // Preenche a disciplina com o número máximo de alunos permitidos (60).
        for (int i = 1; i <= 60; i++) {
            Aluno aluno = new Aluno("Aluno " + i, "usuario_aluno_" + i, "senha_aluno_" + i, 1000 + i);
            disciplina.adicionarAluno(aluno);
        }

        // deve lançar exceção.
        Aluno alunoExtra = new Aluno("Aluno Extra", "usuario_aluno_extra", "senha_aluno_extra", 2000);
        disciplina.adicionarAluno(alunoExtra);
    }

    /**
     * Testa o método {@link Disciplina#possuiProfessor()}.
     * Verifica se a disciplina possui um professor.
     */
    @Test
    public void testPossuiProfessor() {
        assertTrue(disciplina.possuiProfessor());
    }

    /**
     * Testa o método {@link Disciplina#listarAlunos()}.
     * Verifica se a lista de alunos da disciplina é retornada corretamente.
     *
     * @throws ExcecaoDisciplinaCheia Se a disciplina estiver cheia.
     */
    @Test
    public void testListarAlunos() throws ExcecaoDisciplinaCheia {
        disciplina.adicionarAluno(aluno);
        assertEquals(1, disciplina.listarAlunos().size());
        assertTrue(disciplina.listarAlunos().contains(aluno));
    }

    /**
     * Testa a lecionação de uma disciplina por um professor.
     * Verifica se o professor não pode lecionar a mesma disciplina novamente.
     *
     * @throws ExcecaoDisciplinaComProfessor Se a disciplina já tiver um professor.
     */
    @Test(expected = ExcecaoDisciplinaComProfessor.class)
    public void testLecionarDisciplinaComProfessor() throws ExcecaoDisciplinaComProfessor {
        professor.lecionar(disciplina);
        assertTrue(professor.getDisciplinasLecionadas().containsKey(disciplina.getId()));

        professor.lecionar(disciplina);
    }
}
