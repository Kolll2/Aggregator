package aggr;
import aggr.model.HHStrategy;
import aggr.model.Model;
import aggr.model.MoikrugStrategy;
import aggr.model.Provider;
import aggr.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        HHStrategy hhStrategy = new HHStrategy();
        MoikrugStrategy moikrugStrategy = new MoikrugStrategy();
        HtmlView htmlView = new HtmlView();
        Provider provider = new Provider(hhStrategy);
        Provider providerMM = new Provider(moikrugStrategy);
        Model model = new Model(htmlView,provider, providerMM);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}
