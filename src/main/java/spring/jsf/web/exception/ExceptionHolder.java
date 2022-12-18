package spring.jsf.web.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.NoSuchElementException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHolder extends ResponseEntityExceptionHandler {
    private Response response;
    private final Translator translator = new Translator();
    private String msg;
    private Locale locale;
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(HttpServletRequest req) {
        response = new Response(HttpStatus.OK);
        locale = req.getLocale();
        //msg = translator.getStringNotFound(locale.getLanguage(), req.getRequestURI(), req.getQueryString());
        msg = translator.getStringNotFound("ky", req.getRequestURI(), req.getQueryString());
        response.setMsg(msg);
        return buildResponse(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(HttpServletRequest req){
        response = new Response(HttpStatus.OK);
        //msg = translator.getStringMistype(locale.getLanguage(), req.getQueryString());
        msg = translator.getStringMistype("ky", req.getQueryString());
        response.setMsg(msg);
        return buildResponse(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(HttpServletRequest req){
        response = new Response(HttpStatus.OK);
        locale = req.getLocale();
        //msg = translator.getStringNullPointer(locale.getLanguage(), req.getQueryString());
        msg = translator.getStringNullPointer("ky", req.getQueryString());
        response.setMsg(msg);
        return buildResponse(response);
    }

    private ResponseEntity<Object>  buildResponse(Response response) {
        return new ResponseEntity<>(response, response.getStatus());
    }
}
