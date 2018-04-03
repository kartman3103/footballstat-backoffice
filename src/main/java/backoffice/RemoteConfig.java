package backoffice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:remote.yml")
@ConfigurationProperties(prefix = "remote")
public class RemoteConfig
{
    private String url;

    private String availableLeagues;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getAvailableLeagues()
    {
        return availableLeagues;
    }

    public void setAvailableLeagues(String availableLeagues)
    {
        this.availableLeagues = availableLeagues;
    }
}