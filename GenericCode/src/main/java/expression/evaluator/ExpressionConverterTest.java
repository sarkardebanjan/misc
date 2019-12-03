package expression.evaluator;

public class ExpressionConverterTest {

    public static void main(String[] args) {

        (new ExpressionConverterTest()).doWork();
    }

    private void doWork() {
        String expressionUI = "(CODE=\"SIMP\" AND (TYPE=\"A+\" OR TYPE=\"O+\") AND QUANTITY > 100)";
        ExpressionConverter converter = new ExpressionConverter();
        System.out.println(converter.convert(expressionUI));
    }
}
