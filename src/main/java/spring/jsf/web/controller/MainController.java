package spring.jsf.web.controller;

import okhttp3.HttpUrl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.jsf.web.dto.TransferRequest;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(value = "main")
public class MainController {
    private static final String baseUrl = "http://localhost:8080/";

    @RequestMapping(value = "/post-get", method = RequestMethod.POST)
    public String main(@RequestBody TransferRequest request) throws IOException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + "employee/" + request.getEmId() + "/department/" + request.getDepId())).newBuilder();
        String url = urlBuilder.build().toString();
        return okHttp.postDepToEmployee(url)+"\r\n"+okHttp.getDepById(request.getDepId()).toString();
    }
}
