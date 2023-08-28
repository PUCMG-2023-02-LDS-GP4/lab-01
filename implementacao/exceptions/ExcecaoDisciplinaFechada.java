package exceptions;

public class ExcecaoDisciplinaFechada extends Exception{

    // Construtor

    public ExcecaoDisciplinaFechada(){
        super("A disciplina está com as inscrições fechadas.");
    }
}
