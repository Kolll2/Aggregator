import model.HHStrategy;
import model.Provider;

public class Aggregator {

    public static void main(String[] args) {

        Provider provider = new Provider(new HHStrategy());
        Controller controller = new Controller(provider);
        controller.scan();
    }
}
