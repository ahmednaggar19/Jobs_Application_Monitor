package interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ExpressionFactory {

    private static ExpressionFactory expressionFactory = new ExpressionFactory();

    private HashMap<String, Class<? extends Expression>> registeredExpressions = null;


    /**
     * Instantiates a new initial statement factory.
     */
    private ExpressionFactory() {
        registeredExpressions = new HashMap<>();

    }


    /**
     * Gets the single instance of ExpressionFactory.
     * @return single instance of ExpressionFactory
     */
    public static ExpressionFactory getInstance() {
        return expressionFactory;
    }

    /**
     * Register statement.
     * @param expression the expression id
     * @param expressionClass the expression class
     */
    public void registerExpression(String expression,
                                  Class<? extends Expression>
                                          expressionClass) {
        registeredExpressions.put(expression, expressionClass);
    }



    /**
     * Creates a new InitialStatement object.
     * @param expression the statement id
     * @return the initial statement
     */
    public Expression createExpression(String expression) {
        Class<? extends Expression> statementClass =
                registeredExpressions.get(expression);
        try {
            Constructor<? extends Expression> expressionConstructor =
                    statementClass.getConstructor();
            Expression expressionObject  = expressionConstructor .newInstance();
            return expressionObject;
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException e) {
            return null;
        }
    }
}
