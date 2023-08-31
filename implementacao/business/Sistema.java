package business;

import exceptions.*;

import java.util.*;

public class Sistema {

    // Declaração de atributos
    private Usuario usuarioAtual;
    private Map<String, Usuario> usuarios;
    private Map<String,Curso> cursos;

    // Construtor

    public Sistema(){
        this.usuarioAtual = null;
        this.usuarios = new HashMap<String, Usuario>();
        this.cursos = new HashMap<String, Curso>();
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

    public Map<String,Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Map<String,Curso> cursos) {
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

    public void adicionarCurso(String nome, int creditos, Map<Integer,Disciplina> disciplinas) throws ExcecaoCursoExistente {
        boolean cursoExiste = this.cursos.containsKey(nome);

        if(cursoExiste){
            throw new ExcecaoCursoExistente();
        }else{
            this.cursos.put(nome,new Curso(nome, creditos, disciplinas));
        }
    }

    /**
     * Função responsável por retirar um curso da lista de cursos presentes no sistema.
     * @param curso - curso a ser removido
     * @throws ExcecaoCursoNaoExistente - exceção responsável por indicar a aûsencia de um usuário presente no sistema referente aos parametros passados.
     */
    public void removerCurso(String curso) throws ExcecaoCursoNaoExistente {
        if(this.cursos.containsKey(curso)){
            this.cursos.remove(curso);
        }else{
            throw new ExcecaoCursoNaoExistente();
        }
    }

    public List<Disciplina> listarDisciplinas(){
        List<Disciplina> disciplinas = new ArrayList<>();

        for (Curso curso : this.cursos.values()) {
            for (Disciplina disciplina : curso.getDisciplinas().values()) {
                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }

    /**
     *
     * @param id - id da disciplina a ser listada
     * @throws ExcecaoSemPermissao - indica que o usuário não tem permissão para fazer a função
     * @return lista de alunos da disciplina listada
     */
    public List<Aluno> listarAlunos(int id) throws ExcecaoSemPermissao {
        if(this.getUsuarioAtual() instanceof Professor){
            List<Aluno> alunos = new ArrayList<>();
            List<Disciplina> todasDisciplinas = this.listarDisciplinas();
            Disciplina disciplinaSelecionada = new Disciplina("", true, true);
            for (Disciplina d : todasDisciplinas) {
                if (d.getId() == id) {
                    disciplinaSelecionada.setAlunos(d.getAlunos());
                }
            }
            alunos.addAll(disciplinaSelecionada.getAlunos().values());
            return alunos;
        }
        else{
            throw new ExcecaoSemPermissao();
        }
    }
}
