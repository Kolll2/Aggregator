package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT_OLD = "http://hh.ua/search/vacancy?text=java+%s&page=%s";
    private static final String URL_FORMAT_NEW = "https://hh.ru/search/vacancy?area=%s&clusters=true&enable_snippets=true&text=java&page=%s";

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
    public List<Vacancy> getVacancies(String searchString) {
        try {
            Document document = Jsoup.connect(URL_FORMAT_OLD)
                    .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/77.0.3865.120 YaBrowser/19.10.3.281 Yowser/2.5 Safari/537.36")
                    .referrer("no-referrer-when-downgrade").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
