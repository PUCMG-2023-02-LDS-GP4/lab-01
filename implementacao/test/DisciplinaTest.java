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

    @Test
    public void testAdicionarAluno() throws ExcecaoDisciplinaCheia {
        disciplina.adicionarAluno(aluno);

        assertTrue(disciplina.getAlunos().containsKey(aluno.getCodMatricula()));
    }

    @Test(expected = ExcecaoDisciplinaCheia.class)
    public void testAdicionarAlunoDisciplinaCheia() throws ExcecaoDisciplinaCheia {
        // Preencha a disciplina com o número máximo de alunos permitidos (60).
        for (int i = 1; i <= 60; i++) {
            Aluno aluno = new Aluno("Aluno " + i, "usuario_aluno_" + i, "senha_aluno_" + i, 1000 + i);
            disciplina.adicionarAluno(aluno);
        }

        // Tente adicionar mais um aluno, o que deve gerar uma exceção.
        Aluno alunoExtra = new Aluno("Aluno Extra", "usuario_aluno_extra", "senha_aluno_extra", 2000);
        disciplina.adicionarAluno(alunoExtra);
    }

    @Test
    public void testPossuiProfessor() {
        assertTrue(disciplina.possuiProfessor());
    }

    @Test
    public void testListarAlunos() throws ExcecaoDisciplinaCheia {
        disciplina.adicionarAluno(aluno);
        assertEquals(1, disciplina.listarAlunos().size());
        assertTrue(disciplina.listarAlunos().contains(aluno));
    }

    @Test(expected = ExcecaoDisciplinaComProfessor.class)
    public void testLecionarDisciplinaComProfessor() throws ExcecaoDisciplinaComProfessor {
        professor.lecionar(disciplina);
        assertTrue(professor.getDisciplinasLecionadas().containsKey(disciplina.getId()));

        // Tente lecionar a mesma disciplina novamente, o que deve gerar uma exceção.
        professor.lecionar(disciplina);
    }
}

