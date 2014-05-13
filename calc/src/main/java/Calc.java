import exceptions.ComputeException;
import operations.Operation;
import operations.OperationsParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgatay
 */
public class Calc {
    List<Operation> operations;
    List<String> displays;
    Double computedValue;
    public Calc(final List<String> ops) {
        this.operations = new ArrayList<>();
        this.displays = new ArrayList<>();
        computedValue = 0d;
        
        for (String op : ops) {
            final Operation operation = OperationsParser.parse(op);
            if (operation != null) {
                operations.add(operation);
            }
        }
    }

    public List<String> getDisplays(){
        for (Operation operation : operations) {
            try {
                computedValue = operation.apply(computedValue);
            } catch (ComputeException e) {
                displays.add(String.valueOf(e.getValue()));
            }
        }
        //walk ops and populate displays
        return displays;
    }
}
