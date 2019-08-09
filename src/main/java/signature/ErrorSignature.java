package signature;

public class ErrorSignature extends RuntimeException {

    public ErrorSignature(Throwable cause) {
        super(cause);
    }
}
