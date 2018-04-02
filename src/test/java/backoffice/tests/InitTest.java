package backoffice.tests;


import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


public class InitTest
{
    @Test
    @Ignore
    public void pingTest()
    {
        try
        {
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