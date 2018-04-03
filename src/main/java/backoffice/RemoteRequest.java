package backoffice;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

@Component
public class RemoteRequest
{
    public HttpResponse post(String url)
    {
        Objects.requireNonNull(url, "Url cannot be null");

        try
        {
            Request request = Request.Post(url);
            return request.execute().returnResponse();
        }
        catch (IOException ex)
        {
            throw new RuntimeException();
        }
    }

    public String postContent(String url)
    {
        Objects.requireNonNull(url, "Url cannot be null");

        try
        {
            HttpResponse response = post(url);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            response.getEntity().writeTo(output);

            return output.toString(Charset.defaultCharset().name());
        }
        catch (IOException ex)
        {
            throw new RuntimeException();
        }

    }
}