package backoffice.tests;


import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import backoffice.Application;
import backoffice.RemoteConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Assert;
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
    public void pingTest()
    {
        try
        {
            String url = remoteConfig.getUrl();
            System.out.println(url);

            Request request = Request.Post("http://localhost:8585/availableLeagues");
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