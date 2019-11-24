import model.Provider;
import model.Strategy;

public class Aggregator {

    public static void main(String[] args) {

        Provider provider = new Provider(new Strategy() {});
        Controller controller = new Controller(provider);

        System.out.println(controller.toString());
    }
}
