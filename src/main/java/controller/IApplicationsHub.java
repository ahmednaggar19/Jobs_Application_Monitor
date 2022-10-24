package controller;

import controller.utils.FilterCriteria;
import model.Application;

import java.util.ArrayList;

public interface IApplicationsHub {

    public ArrayList<Application> getApplications(FilterCriteria criteria);

    public void removeApplications(FilterCriteria criteria);

    public void addApplication(Application application);
}
