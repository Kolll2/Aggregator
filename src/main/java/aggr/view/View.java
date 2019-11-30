package aggr.view;

import aggr.Controller;
import aggr.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
