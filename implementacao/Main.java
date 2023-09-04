import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

import business.*;
import exceptions.ExcecaoUsuarioNaoEncontrado;

public class Main {


    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws InvalidParameterException, IOException, ExcecaoUsuarioNaoEncontrado {

        try{
            Sistema s = new Sistema();
            menu(s);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void menu(Sistema sistema) throws Exception{
        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastro");
            opcao = in.nextInt();
            in.nextLine();
            switch (opcao) {
                case 1:
                    logar(sistema);
                    break;
                case 2:
                    cadastro(sistema);
                    break;
                case 0:
                    break;
            }
        } while (opcao != 0);
    }

    public static void cadastro(Sistema s){
        try {
            System.out.println("Você escolheu a opção de cadastro!");

            System.out.println("Insira seu nome:");
            String nome = in.nextLine();

            System.out.println("Insira o nome de usuario:");
            String usuario = in.nextLine();

            System.out.println("Insira a senha:");
            String senha = in.nextLine();

            System.out.println("Selecione o tipo de usuário");
            System.out.println("1 - Aluno");
            System.out.println("2 - Professor");
            int tipoUsuario = Integer.parseInt(in.nextLine());

            s.cadastrar(usuario,senha,nome,tipoUsuario);
            s.logar(usuario, senha);

            if (tipoUsuario == 1){
                menuAluno(s);
            } else if (tipoUsuario == 2) {
                menuProfessor(s);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void logar(Sistema sistema){
        try{
            System.out.println("Você escolheu a opção de login!");

            System.out.println("Digite seu nome de usuario:");
            String usuario = in.nextLine();

            System.out.println("Digite sua senha:");
            String senha = in.nextLine();
            try{
                sistema.logar(usuario, senha);
                System.out.println("Logado!");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if(sistema.getUsuarioAtual() instanceof Aluno){
                menuAluno(sistema);
            } else if (sistema.getUsuarioAtual() instanceof Professor) {
                menuProfessor(sistema);
            }

        }catch (Exception e){
            e.getMessage();
        }
    }

    public static void menuAluno(Sistema sistema) throws Exception {
        System.out.println("Bem-vindo aluno "+ sistema.getUsuarioAtual().getNome() + "!");
        System.out.println("Selecione a opção que você deseja utilizar:");
        System.out.println("1 - Matricular em uma disciplina");
        System.out.println("2 - Trancar a matricula em uma disciplina");
        System.out.println("3 - Listar disciplinas.");
        System.out.println("4 - Encerrar sessão.");
        int opcao = in.nextInt();

        switch (opcao){
            case 1:
                matricularAluno(sistema);
                break;
            case 2:
                trancarMatriculaAluno(sistema);
                break;
            case 3:
                listarDisciplinasDoAluno(sistema);
                break;
            case 4:
                sistema.setUsuarioAtual(null);
                menu(sistema);
                break;
        }
    }

    public static void matricularAluno(Sistema sistema){
        try{
            System.out.println("Você selecionou a opção de se matricular em uma disciplina.");
            System.out.println("Veja as disciplinas disponíveis no sistema:");
            for(Disciplina d : sistema.listarDisciplinas()){
                System.out.println(d.getId() + " - " + d.getNome());
            }
            System.out.println("Digite o nome da disciplina que você deseja se matricular:");
            int id = in.nextInt();
            sistema.matricular(id);
            menuAluno(sistema);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void trancarMatriculaAluno(Sistema s){
        try{
            System.out.println("Digite o identificador da disciplina que você deseja remover:");
            int id = in.nextInt();
            s.trancarMatriculaAluno(id);
            menuAluno(s);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void listarDisciplinasDoAluno(Sistema sistema){
        try{
            System.out.println("Essas são suas disciplinas: ");
            for(Disciplina d: sistema.listarDisciplinasAluno()){
                System.out.println(d.getId() + " - " + d.getNome());
            }
            menuAluno(sistema);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menuProfessor(Sistema sistema){
        try{
            System.out.println("Bem-vindo Professor "+ sistema.getUsuarioAtual().getNome());
            System.out.println("Selecione a opção que você deseja utilizar:");
            System.out.println("1 - Lecionar em uma disciplina");
            System.out.println("2 - Listar alunos das disciplinas que você leciona");
            System.out.println("3 - Encerrar sessão.");
            int opcao = in.nextInt();
            switch (opcao){
                case 1:
                    lecionarAula(sistema);
                    break;
                case 2:
                    listarAlunosDisciplinasProfessor(sistema);
                    break;
                case 3:
                    sistema.setUsuarioAtual(null);
                    menu(sistema);
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void lecionarAula(Sistema sistema){
        try{
            System.out.println("Você selecionou a opção de lecionar uma disciplina.");
            System.out.println("Veja as disciplinas disponíveis no sistema:");
            for(Disciplina d : sistema.listarDisciplinas()){
                System.out.println(d.getId() + " - " + d.getNome());
            }
            System.out.println("Digite o nome da disciplina que você deseja se matricular:");
            int id = in.nextInt();
            sistema.lecionarProfessor(id);
            menuProfessor(sistema);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void listarAlunosDisciplinasProfessor(Sistema sistema){
        try{
            System.out.println("Você selecionou a opção de listar os alunos das disciplinas que você ensina!");
            System.out.println("Essas são as disciplinas que você leciona: ");
            sistema.listarDisciplinasProfessor();
            System.out.println("Digite o identicador da disciplina que você quer ver os alunos: ");
            int id = in.nextInt();
            if(sistema.listarAlunos(id).size() == 0){
                System.out.println("Não possuem alunos nessa disciplina");
            }
            for(Aluno a : sistema.listarAlunos(id)){
                System.out.println(a.getNome());
            }
            menuProfessor(sistema);
        }catch (Exception e){
            e.getMessage();
        }
    }
}










                                                                                 
