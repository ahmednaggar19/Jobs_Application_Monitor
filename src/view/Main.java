package view;

import interpreter.AddApplicationExpression;
import interpreter.ViewApplicationExpression;
import model.Application;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        AddApplicationExpression addApplicationExpression = new AddApplicationExpression();
        ViewApplicationExpression viewApplicationExpression = new ViewApplicationExpression();
        ArrayList<Application> result = addApplicationExpression.executeExpression(args);
        if (result != null) {

        } else {
            result = viewApplicationExpression.executeExpression(args);
            if (result == null) return;
        }
        for (Application application : result) {
            System.out.print(application.getFirmName() + " ");
            System.out.print(application.getLocation());
            System.out.print(application.getRole());
            System.out.println();
        }
    }
}
