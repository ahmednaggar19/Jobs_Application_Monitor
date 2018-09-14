package controller;

import controller.database.DatabaseConnector;
import controller.database.SQLStatements;
import controller.utils.FilterCriteria;
import controller.utils.Util;
import model.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static controller.utils.Util.getStatus;
import static controller.utils.Util.getTerm;

public class ApplicationsHub implements IApplicationsHub{


    @Override
    public ArrayList<Application> getApplications(FilterCriteria criteria) {
        ArrayList<Application> applications = new ArrayList<>();
        ResultSet rs = SQLStatements.queryApplications(criteria);
        try {
            while (rs.next()) {
                Application application = new Application();
                application.setFirmName(rs.getString(1));
                application.setRole(rs.getString(2).equals("SOFTWARE_ENGINEER" ) ? Util.JobRole.SOFTWARE_ENGINEER : Util.JobRole.SOFTWARE_ENGINEER_INTERN);
                application.setLocation(rs.getString(3));
                application.setSubmissionDate(rs.getTimestamp(4));
                application.setTerm(getTerm(rs.getString(5)));
                System.out.println(getStatus(rs.getString(6)));
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
    @Override
    public void removeApplications(FilterCriteria criteria) {

    }

    @Override
    public void addApplication(Application application) {
        SQLStatements.insertApplication(application);
    }



}
