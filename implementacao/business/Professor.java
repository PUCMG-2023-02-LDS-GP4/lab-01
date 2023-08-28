package business;

import java.util.List;

public class Professor extends Usuario{
    private String nome;
    private String usuario;
    private String senha;

    private List<Disciplina> disciplinasLecionadas;

    public Professor(String nome, String usuario, String senha, List<Disciplina> disciplinasLecionadas) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.disciplinasLecionadas = disciplinasLecionadas;
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

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void setDisciplinasLecionadas(List<Disciplina> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }

    public void lecionar(Disciplina d){}
}
