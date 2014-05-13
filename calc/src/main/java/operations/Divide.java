package operations;

import exceptions.CalcException;

/**
* @author cgatay
*/
public class Divide extends Operation<Divide> {

    public Divide() {
    }


    @Override
    public Double apply(Double previousValue) {
        if (value == 0d) {
            throw new CalcException();
        }
        return previousValue / value;
    }

}
