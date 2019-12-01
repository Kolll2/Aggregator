package aggr.view;

import aggr.Controller;
import aggr.vo.Vacancy;

import java.util.List;

public class HtmlView implements View {

    private final String filePath = "./src/" +
            this.getClass().getPackage().getName().replace('.', '/') +
            "/vacancies.html";
    Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
//        for (Vacancy vac: vacancies) {
//            System.out.println(vac.toString());
//        }
        String vacViewHTML = getUpdatedFileContent(vacancies);
        updateFile(vacViewHTML);
        System.out.println(filePath);
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    /**
     * To do later
     * @param vacancies
     * @return
     */
    private String getUpdatedFileContent(List<Vacancy> vacancies){
        return null;
    }

    /**
     * To do later
     * @param fileName
     */
    private void updateFile(String fileName){}
}
