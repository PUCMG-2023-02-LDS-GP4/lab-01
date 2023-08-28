package exceptions;

public class ExcecaoDisciplinaCheia extends Exception {

    // Construtor
    public ExcecaoDisciplinaCheia(){
        super("A disciplina está cheia então não foi possivel adicionar mais alunos.");
    }
}
