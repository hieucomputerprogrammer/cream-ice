package io.hieu.creamice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        exception.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException userNotFoundException,
//                                                                     WebRequest webRequest) {
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(new Date(),
//                        userNotFoundException.getMessage(),
//                        webRequest.getDescription(false));
//
//        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(RoleNotFoundException.class)
    public final ResponseEntity<Object> handleRoleNotFoundExceptions(RoleNotFoundException roleNotFoundException,
                                                                     WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        roleNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public final ResponseEntity<Object> handleRecipeNotFoundExceptions(RecipeNotFoundException recipeNotFoundException,
                                                                     WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        recipeNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public final ResponseEntity<Object> handlePaymentNotFoundExceptions(PaymentNotFoundException paymentNotFoundException,
                                                                       WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        paymentNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleOrderNotFoundExceptions(OrderNotFoundException orderNotFoundException,
                                                                        WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        orderNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderDetailsNotFoundException.class)
    public final ResponseEntity<Object> handleOrderDetailsNotFoundExceptions(OrderDetailsNotFoundException orderDetailsNotFoundException,
                                                                      WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        orderDetailsNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IceCreamNotFoundException.class)
    public final ResponseEntity<Object> handleIceCreamNotFoundExceptions(IceCreamNotFoundException iceCreamNotFoundException,
                                                                             WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        iceCreamNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public final ResponseEntity<Object> handleFeedbackNotFoundExceptions(FeedbackNotFoundException feedbackNotFoundException,
                                                                         WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        feedbackNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FAQNotFoundException.class)
    public final ResponseEntity<Object> handleFAQNotFoundExceptions(FAQNotFoundException faqNotFoundException,
                                                                         WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        faqNotFoundException.getMessage(),
                        webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(),
                        "Validation failed.",
                        exception.getBindingResult().toString());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}