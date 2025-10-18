package exception;

public class PilotoIncorrectoException extends RuntimeException {
    public PilotoIncorrectoException(String elNombreEsObligatorio) {super(elNombreEsObligatorio);
    }
}
