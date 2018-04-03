package backoffice.tests;


import java.util.StringJoiner;

import backoffice.Application;
import backoffice.RemoteConfig;
import backoffice.RemoteRequest;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class InitTest
{
    @Autowired
    private RemoteConfig remoteConfig;

    @Autowired
    private RemoteRequest remoteRequest;

    @Test
    @Ignore(value = "Ignore while footballstat project not deployed to remote server")
    public void pingTest()
    {
        String url = urlFactory(remoteConfig.getUrl(), remoteConfig.getAvailableLeagues());

        HttpResponse response = remoteRequest.post(url);
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test(expected = NullPointerException.class)
    public void incorrectUrlRequestTest()
    {
        remoteRequest.post(null);
    }

    @Test
    public void contentTest()
    {
        String url = urlFactory(remoteConfig.getUrl(), remoteConfig.getAvailableLeagues());
        String content = remoteRequest.postContent(url);

        Assert.assertTrue(!content.isEmpty());
    }

    private String urlFactory(String ... urlParts)
    {
        StringJoiner joiner = new StringJoiner("/");
        for (String part : urlParts)
        {
            joiner.add(part);
        }
        return joiner.toString();
    }
}