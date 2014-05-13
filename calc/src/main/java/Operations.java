import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cgatay
 */
public class Operations {
    
    public static Operation parse(String input){
        if (Add.matches(input)){
            return new Add(input);
        }
        if (Substract.matches(input)){
            return new Substract(input);
        }
        if (Multiply.matches(input)){
            return new Multiply(input);
        }
        if (Divide.matches(input)){
            return new Divide(input);
        }
        if (Display.matches(input)){
            return new Display();
        }
        return new Noop(); 
    }

    public static class Noop implements Operation{
        @Override
        public Double apply(final Double previousValue) {
            return previousValue;
        }
    }

    public static class Display implements Operation{
        static Pattern regex = Pattern.compile("DISPLAY");

        @Override
        public Double apply(final Double previousValue) {
            return previousValue;
        }

        public static boolean matches(String input){
            return regex.matcher(input).matches();
        }
    }




    public static class Add implements Operation{
        private final Double value;
        static Pattern regex = Pattern.compile("ADD ([.0-9]+)");
        
        Add(String input){
            final Matcher matcher = regex.matcher(input);
            if (matcher.matches()){
                this.value = Double.valueOf(matcher.group(1));
            }else{
                throw new RuntimeException("Could not parse input");
            }
        }
        
        public static boolean matches(String input){
            return regex.matcher(input).matches();
        }
        
        
        @Override
        public Double apply(Double previousValue){
            return previousValue + value;   
        }
    }
    public static class Substract implements Operation{
        private final Double value;
        static Pattern regex = Pattern.compile("SUBSTRACT ([.0-9]+)");
        
        Substract(String input){
            final Matcher matcher = regex.matcher(input);
            if (matcher.matches()){
                this.value = Double.valueOf(matcher.group(1));
            }else{
                throw new RuntimeException("Could not parse input");
            }
        }
        
        public static boolean matches(String input){
            return regex.matcher(input).matches();
        }
        
        
        @Override
        public Double apply(Double previousValue){
            return previousValue - value;   
        }
    }
    public static class Multiply implements Operation{
        private final Double value;
        static Pattern regex = Pattern.compile("MULTIPLY BY ([.0-9]+)");

        Multiply(String input){
            final Matcher matcher = regex.matcher(input);
            if (matcher.matches()){
                this.value = Double.valueOf(matcher.group(1));
            }else{
                throw new RuntimeException("Could not parse input");
            }
        }
        
        public static boolean matches(String input){
            return regex.matcher(input).matches();
        }
        
        
        @Override
        public Double apply(Double previousValue){
            return previousValue * value;   
        }
    }

    public static class Divide implements Operation{
        private final Double value;
        static Pattern regex = Pattern.compile("DIVIDE BY ([0-9.]+)");

        Divide(String input){
            final Matcher matcher = regex.matcher(input);
            if (matcher.matches()) {
                this.value = Double.valueOf(matcher.group(1));
            }else{
                throw new RuntimeException("Could not parse input");
            }
        }

        public static boolean matches(String input){
            return regex.matcher(input).matches();
        }


        @Override
        public Double apply(Double previousValue){
            if (value == 0d){
                throw new CalcException();
            }
            return previousValue / value;
        }
    }




    /**
     * @author cgatay
     */
    public static interface Operation {
        Double apply(Double previousValue);
    }
}
