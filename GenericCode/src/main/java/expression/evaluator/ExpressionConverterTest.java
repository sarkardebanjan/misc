package expression.evaluator;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;

public class ExpressionConverterTest {

    public static void main(String[] args) {

        (new ExpressionConverterTest()).doWork();
    }

    private void doWork() {
        //String expressionUI = "(CODE=\"SIMP\" AND (TYPE=\"A+\" OR TYPE=\"O+\") AND QUANTITY > 100)";
        String expressionUI = "\"SIMP\".equals(\"SIMP\")";

        Product product = new Product(10L, "Electronics", 1, new BigDecimal("999.99"));

        ExpressionConverter converter = new ExpressionConverter();
        System.out.println("SpEL Expression: " + converter.convert(expressionUI));

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expressionUI);
        StandardEvaluationContext context = new StandardEvaluationContext(product);
        Boolean result = (Boolean) expression.getValue(context);
        System.out.println("Result: " + result);
    }
}
