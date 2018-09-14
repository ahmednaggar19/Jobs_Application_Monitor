package interpreter;

import controller.ApplicationsHub;
import controller.utils.Util;
import model.Application;

import java.util.ArrayList;

import static controller.utils.Util.getTerm;

public class AddApplicationExpression implements Expression {

    private static String CLASS_ID = "add_application";

    static {
        ExpressionFactory.
                getInstance().registerExpression(CLASS_ID, AddApplicationExpression.class);
    }

    @Override
    public ArrayList<Application> executeExpression(String[] params) {
        if (!params[0].equals(CLASS_ID)) {
            return null;
        }
        Application application = new Application();
        application.setFirmName(params[1]);
        application.setLocation(params[2]);
        application.setTerm(getTerm(params[3]));
        application.setRole(params[4] == "SOFTWARE_ENGINEER" ? Util.JobRole.SOFTWARE_ENGINEER : Util.JobRole.SOFTWARE_ENGINEER_INTERN);
        ApplicationsHub applicationsHub = new ApplicationsHub();
        applicationsHub.addApplication(application);
        return new ArrayList<>();
    }
}
