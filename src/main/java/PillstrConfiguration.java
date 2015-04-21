import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by keegan on 4/17/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PillstrConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty("test")
    private String test;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public String getTest() {
        return test;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
