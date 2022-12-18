package configuration.properties;

import configuration.lists.EnvironmentList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Config {
    public EnvironmentList environment;
    public BrowserConfig browserConfig;
}
