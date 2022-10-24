package controller.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FilterCriteria {

    private Map<String, String> criteriaMap;

    public FilterCriteria () {
        this.criteriaMap = new HashMap<String, String>();
    }

    public void addCriteria(String cirteria, String criteriaValue) {
        this.criteriaMap.put(cirteria, criteriaValue);
    }

    public String getCriteriaValue(String criteria) {
        return this.criteriaMap.get(criteria);
    }

    public Set<String> getCriterias() {
        return this.criteriaMap.keySet();
    }
}
