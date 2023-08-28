package exceptions;

public class ExcecaoCursoNaoExistente extends Exception{
    /**
     * Exceção responsável por indicar a ausência do curso com o nome passado por parâmetro.
     */

    // Construtor

    public ExcecaoCursoNaoExistente(){
        super("Não existe nenhum curso com esse nome cadastrado no sistema.");
    }
}
