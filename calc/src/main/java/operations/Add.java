package operations;

/**
* @author cgatay
*/
public class Add extends Operation<Add> {

    public Add() {
    }


    @Override
    public Double apply(Double previousValue) {
        return previousValue + value;
    }
}
