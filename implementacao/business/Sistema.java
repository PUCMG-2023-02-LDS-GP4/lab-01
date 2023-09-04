package business;

import exceptions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Sistema {

    // Declaração de atributos
    private Usuario usuarioAtual;
    private Map<String, Usuario> usuarios;
    private Map<String,Curso> cursos;
    private static final String arqAlunos = "files\\alunos.txt";
    private static final String arqCursos = "files\\cursos.txt";
    private static final String arqDisciplina = "files\\disciplinas.txt";
    // Construtor

    public Sistema() throws IOException, InvalidParameterException{
        this.usuarioAtual = null;
        this.usuarios = new HashMap<String, Usuario>();
        this.cursos = new HashMap<String, Curso>();

        this.lerCursos();
        this.lerDisciplinas();
        this.lerAlunos();
        
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
                if(tipo == 1)
                    this.usuarios.put(usuario, new Aluno(usuario, senha, nome));
                else if (tipo == 2)
                    this.usuarios.put(usuario, new Professor(usuario, senha, nome));
                else
                    throw new ExcecaoTipoIncorreto();
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
            Disciplina disciplinaSelecionada = new Disciplina("",null, true, true);
            for (Disciplina d : todasDisciplinas) {
                if (d.getId() == id) {
                    disciplinaSelecionada.setCurso(d.getCurso());
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

    public void matricular(int id) throws ExcecaoDisciplinaFechada, ExcecaoDisciplinaNaoExistente {
        Usuario user = this.getUsuarioAtual();
        Disciplina d = null;

        List<Curso> cursos = (List<Curso>) this.getCursos().values().stream().toList();
        for(Curso c : cursos){
            if(c.getDisciplinas().containsKey(id)){
                d = c.getDisciplinas().get(id);
            }
        }
        if(user instanceof Aluno){
            Aluno a = (Aluno) this.getUsuarios().get(user.getUsuario());
            if(d == null){
                throw new ExcecaoDisciplinaNaoExistente();
            }
            a.matricular(d);
            System.out.println("Você se matriculou na disciplina: " + d.getNome());
        }
    }

    public void trancarMatriculaAluno(int id) throws ExcecaoDisciplinaNaoExistente {
        Usuario user = this.getUsuarioAtual();

        if(user instanceof Aluno){
            Aluno a = (Aluno) this.getUsuarios().get(user.getUsuario());
            Disciplina d = a.getDisciplinas().get(id);
            a.trancarMatricula(id);
            System.out.println("Você foi desmatriculado da disciplina: " + d.getNome());
        }else{
            throw new ExcecaoDisciplinaNaoExistente();
        }
    }

    public List<Disciplina> listarDisciplinasAluno(){
        Usuario user = this.getUsuarioAtual();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        if(user instanceof Aluno){
            Aluno a = (Aluno) this.getUsuarios().get(user.getUsuario());
            disciplinas = a.listarDisciplinas();
            return disciplinas;
        }
        return disciplinas;
    }

    public List<Usuario> listarAlunosSistema() {
        List<Usuario> alunos = new ArrayList<>();
        alunos = this.getUsuarios().values().stream().filter((u) -> u instanceof Aluno).toList();
        return alunos;
    }

    public List<Disciplina> listarDisciplinasProfessor(){
        Usuario user = this.getUsuarioAtual();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        if(user instanceof Professor){
            Professor p = (Professor) this.getUsuarios().get(user.getUsuario());
            disciplinas = p.getDisciplinasLecionadas().values().stream().toList();
            return disciplinas;
        }
        return disciplinas;
    }

    public void lecionarProfessor(int id) throws ExcecaoDisciplinaComProfessor, ExcecaoDisciplinaNaoExistente {
        Usuario user = this.getUsuarioAtual();
        Disciplina d = null;
        List<Curso> cursos = (List<Curso>) this.getCursos().values().stream().toList();
        for(Curso c : cursos){
            if(c.getDisciplinas().containsKey(id)){
                d = c.getDisciplinas().get(id);
            }
        }
        if(user instanceof Professor){
            Professor p = (Professor) this.getUsuarios().get(user.getUsuario());
            if(d != null){
                if(!d.possuiProfessor()) {
                    p.lecionar(d);
                }
            }else{
                throw new ExcecaoDisciplinaNaoExistente();
            }
        }
    }

     public void lerCursos() throws IOException, InvalidParameterException {

        // Lendo Cursos
        try (Scanner scanner = new Scanner(new File(arqCursos))) {
            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String nome = campos[0];
                int id = Integer.parseInt(campos[1]);
                int creditos = Integer.parseInt(campos[2]);
 
                Curso curso = new Curso(nome, id, creditos);
                this.cursos.put(nome, curso);

            }
        }
     }

     public void lerDisciplinas() {
         try (Scanner scanner = new Scanner(new File(arqDisciplina))) {
             String linha;
             while (scanner.hasNextLine()) {
                 linha = scanner.nextLine();
                 String[] campos = linha.split(";");
                 String nome = campos[0];
                 int id = Integer.parseInt(campos[1]);
                 String curso = campos[2];
                 boolean inscricoesAbertas = Boolean.valueOf(campos[3]);
                 boolean obrigatoria = Boolean.valueOf(campos[4]);


                 Curso cursoSelecionado = this.cursos.get(curso);
                 cursoSelecionado.adicionarDisciplinaArquivo(nome, id, inscricoesAbertas, obrigatoria);
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }

    public void lerAlunos() {
        try (Scanner scanner = new Scanner(new File(arqAlunos))) {
            String linha;
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine();
                String[] campos = linha.split(";");
                String nome = campos[0];
                String usuario = campos[1];
                String senha = campos[2];
                int matricula = Integer.parseInt(campos[3]);

                Aluno novoAluno = new Aluno(nome, usuario, senha, matricula);
                this.usuarios.put(usuario, novoAluno);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Salva as informações dos usuários em arquivos CSV.
     * 
     * @throws IOException Exceção lançada em caso de erro ao salvar os arquivos.
     */

    /*
    public void salvar() throws IOException {
        Map<String, List<Usuario>> mapaDeUsuarios = new HashMap<>();

        usuarios.forEach((usuario, user) -> {
            String nomeDaClasse = user.getClass().getSimpleName();

            if (mapaDeUsuarios.containsKey(nomeDaClasse)) {
                mapaDeUsuarios.get(nomeDaClasse).add(user);
            } else {
                List<Usuario> listaDeUsuarios = new ArrayList<>();
                listaDeUsuarios.add(user);
                mapaDeUsuarios.put(nomeDaClasse, listaDeUsuarios);
            }
        });

        mapaDeUsuarios.keySet().forEach(classe -> {
            Path nomeDoArquivo = Paths.get("implementacao/" + classe + "s.csv");
            List<Usuario> usuariosDaClasse = mapaDeUsuarios.get(classe);

            try {
                if (!Files.exists(nomeDoArquivo)) {
                    Files.createFile(nomeDoArquivo);
                }

                Files.write(nomeDoArquivo, usuariosDaClasse.stream()
                                            .map(Object::toString)
                                            .collect(Collectors.toList()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    */

}
