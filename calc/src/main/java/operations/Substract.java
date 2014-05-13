package operations;

/**
* @author cgatay
*/
public class Substract extends Operation<Substract> {
    public Substract() {
    }


    @Override
    public Double apply(Double previousValue) {
        return previousValue - value;
    }
}
