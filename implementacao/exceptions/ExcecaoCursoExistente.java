package exceptions;

public class ExcecaoCursoExistente extends Exception{

    // Construtor

    public ExcecaoCursoExistente(){
        super("Já existe um curso com esse nome.");
    }
}
