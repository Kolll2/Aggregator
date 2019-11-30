package aggr.model;

import aggr.vo.Vacancy;

import java.util.List;

public interface Strategy {
    List<Vacancy> getVacancies(String searchString);

}
