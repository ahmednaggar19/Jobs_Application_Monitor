package interpreter;

import controller.ApplicationsHub;
import controller.utils.FilterCriteria;
import model.Application;

import java.util.ArrayList;

public class ViewApplicationExpression implements Expression {

    private static String CLASS_ID = "view_applications";

    static {
        ExpressionFactory.
                getInstance().registerExpression(CLASS_ID, ViewApplicationExpression.class);
    }

    @Override
    public ArrayList<Application> executeExpression(String[] params) {
        if (params.length  < 1 || (params.length >= 1 && params[0].equals(CLASS_ID)) || (params.length % 2 == 0)) {
            return  null;
        }
        FilterCriteria criteria = new FilterCriteria();
        for (int i = 1; i < params.length; i += 2) {
            criteria.addCriteria(params[i], params[i + 1]);
        }
        ApplicationsHub applicationsHub = new ApplicationsHub();

        return applicationsHub.getApplications(criteria);
    }
}
