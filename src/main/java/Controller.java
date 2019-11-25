import model.Provider;
import vo.Vacancy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {


    private Provider[] providers;

    public Controller(Provider ... providers) {

        if (providers.length == 0) throw new IllegalArgumentException();
        this.providers = providers;

    }

    @Override
    public String toString() {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }

    public void scan() {
        List<Vacancy> vacancies = Arrays.stream(providers)
                .map(provider -> provider.getJavaVacancies(""))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(vacancies.size());
    }
}
