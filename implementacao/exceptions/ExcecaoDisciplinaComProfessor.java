package exceptions;

public class ExcecaoDisciplinaComProfessor extends Exception{

    // Construtor

    public ExcecaoDisciplinaComProfessor(){
        super("Essa disciplina já está sendo lecionada por um professor.");
    }
}
