package business;

import java.util.List;

public class Aluno extends Usuario{
    private String nome;
    private String usuario;
    private String senha;
    private int codMatricula;
    private List<Disciplina> disciplinas;

    public Aluno(String nome, String usuario, String senha, int codMatricula, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.codMatricula = codMatricula;
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public void matricular(Disciplina d){}

}
