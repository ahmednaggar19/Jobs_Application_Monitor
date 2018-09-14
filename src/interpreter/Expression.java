package interpreter;

import model.Application;

import java.util.ArrayList;

public interface Expression {

    public ArrayList<Application> executeExpression (String[] params);
}
