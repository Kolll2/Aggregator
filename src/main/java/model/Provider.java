package model;

import vo.Vacancy;

import java.util.List;

public class Provider {
    Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    List<Vacancy> getJavaVacancies(String searchString){
        // call strategy method
        return null;
    }
}
