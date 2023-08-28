package business;

import exceptions.*;

import java.util.*;

public class Sistema {

    // Declaração de atributos
    private Usuario usuarioAtual;
    private Map<String, Usuario> usuarios;
    private Set<Curso> cursos;

    // Construtor

    public Sistema(){
        this.usuarioAtual = null;
        this.usuarios = new HashMap<String, Usuario>();
        this.cursos = new HashSet<Curso>();
    }

    // Getters e Setters

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

    /**
     * Função responsável por atribuir o usuário logado no sistema.
     * @param usuario - campo de usuário
     * @param senha - campo de senha
     * @throws ExcecaoUsuarioNaoEncontrado - exceção responsável por indicar a aûsencia de um usuário presente no sistema referente aos parametros passados.
     */
    public void logar(String usuario, String senha) throws ExcecaoUsuarioNaoEncontrado {
        Usuario u = this.usuarios.get(usuario);
            if(u.getUsuario().equals(usuario) && u.getSenha().equals(senha)) {
                this.usuarioAtual = u;
            }
            if(u == null){
                throw new ExcecaoUsuarioNaoEncontrado();
            }
    }

    /**
     * Função responsável por cadastrar um usuário no sistema
     * @param usuario - campo de usuário.
     * @param senha - campo de senha.
     * @param nome - campo de nome.
     * @param tipo - informa o tipo de usuário que será cadastrado.
     * @throws ExcecaoUsuarioCadastrado - exceção responsável por indicar que já existe um usuário no sistema cadastrado com o mesmo campo de usuário informado.
     * @throws ExcecaoTipoIncorreto - exceção para indicar o digito incorreto referente ao tipo para o cadastro de um novo usuário no sistema.
     */
    public void cadastrar(String usuario, String senha, String nome, int tipo) throws ExcecaoUsuarioCadastrado, ExcecaoTipoIncorreto {
        if(!this.usuarios.containsKey(usuario)){
            switch (tipo){
                case 1:
                    this.usuarios.put(usuario, new Aluno(usuario, senha, nome));
                case 2:
                    this.usuarios.put(usuario, new Professor(usuario, senha, nome));
                default:
                    throw new ExcecaoTipoIncorreto();
            }
        }else{
            throw new ExcecaoUsuarioCadastrado();
        }

    }

    /**
     * Função responsável por adicionar um curso no sistema.
     * @param nome - Nome do curso a ser adicionado
     * @param creditos - Quantidade de créditos do curso
     * @param disciplinas - Disciplinas presentes no curso.
     * @throws ExcecaoCursoExistente - exceção responsável por indicar a presença de um curso com o nome passado por parâmetro.
     */

    public void adicionarCurso(String nome, int creditos, List<Disciplina> disciplinas) throws ExcecaoCursoExistente {
        boolean cursoExiste = false;
        for(Curso c : this.cursos){
            if(c.getNome().equals(nome)){
                cursoExiste = true;
            }
        }
        if(cursoExiste){
            throw new ExcecaoCursoExistente();
        }else{
            this.cursos.add(new Curso(nome, creditos, disciplinas));
        }
    }

    /**
     * Função responsável por retirar um curso da lista de cursos presentes no sistema.
     * @param c - curso a ser removido
     * @throws ExcecaoCursoNaoExistente - exceção responsável por indicar a aûsencia de um usuário presente no sistema referente aos parametros passados.
     */
    public void removerCurso(Curso c) throws ExcecaoCursoNaoExistente {
        if(this.cursos.contains(c)){
            this.cursos.remove(c);
        }else{
            throw new ExcecaoCursoNaoExistente();
        }
    }
}
