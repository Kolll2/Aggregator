package aggr.view;

import aggr.Controller;
import aggr.vo.Vacancy;

import java.util.List;

public class HtmlView implements View {
    Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
//        for (Vacancy vac: vacancies) {
//            System.out.println(vac.toString());
//        }

        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }
}
