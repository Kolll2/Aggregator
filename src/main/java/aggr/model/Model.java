package aggr.model;

import aggr.view.View;
import aggr.vo.Vacancy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Model {
    View view;
    Provider [] providers;

    public Model(View view, Provider ... providers) {
       if (Objects.isNull(view) || Objects.isNull(providers) || providers.length == 0)
           throw new IllegalArgumentException();

       this.view = view;
       this.providers = providers;

    }

    public void selectCity(String city){
        List<Vacancy> vacancies = Arrays.stream(providers)
                .map(provider -> provider.getJavaVacancies(city))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        view.update(vacancies);
    }
}
