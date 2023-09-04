package test;

import business.Curso;
import business.Disciplina;
import business.Professor;
import exceptions.ExcecaoDisciplinaExistente;
import exceptions.ExcecaoDisciplinaNaoExistente;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Classe de testes para a classe {@link Curso}.
 */
public class CursoTest {


    private Curso curso;
    private Professor professor;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        professor = new Professor("Nome do Professor", "usuario_prof", "senha_prof");
        curso = new Curso("Nome do Curso", 60, new HashMap<>());
        disciplina = new Disciplina("Nome da Disciplina", curso, professor, true, false);
    }

    /**
     * Testa a remoção de uma disciplina inexistente.
     * Deve lançar uma exceção {@link ExcecaoDisciplinaNaoExistente}.
     *
     * @throws ExcecaoDisciplinaNaoExistente Se a disciplina não existir.
     */
    @Test(expected = ExcecaoDisciplinaNaoExistente.class)
    public void testRemoverDisciplinaInexistente() throws ExcecaoDisciplinaNaoExistente {
        curso.removerDisciplina(999);
    }

    /**
     * Testa o método de salvar dados da classe {@link Curso}.
     * Verifica se os dados são salvos corretamente no formato esperado.
     */
    @Test
    public void testSalvarDados() {
        String dados = curso.salvarDados();
        assertEquals("Nome do Curso;" + curso.getId() + ";60", dados);
    }
}
