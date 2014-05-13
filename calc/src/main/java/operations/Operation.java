package operations;

/**
 * @author cgatay
 */
public abstract class Operation<T extends Operation> {
    protected Double value;

    abstract public Double apply(Double previousValue);

    Operation<T> with(Double value) {
        this.value = value;
        return this;
    }
}
