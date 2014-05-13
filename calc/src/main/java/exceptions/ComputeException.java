package exceptions;

/**
 * @author cgatay
 */
public class ComputeException extends RuntimeException {

    private final Double value;

    public ComputeException(final Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}

