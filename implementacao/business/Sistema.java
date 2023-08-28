package business;

import java.util.*;

public class Sistema {
    private Usuario usuarioAtual;
    private Map<String, Usuario> usuarios;
    private Set<Curso> cursos;

    public Sistema(){
        this.usuarioAtual = null;
        this.usuarios = new HashMap<String, Usuario>();
        this.cursos = new HashSet<Curso>();
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public void logar(String usuario, String senha){

    }

    public void cadastrar(String usuario, String senha, String nome){}

    public void adicionarCurso(String nome, int creditos, List<Disciplina> disciplinas){}

    public void removerCurso(Disciplina d){}
}
