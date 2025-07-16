package exception;

/**
 * Excepción personalizada para indicar que un libro no fue encontrado.
 */
public class LibroNoEncontradoException extends Exception {
    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
