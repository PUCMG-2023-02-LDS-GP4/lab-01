package exceptions;

public class ExcecaoDisciplinaExistente extends Exception{
    // Construtor

    public ExcecaoDisciplinaExistente(){
        super("Essa disciplina já existe.");
    }
}
