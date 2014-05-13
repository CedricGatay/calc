import exceptions.CalcException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author cgatay
 */
public class MainTest {

    @Test
    public void testBasicOperation() throws Exception{
        List<String> ops = new ArrayList<>();
        ops.add("ADD 5");
        ops.add("SUBSTRACT 2");
        ops.add("MULTIPLY BY 3");
        ops.add("DISPLAY");

        Calc calc = new Calc(ops);
        List<String> displays = calc.getDisplays();
        assertThat(displays).hasSize(1);
        assertThat(displays.get(0)).isEqualTo("9.0");
    }

    @Test
    public void test_noop() throws Exception {
        Calc calc = new Calc(new ArrayList<String>());
        
        assertThat(calc.getDisplays()).isEmpty();
    }
    
    @Test(expected = CalcException.class)
    public void test_divideByZero_throws_CalcException() throws Exception {
        List<String> ops = new ArrayList<>();
        ops.add("DIVIDE BY 0");
        ops.add("DISPLAY");
        Calc calc = new Calc(ops);
        calc.getDisplays();
    }

    @Test
    public void test_nodisplay_op_no_computations() throws Exception {
        List<String> ops = new ArrayList<>();
        ops.add("ADD 5");
        ops.add("SUBSTRACT 2");
        ops.add("MULTIPLY BY 3");

        Calc calc = new Calc(ops);
        assertThat(calc.getDisplays()).isEmpty();
    }

    @Test
    public void test_unparseable_op_ignored() throws Exception {
        List<String> ops = new ArrayList<>();
        ops.add("ADD 5");
        ops.add("SUSTRACT 2");
        ops.add("DISPLAY");
        
        Calc calc = new Calc(ops);
        final List<String> displays = calc.getDisplays();
        assertThat(displays).hasSize(1);
        assertThat(displays.get(0)).isEqualTo("5.0");
    }

    @Test
    public void test_multiple_getDisplaysCall_are_cached() throws Exception {
        List<String> ops = new ArrayList<>();
        ops.add("ADD 5");
        ops.add("DISPLAY");
        final Calc calc = new Calc(ops);
        final List<String> firstCall = calc.getDisplays();
        final List<String> secondCall = calc.getDisplays();
        assertThat(firstCall).isSameAs(secondCall);
    }

    @Test
    public void test_twoDisplays() throws Exception{
        List<String> ops = new ArrayList<>();
        ops.add("ADD 5");
        ops.add("SUBSTRACT 2");
        ops.add("MULTIPLY BY 3");
        ops.add("DISPLAY");
        ops.add("DIVIDE BY 2");
        ops.add("DISPLAY");

        Calc calc = new Calc(ops);
        List<String> displays = calc.getDisplays();
        assertThat(displays).hasSize(2);
        assertThat(displays.get(0)).isEqualTo("9.0");
        assertThat(displays.get(1)).isEqualTo("4.5");
        
    }
}
