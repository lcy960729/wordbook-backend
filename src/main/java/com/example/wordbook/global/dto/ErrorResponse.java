package com.example.wordbook.global.dto;

import com.example.wordbook.global.enums.ErrorCode;
import com.example.wordbook.global.exception.BusinessException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int status;
    private String code;
    private List<FieldError> errors;
    private String message;


    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(String field, String value, String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "{" +
                    "field='" + field + '\'' +
                    ", value='" + value + '\'' +
                    ", reason='" + reason + '\'' +
                    '}';
        }
    }

    public static ErrorResponse of(ErrorCode code) {
        ErrorResponse errorResponse = new ErrorResponse(code);
        errorResponse.errors = new ArrayList<>();

        return errorResponse;
    }

    public static ErrorResponse of(BusinessException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        errorResponse.message = e.getMessage();
        errorResponse.errors = new ArrayList<>();

        return errorResponse;
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_TYPE_VALUE);
        errorResponse.errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());;

        return errorResponse;
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult) {
        ErrorResponse errorResponse = new ErrorResponse(code);
        errorResponse.errors = FieldError.of(bindingResult);

        return errorResponse;
    }

    public static ErrorResponse of(ErrorCode code, List<FieldError> fieldErrors) {
        ErrorResponse errorResponse = new ErrorResponse(code);
        errorResponse.errors = fieldErrors;

        return errorResponse;
    }

    private ErrorResponse(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", errors=" + errors +
                ", message='" + message + '\'' +
                '}';
    }
}
