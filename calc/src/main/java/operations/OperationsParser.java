package operations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cgatay
 */
public class OperationsParser {

    public static Operation parse(String input) {
        for (Op op : Op.values()) {
            if (op.matches(input)) {
                return op.create(input);
            }
        }
        return null;
    }


    public enum Op {
        ADD("ADD ([.0-9]+)", Add.class),
        SUBSTRACT("SUBSTRACT ([.0-9]+)", Substract.class),
        MULTIPLY("MULTIPLY BY ([.0-9]+)", Multiply.class),
        DIVIDE("DIVIDE BY ([0-9.]+)", Divide.class),
        DISPLAY("DISPLAY", Display.class);
        private final Pattern pattern;
        private final Class<? extends Operation> displayClass;

        Op(final String pattern, final Class<? extends Operation> displayClass) {
            this.displayClass = displayClass;
            this.pattern = Pattern.compile(pattern);
        }

        public boolean matches(String input) {
            return pattern.matcher(input).matches();
        }

        public Operation create(final String input) {
            if (this == DISPLAY) {
                return new Display();
            }
            final Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                Double value = Double.valueOf(matcher.group(1));
                try {
                    return displayClass.newInstance().with(value);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
