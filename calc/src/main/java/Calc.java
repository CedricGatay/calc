import java.util.ArrayList;
import java.util.List;

/**
 * @author cgatay
 */
public class Calc {
    List<Operations.Operation> operations;
    List<String> displays;
    Double computedValue;
    public Calc(final List<String> ops) {
        this.operations = new ArrayList<>();
        this.displays = new ArrayList<>();
        computedValue = 0d;
        
        for (String op : ops) {
            operations.add(Operations.parse(op));
        }
    }

    public List<String> getDisplays(){
        for (Operations.Operation operation : operations) {
            computedValue = operation.apply(computedValue);
            //don't like this instanceof !
            if (operation instanceof Operations.Display){
                displays.add(String.valueOf(computedValue));
            }
        }
        //walk ops and populate displays
        return displays;
    }
}
