package operations;

import exceptions.ComputeException;

/**
* @author cgatay
*/
public class Display extends Operation<Display> {

    @Override
    public Double apply(final Double previousValue) {
        throw new ComputeException(previousValue);
    }

    @Override
    public Display with(final Double value) {
        return this;
    }
}
