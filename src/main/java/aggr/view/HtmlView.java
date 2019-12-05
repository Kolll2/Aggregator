package aggr.view;

import aggr.Controller;
import aggr.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private final String filePath = "./src/main/java/" +
            this.getClass().getPackage().getName().replace('.', '/') +
            "/vacancies.html";
    Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
//        for (Vacancy vac: vacancies) {
//            System.out.println(vac.toString());
//        }
        String vacViewHTML = null;
        vacViewHTML = getUpdatedFileContent(vacancies);
        updateFile(vacViewHTML);
        //System.out.println(filePath);
        //System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    /**
     * builds a simple html form with the result of parsing
     * @param vacancies
     * @return String document
     * @return "Some exception occurred" on error
     */
    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();
            //     System.out.println(document.toString());
            Element template = document.getElementsByClass("template").first();
            Element vacancyTemplate = template.clone();
            vacancyTemplate.removeClass("template").removeAttr("style");
            document
                    .getElementsByAttributeValueEnding("class", "vacancy")
                    .remove();
            Element temp = vacancyTemplate.clone();

            for (Vacancy vac:vacancies) {
                Element link = temp.getElementsByTag("a").first();
                link.text(vac.getTitle());
                link.attr("href", vac.getUrl());
                temp.getElementsByClass("city").first()
                        .text(vac.getCity());
                temp.getElementsByClass("companyName").first()
                        .text(vac.getCompanyName());
                if (vac.getSalary() != null || vac.getSalary().length() != 0)
                    temp.getElementsByClass("salary").first()
                            .text(vac.getSalary());
                template.before(temp.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return document.toString();
    }


    /**
     * Rewrite outputFile
     * @param content
     */
    private void updateFile(String content){
        try (FileOutputStream fileOutputStream= new FileOutputStream(filePath)){
            fileOutputStream.write(content.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads template for output
     * @return Document Jsoup
     * @throws IOException
     */

    protected Document getDocument() throws IOException{
        File in = new File(filePath);
        return Jsoup.parse(in, "UTF-8");
    }
}
