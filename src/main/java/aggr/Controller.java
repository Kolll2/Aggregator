package aggr;
import aggr.model.Model;

import java.util.Objects;


public class Controller {

    private Model model;

    public Controller(Model model) {
        if (Objects.isNull(model))
            throw new IllegalArgumentException();

        this.model = model;
    }

    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
