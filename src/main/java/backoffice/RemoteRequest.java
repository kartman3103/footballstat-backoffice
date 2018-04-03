package backoffice;


import java.io.IOException;
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
}