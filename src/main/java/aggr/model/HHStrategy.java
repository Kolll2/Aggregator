package aggr.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import aggr.vo.Vacancy;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%s";
    private static final String URL_FORMAT_OLD = "http://hh.ua/search/vacancy?text=java+%s&page=%s";
    private static final String URL_FORMAT_NEW = "https://hh.ru/search/vacancy?area=%s&clusters=true&enable_snippets=true&text=java&page=%s";
    private int PAGE_VALUE;

    public String getURL_FORMAT_OLD() {
        return String.format(URL_FORMAT_OLD, "Kiev", 3);
    }
    public String getURL_FORMAT_NEW() {
        /**Returns URL for parsing vovations Java
         * @param  city area code
         * @param  pageNumber page number for view
         */
        return String.format(URL_FORMAT_NEW, 115, 3);
    }


    /**
     * stub
     * @param searchString
     * @return
     */



    @Override
    public List<Vacancy> getVacancies(String searchString)  {
        List<Vacancy> result = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument(searchString, PAGE_VALUE);
            while (true) {
                //"vacancy-serp__vacancy"
                //"vacancy-serp__vacancy vacancy-serp__vacancy_premium"
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                //System.out.println(elements.size());
                if (elements.size() == 0) {
                    PAGE_VALUE = 0;
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title").text());
                        vac.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vac.setSiteName(URL_FORMAT);
                        String urlPage = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                        vac.setUrl(urlPage);
                        String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                        vac.setSalary(salary.length()==0 ? "" : salary);
                        System.out.println(vac.toString());
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
        Document document = Jsoup.connect(String.format(URL_FORMAT, URLEncoder.encode(searchString, "utf-8"), page))
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/77.0.3865.120 YaBrowser/19.10.3.281 Yowser/2.5 Safari/537.36")
                .referrer("no-referrer-when-downgrade").get();
            System.out.println(document.baseUri() + " => "+page);
//            System.out.println(document.text());

        return document;
    }


    public String getURL_FORMAT() {
        return String.format(URL_FORMAT, "Kiev", 3);
    }
}
