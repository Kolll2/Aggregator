package model;

import vo.Vacancy;

import java.util.Collections;
import java.util.List;

public class StrategyImplementation implements Strategy {
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return Collections.emptyList();
    }
}
