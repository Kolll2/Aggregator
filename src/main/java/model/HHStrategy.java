package model;

public class HHStrategy {
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


}
