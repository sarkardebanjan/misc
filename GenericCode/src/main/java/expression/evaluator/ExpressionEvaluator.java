package expression.evaluator;

import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

public class ExpressionEvaluator {

    private static Set<String> PRIMITIVE_NUMERIC_TYPES;
    private static Map<String, String> NUMERIC_OPERATORS_AND_RETURN_TYPES;
    private static Map<String, String> STRING_OPERATORS_AND_RETURN_TYPES;
    private static Map<String, String> DATE_OPERATORS_AND_RETURN_TYPES;

    static {
        NUMERIC_OPERATORS_AND_RETURN_TYPES = new HashMap<>();
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put("=", "java.lang.Boolean");
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put("!=", "java.lang.Boolean");
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put("<", "java.lang.Boolean");
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put(">", "java.lang.Boolean");
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put("<=", "java.lang.Boolean");
        NUMERIC_OPERATORS_AND_RETURN_TYPES.put(">=", "java.lang.Boolean");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("+", "java.lang.Integer");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("-", "java.lang.Integer");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("*", "java.lang.Integer");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("/", "java.lang.Integer");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("%", "java.lang.Integer");
        //NUMERIC_OPERATORS_AND_RETURN_TYPES.put("^", "java.lang.Integer");
        NUMERIC_OPERATORS_AND_RETURN_TYPES = Collections.unmodifiableMap(NUMERIC_OPERATORS_AND_RETURN_TYPES);

        STRING_OPERATORS_AND_RETURN_TYPES = new HashMap<>();
        STRING_OPERATORS_AND_RETURN_TYPES.put("=", "java.lang.Boolean");
        STRING_OPERATORS_AND_RETURN_TYPES.put("!=", "java.lang.Boolean");
        STRING_OPERATORS_AND_RETURN_TYPES.put("contains", "java.lang.Boolean");
        STRING_OPERATORS_AND_RETURN_TYPES.put("startsWith", "java.lang.Boolean");
        STRING_OPERATORS_AND_RETURN_TYPES.put("endsWith", "java.lang.Boolean");
        STRING_OPERATORS_AND_RETURN_TYPES = Collections.unmodifiableMap(STRING_OPERATORS_AND_RETURN_TYPES);

        DATE_OPERATORS_AND_RETURN_TYPES = new HashMap<>();
        DATE_OPERATORS_AND_RETURN_TYPES.put("=", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES.put("!=", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES.put("<", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES.put(">", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES.put("<=", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES.put(">=", "java.lang.Boolean");
        DATE_OPERATORS_AND_RETURN_TYPES = Collections.unmodifiableMap(DATE_OPERATORS_AND_RETURN_TYPES);

        PRIMITIVE_NUMERIC_TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("java.lang.Integer", "java.lang.Long", "java.lang.Double", "java.lang.Float")));
    }

    public Boolean evaluate(String operand1, String operand2, String operator, Class operandClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        boolean invertResult = operator.startsWith("!") ? true : false;
        String finalOperator = operator.startsWith("!") ? operator.replaceFirst("!", "") : operator;

        Boolean result = null;

        if (PRIMITIVE_NUMERIC_TYPES.contains(operandClass.getName())) {
            result = evaluateArithmeticExpressions(operand1, operand2, finalOperator);
        } else if ("java.math.BigDecimal".equals(operandClass.getName())) {
            result = evaluateBigDecimalArithmeticExpressions(operand1, operand2, finalOperator);
        } else {
            if (finalOperator.equals("=")) {
                Method operatorMethod = operandClass.getMethod("equals", Object.class);
                result = (Boolean) operatorMethod.invoke(operand1, operand2);
            } else {
                List<String> operatorMethodNames = findMethodForOperator(finalOperator, operandClass);

                if (CollectionUtils.isEmpty(operatorMethodNames)) {
                    throw new UnsupportedOperationException("The equivalent method could not be determined for operand1: " + operand1 + ", operand2: " + operand2 + ", operandClass: " + operandClass.getName() + " and operator: " + operator);
                }

                for (String operatorMethodName : operatorMethodNames) {
                    Method operatorMethod = operandClass.getMethod(operatorMethodName);
                    result = (Boolean) operatorMethod.invoke(operand1, operand2);
                }
            }
        }

        if (null == result)
            throw new UnsupportedOperationException("The result could not be determined for operand1: " + operand1 + ", operand2: " + operand2 + ", operandClass: " + operandClass.getName() + " and operator: " + operator);

        return invertResult ? !result : result;
    }

    private Boolean evaluateBigDecimalArithmeticExpressions(String operand1, String operand2, String operator) {
        BigDecimal bigDecimalOperand1 = new BigDecimal(operand1);
        BigDecimal bigDecimalOperand2 = new BigDecimal(operand2);

        switch (operator) {
            case "=":
                return bigDecimalOperand1.compareTo(bigDecimalOperand2) == 0;
            case "<":
                return bigDecimalOperand1.compareTo(bigDecimalOperand2) < 0;
            case ">":
                return bigDecimalOperand1.compareTo(bigDecimalOperand2) > 0;
            case "<=":
                return bigDecimalOperand1.compareTo(bigDecimalOperand2) == 0 || bigDecimalOperand1.compareTo(bigDecimalOperand2) < 0;
            case ">=":
                return bigDecimalOperand1.compareTo(bigDecimalOperand2) == 0 || bigDecimalOperand1.compareTo(bigDecimalOperand2) > 0;
            default:
                return null;
        }

    }

    private Boolean evaluateArithmeticExpressions(String operand1, String operand2, String operator) {
        Double numericOperand1 = Double.valueOf(operand1);
        Double numericOperand2 = Double.valueOf(operand2);

        switch (operator) {
            case "=":
                return numericOperand1 == numericOperand2;
            case "<":
                return numericOperand1 < numericOperand2;
            case ">":
                return numericOperand1 > numericOperand2;
            case "<=":
                return numericOperand1 <= numericOperand2;
            case ">=":
                return numericOperand1 >= numericOperand2;
            default:
                return null;
        }
    }

    private List<String> findMethodForOperator(String operator, Class operandClass) {
        switch (operandClass.getName()) {
            case "java.lang.String":
                switch (operator) {
                    case "contains":
                        return Arrays.asList("contains");
                    case "startsWith":
                        return Arrays.asList("startsWith");
                    case "endsWith":
                        return Arrays.asList("endsWith");
                    default:
                        return null;
                }

            case "java.util.Date":
                switch (operator) {
                    case "<":
                        return Arrays.asList("before");
                    case ">":
                        return Arrays.asList("after");
                    case "<=":
                        return Arrays.asList("equals", "before");
                    case ">=":
                        return Arrays.asList("equals", "after");
                    default:
                        return null;
                }
            default:
                return null;
        }
    }
}
