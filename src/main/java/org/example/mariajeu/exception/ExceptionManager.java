package org.example.mariajeu.exception;

import org.example.mariajeu.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDTO> handleAppException(AppException ex) {
        String status = ex.getErrorCode().getMessage();
        ErrorDTO errorDTO = new ErrorDTO(status, ex.getMessage());
        return new ResponseEntity<>(errorDTO, ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleRuntimeException(RuntimeException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("type", "INTERNAL_SERVER_ERROR");
        errorResponse.put("message", e.getMessage());
        return errorResponse;
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<AppException> handleValidationException(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        List<String> errorMessages = new ArrayList<>();
//        for (FieldError fieldError : fieldErrors) {
//            errorMessages.add(fieldError.getDefaultMessage());
//        }
//        AppException errorResponse = new AppException(ErrorCode.BAD_REQUEST, errorMessages.toString());
//
//        return ResponseEntity.badRequest().body(errorResponse);
//    }


}
