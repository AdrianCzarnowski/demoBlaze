package configuration.properties;

import configuration.lists.DriverEnum;
import configuration.model.EnvironmentModel;
import configuration.properties.reader.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class App {
    private static Logger log = LoggerFactory.getLogger("AppProperties.class");
    YamlReader yamlReader = new YamlReader();

    public App() {
        setEnvironmentFromYaml();
    }

    public DriverEnum setBrowserFromYaml() {
        return yamlReader.getConfig().getBrowserConfig().getDriverEnum();
    }

    private void setEnvironmentFromYaml() {

        List<EnvironmentModel> environments = yamlReader.getConfig().getEnvironment().getEnvironments();
        boolean foundActiveBrowser = false;
        for (EnvironmentModel environmentModel : environments) {
            if (environmentModel.isActive()) {
                foundActiveBrowser = true;
                Map<String, Object> properties = environmentModel.getEnvironmentProperties();
                for (Map.Entry entry : properties.entrySet()) {
                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
                }
                log.info("Properties size: " + properties.size());
                break;
            }
        }
    }
}

