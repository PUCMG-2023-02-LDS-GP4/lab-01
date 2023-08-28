package exceptions;

public class ExcecaoDisciplinaNaoExistente extends Exception{

    /**
      * Exceção designada para sinalizar a tentativa de exclusão de uma disciplina inexistente na grade curricular do curso.
     **/

    // Construtor
    public ExcecaoDisciplinaNaoExistente(){
        super("A disciplina com o nome fornecido não existe.");
    }
}
