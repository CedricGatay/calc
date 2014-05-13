import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cgatay
 */
public class Operations {

    public static Operation parse(String input) {
        for (Op op : Op.values()) {
            if (op.matches(input)){
                return op.create(input);
            }
        }
        return new Noop();
    }

    public static class Noop implements Operation<Void> {
        @Override
        public Double apply(final Double previousValue) {
            return previousValue;
        }

        @Override
        public Void with(final Double value) {
            return null;
        }
    }

    public static class Display implements Operation<Void> {

        @Override
        public Double apply(final Double previousValue) {
            return previousValue;
        }

        @Override
        public Void with(final Double value) {
            return null;
        }
    }


    public static class Add implements Operation<Add> {
        private Double value;

        Add() {
        }


        @Override
        public Double apply(Double previousValue) {
            return previousValue + value;
        }

        @Override
        public Add with(final Double value) {
            this.value = value;
            return this;
        }
    }

    public static class Substract implements Operation<Substract> {
        private Double value;

        Substract() {
        }


        @Override
        public Double apply(Double previousValue) {
            return previousValue - value;
        }

        @Override
        public Substract with(final Double value) {
            this.value = value;
            return this;
        }
    }

    public static class Multiply implements Operation<Multiply> {
        private  Double value;

        Multiply() {
        }


        @Override
        public Double apply(Double previousValue) {
            return previousValue * value;
        }

        @Override
        public Multiply with(final Double value) {
            this.value = value;
            return this;
        }
    }

    public static class Divide implements Operation<Divide> {
        private Double value;

        Divide() {
        }


        @Override
        public Double apply(Double previousValue) {
            if (value == 0d) {
                throw new CalcException();
            }
            return previousValue / value;
        }

        @Override
        public Divide with(final Double value) {
            this.value = value;
            return this;
        }
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
            if (this == DISPLAY){
                return new Display();
            }
            final Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                Double value = Double.valueOf(matcher.group(1));
                try {
                    return (Operation) displayClass.newInstance().with(value);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return new Noop();
        }
    }

    /**
     * @author cgatay
     */
    public static interface Operation<T> {
        Double apply(Double previousValue);
        T with(Double value);
    }
}
