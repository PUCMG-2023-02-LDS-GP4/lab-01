package exceptions;

public class ExcecaoQuantidadeAlunosExcessiva extends Exception{

    /**
     * Exceção que auxilia na criação de uma nova disciplina com uma lista de alunos predefinida, garantindo que o número de alunos não exceda 60.
     */


    // Construtor

    public ExcecaoQuantidadeAlunosExcessiva(){
        super("A quantidade de alunos não pode ser maior que 60.");
    }

}
