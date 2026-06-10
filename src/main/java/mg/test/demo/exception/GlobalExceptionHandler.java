package mg.test.demo.exception;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFoundException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(" Resource not found ");
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail MethodArgumentNotValidException(Exception ex){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Invalid method argument");
        pb.setDetail(ex.getMessage());
        return pb;
    }


    // GENERIC EXCEPTION HANDLER
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) { 
        ProblemDetail prob = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        prob.setTitle(" An unexpected error occured ");
        prob.setDetail(ex.getMessage());
        return prob;
    }

}