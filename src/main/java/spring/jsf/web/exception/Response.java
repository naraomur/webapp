package spring.jsf.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Response {
    //to convert the timestamp to a normal time format
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;
    private String msg;

    public Response() {
        time = LocalDateTime.now();
    }

    public Response(HttpStatus status){
        this();
        this.status = status;
    }

    public Response(HttpStatus status, String msg) {
        this();
        this.msg = msg;
        this.status = status;
    }

    public Response(HttpStatus status, String msg, LocalDateTime time) {
        this();
        this.msg = msg;
        this.status = status;
        this.time = time;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
