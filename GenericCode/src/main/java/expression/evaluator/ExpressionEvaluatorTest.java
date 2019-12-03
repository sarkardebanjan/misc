package expression.evaluator;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ExpressionEvaluatorTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String expressionUI = "(CODE=\"SIMP\" AND (TYPE=\"A+\" OR TYPE=\"O+\") AND QUANTITY > 100)";



        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        System.out.println(evaluator.evaluate("NORM", "SIMP", "!=", String.class));
        System.out.println(evaluator.evaluate("10", "100", ">=", Integer.class));
    }
}
