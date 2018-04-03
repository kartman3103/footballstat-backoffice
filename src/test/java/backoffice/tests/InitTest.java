package backoffice.tests;


import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.StringJoiner;

import backoffice.Application;
import backoffice.RemoteConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
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

    @Test
    @Ignore
    public void pingTest()
    {
        try
        {
            StringJoiner joiner = new StringJoiner("/");
            joiner.add(remoteConfig.getUrl());
            joiner.add(remoteConfig.getAvailableLeagues());

            Request request = Request.Post(joiner.toString());
            HttpResponse response = request.execute().returnResponse();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            response.getEntity().writeTo(output);

            String content = output.toString(Charset.defaultCharset().name());

            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            Assert.assertTrue(!content.isEmpty());
        }
        catch (Exception ex)
        {
            Assert.fail("Exception: " + ex);
        }
    }
}