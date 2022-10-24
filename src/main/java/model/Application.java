package model;

import controller.utils.Util;

import java.util.Date;

public class Application {

    private String firmName;
    private String location;
    private Util.JobRole role;
    private Util.Term term;
    private Date submissionDate;

    public Application() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Util.JobRole getRole() {
        return role;
    }

    public void setRole(Util.JobRole role) {
        this.role = role;
    }

    public Util.Term getTerm() {
        return term;
    }

    public void setTerm(Util.Term term) {
        this.term = term;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }
}
