package operations;

/**
* @author cgatay
*/
public class Multiply extends Operation<Multiply> {
    public Multiply() {
    }


    @Override
    public Double apply(Double previousValue) {
        return previousValue * value;
    }

}
