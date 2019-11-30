package aggr;
import aggr.model.HHStrategy;
import aggr.model.Model;
import aggr.model.Provider;
import aggr.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {

        HHStrategy hhStrategy = new HHStrategy();
        HtmlView htmlView = new HtmlView();
        Provider provider = new Provider(hhStrategy);
        Model model = new Model(htmlView, provider);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();

    }
}
