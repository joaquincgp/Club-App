package club;

public class SocioNoExisteException extends Exception {

    public SocioNoExisteException(String mensaje) {
        super(mensaje);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
