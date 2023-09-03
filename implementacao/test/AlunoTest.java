package test;

import business.Aluno;
import business.Disciplina;
import exceptions.ExcecaoDisciplinaFechada;
import exceptions.ExcecaoDisciplinaNaoExistente;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Classe de teste para a classe {@link Aluno}.
 */
public class AlunoTest {

    private Aluno aluno;
    private Disciplina disciplina;

    
    @Before
    public void setUp() {
        aluno = new Aluno("Nome do Aluno", "usuario", "senha", 12345);
        disciplina = new Disciplina("Nome da Disciplina", 1, null, true, false);
    }

    /**
     * Testa a matrícula de um aluno em uma disciplina.
     *
     * @throws ExcecaoDisciplinaFechada se a disciplina estiver fechada para inscrições.
     */
    @Test
    public void testMatricularAluno() throws ExcecaoDisciplinaFechada {
        aluno.matricular(disciplina);

        Map<Integer, Disciplina> disciplinasDoAluno = aluno.getDisciplinas();
        assertTrue(disciplinasDoAluno.containsKey(disciplina.getId()));
        assertEquals(disciplina, disciplinasDoAluno.get(disciplina.getId()));
    }

    /**
     * Testa o trancamento de matrícula do aluno em uma disciplina.
     *
     * @throws ExcecaoDisciplinaNaoExistente se a disciplina com o ID informado não existir.
     */
    @Test
    public void testTrancarMatricula() throws ExcecaoDisciplinaNaoExistente {
        Map<Integer, Disciplina> disciplinasDoAluno = new HashMap<>();
        disciplinasDoAluno.put(disciplina.getId(), disciplina);
        aluno.setDisciplinas(disciplinasDoAluno);

        aluno.trancarMatricula(disciplina.getId());

        assertFalse(disciplinasDoAluno.containsKey(disciplina.getId()));
    }

    /**
     * Testa a matrícula de um aluno em uma disciplina fechada (inscrições fechadas).
     *
     * @throws ExcecaoDisciplinaFechada se a disciplina estiver fechada para inscrições.
     */
    @Test(expected = ExcecaoDisciplinaFechada.class)
    public void testMatricularAlunoEmDisciplinaFechada() throws ExcecaoDisciplinaFechada {
        disciplina.setInscricoesAbertas(false);
        aluno.matricular(disciplina);
    }

    /**
     * Testa o trancamento de matrícula do aluno em uma disciplina inexistente.
     *
     * @throws ExcecaoDisciplinaNaoExistente se a disciplina com o ID informado não existir.
     */
    @Test(expected = ExcecaoDisciplinaNaoExistente.class)
    public void testTrancarMatriculaDeDisciplinaInexistente() throws ExcecaoDisciplinaNaoExistente {
        aluno.trancarMatricula(999);
    }
}
