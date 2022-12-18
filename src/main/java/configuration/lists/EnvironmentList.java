package configuration.lists;


import configuration.model.EnvironmentModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EnvironmentList {
    public EnvironmentModel test;
    public EnvironmentModel prod;

    public List<EnvironmentModel> getEnvironments() {
        List<EnvironmentModel> environments = new ArrayList<>();
        environments.add(getTest());
        environments.add(getProd());
        return environments;
    }
}
