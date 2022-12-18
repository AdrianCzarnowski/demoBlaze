package configuration.properties;

import configuration.lists.DriverEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrowserConfig {
    private DriverEnum driverEnum;
}
