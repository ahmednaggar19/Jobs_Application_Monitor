package controller.utils;

public class Util {

    public static enum JobRole {SOFTWARE_ENGINEER, SOFTWARE_ENGINEER_INTERN};
    public static enum Term {FALL, WINTER, SUMMER, SPRING, FULL_TIME};

    public static enum Status { PENDING, REJECTED ,ACCEPTED ,INTERVIEW_PENDING  ,ACCEPTANCE_PENDING};

    public static enum DataType {INT, TEXT};

    public static Util.Status getStatus (String statusString) {
        if (statusString.equals("PENDING")) {
            return  Util.Status.PENDING;
        } else if (statusString.equals("REJECTED")) {
            return Util.Status.REJECTED;
        } else if (statusString.equals("ACCEPTED")) {
            return Util.Status.ACCEPTED;
        } else if (statusString.equals("INTERVIEW_PENDING")) {
            return Util.Status.INTERVIEW_PENDING;
        } else {
            return Util.Status.ACCEPTANCE_PENDING;
        }
    }

    public static Util.Term getTerm (String termString) {
        if (termString.equals("SUMMER")) {
            return  Util.Term.SUMMER;
        } else if (termString.equals("WINTER")) {
            return Util.Term.WINTER;
        } else if (termString.equals("SPRING")) {
            return Util.Term.SPRING;
        } else if (termString.equals("FALL")) {
            return Util.Term.FALL;
        } else {
            return Util.Term.FULL_TIME;
        }
    }

}
