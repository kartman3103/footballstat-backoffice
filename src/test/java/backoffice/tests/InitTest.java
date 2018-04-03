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
    @Ignore
    public void pingTest()
    {
        StringJoiner joiner = new StringJoiner("/");
        joiner.add(remoteConfig.getUrl());
        joiner.add(remoteConfig.getAvailableLeagues());

//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        response.getEntity().writeTo(output);
//
//        String content = output.toString(Charset.defaultCharset().name());


        HttpResponse response = remoteRequest.post(joiner.toString());
        Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test(expected = NullPointerException.class)
    public void incorrectUrlRequestTest()
    {
        remoteRequest.post(null);
    }
}