package aggr.model;

import aggr.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String URL_PREFIX = "https://moikrug.ru";
    private int PAGE_VALUE;

    @Override
    public List<Vacancy> getVacancies(String searchString)  {
        List<Vacancy> result = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument(searchString, PAGE_VALUE);
            while (true) {
                //"vacancy-serp__vacancy"
                //"vacancy-serp__vacancy vacancy-serp__vacancy_premium"
                Elements elements = document.select("[class=job]");
                elements.addAll(document.select("[class = job marked]"));
//                System.out.println(elements.size());
                if (elements.size() == 0) {
                    PAGE_VALUE = 1;
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValueContaining("class", "title").text());
                        vac.setCity(element.getElementsByAttributeValueContaining("class", "location").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                        vac.setSiteName(URL_FORMAT);
                        String urlPage = element.getElementsByAttributeValueContaining("class", "job_icon").attr("href");
                        vac.setUrl(URL_PREFIX + urlPage);
                        String salary = element.getElementsByAttributeValueContaining("class", "salary").text();
                        vac.setSalary(salary.length()==0 ? "" : salary);
//                        System.out.println(vac.toString());
                        result.add(vac);
                    }
                }
                ++PAGE_VALUE;
                document = getDocument(searchString, PAGE_VALUE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        Document document = Jsoup.connect(String.format(URL_FORMAT,URLEncoder.encode(searchString, "utf-8"), page))
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/77.0.3865.120 YaBrowser/19.10.3.281 Yowser/2.5 Safari/537.36")
                .referrer("no-referrer-when-downgrade").get();
            System.out.println(document.baseUri() + " => "+page);
//            System.out.println(document.text());

        return document;
    }
}
