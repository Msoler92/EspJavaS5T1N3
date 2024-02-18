package cat.itacademy.barcelonactiva.solereina.manel.s05.t01.n03.model.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }
}
