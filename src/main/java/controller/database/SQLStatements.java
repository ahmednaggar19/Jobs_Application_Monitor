package controller.database;

import controller.utils.FilterCriteria;
import controller.utils.Util;
import model.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLStatements {

    static {
        try {
            Class.forName("controller.database.DatabaseConnector");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String queryApplicationStatement = "SELECT A.firm_name, R.name, A.location, A.status_change_date, T.name, S.name " +
            "FROM Application AS A JOIN Roles AS R ON A.role_id=R.id JOIN Status AS S ON A.status_id=S.id JOIN Terms AS T ON A.term_id=T.id ";

    private static String queryRoleStatement = "SELECT id from Roles where name = ";

    private static String queryTermStatement = "SELECT id from Terms where name = ";

    private static String insertStatement = "INSERT INTO Application ";

    public static ResultSet queryApplications(FilterCriteria criteria) {
        int criteriaSize = criteria.getCriterias().size();
        String query = queryApplicationStatement;
        if (criteriaSize > 0) {
            StringBuilder filter  = new StringBuilder("WHERE ");
            int index = 0;
            for (String criteriaName : criteria.getCriterias()) {
                filter.append(criteriaName + "=" + "'" + criteria.getCriteriaValue(criteriaName) + "'");
                index++;
                if (index < criteriaSize) {
                    filter.append(" AND ");
                }
            }
            query += filter.toString();
        }
        return DatabaseConnector.executeQuery(query + ";");
    }

    private static int getRoleId(String roleName) {
        ResultSet resultSet = DatabaseConnector.executeQuery(queryRoleStatement + "'" +  roleName + "';");
        try {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getTermId(String termName) {
        ResultSet resultSet = DatabaseConnector.executeQuery(queryTermStatement + "'" +  termName + "';");
        try {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean insertApplication(Application application) {
        StringBuilder insert = new StringBuilder(insertStatement);
        ArrayList<String> attributes = new ArrayList<>();
        ArrayList<String> attributesValues = new ArrayList<>();
        if (application.getFirmName() == null) {
            return false;
        } else {
            addAttributePair("firm_name", application.getFirmName(), Util.DataType.TEXT ,attributes,
                    attributesValues);
        }
        if (application.getLocation() == null) {
            return false;
        } else {
            addAttributePair("location", application.getLocation(), Util.DataType.TEXT ,attributes,
                    attributesValues);
        }
        if (application.getRole() != null) {
            addAttributePair("role_id", String.valueOf(getRoleId(application.getRole().toString())), Util.DataType.INT ,attributes,
                    attributesValues);
        }
        if (application.getTerm() != null) {
            addAttributePair("term_id", String.valueOf(getTermId(application.getTerm().toString())), Util.DataType.INT ,attributes,
                    attributesValues);
        }
        buildInsertStatement(insert, attributes, attributesValues);
        DatabaseConnector.executeModify(insert.toString());
        return true;
    }

    private static void addAttributePair(String attribute, String attributeValue, Util.DataType dataType,
                                  ArrayList<String> attributes, ArrayList<String> attributesValues) {
        attributes.add(attribute);
        if (dataType == Util.DataType.INT) {
            attributesValues.add(attributeValue);
        } else if (dataType == Util.DataType.TEXT) {
            attributesValues.add("'" + attributeValue + "'");
        }
        return;
    }

    private static void buildInsertStatement (StringBuilder insert, ArrayList<String> attributes,
                                       ArrayList<String> attributesValues) {
        insert.append("(");
        for (int i = 0; i < attributes.size(); i++) {
            insert.append(attributes.get(i));
            if (i < attributes.size() - 1) {
                insert.append(",");
            }
        }
        insert.append(")");

        insert.append("values(");
        for (int i = 0; i < attributesValues.size(); i++) {
            insert.append(attributesValues.get(i));
            if (i < attributes.size() - 1) {
                insert.append(",");
            }
        }
        insert.append(")");
        return;
    }

    public static void main (String ... args) throws SQLException {
        try {
            Class.forName("controller.database.SQLStatements");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(SQLStatements.getRoleId("SOFTWARE_ENGINEER_INTERN"));
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.addCriteria("firm_name", "FB");
        ResultSet rs = SQLStatements.queryApplications(filterCriteria);
        while (rs.next()) {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getTimestamp(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
        }
    }
}
