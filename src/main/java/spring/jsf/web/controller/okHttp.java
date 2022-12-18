package spring.jsf.web.controller;

import com.google.gson.Gson;
import okhttp3.*;
import spring.jsf.web.dto.Department;

import java.io.IOException;

public class okHttp {
    public static final MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;
    private static final String baseUrl = "http://localhost:8080/";
    private static final Gson gson = new Gson();
    public static String postDepToEmployee(String url) throws IOException {
        client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.RequestBody body = okhttp3.RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static Department getDepById(Long id) throws IOException{
        //created the client
        client = new OkHttpClient().newBuilder()
                .build();
        //got the Url
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl+"department/id").newBuilder();
        urlBuilder.addQueryParameter("id", id.toString());
        String url = urlBuilder.build().toString();
        //request
        Request request = new Request.Builder()
                .url(url)
                .build();
        //response
        try (Response response = client.newCall(request).execute()) {
            return gson.fromJson(response.body().string(), Department.class);
        }
    }

}
