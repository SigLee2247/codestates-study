package com.codestates.response;

import com.codestates.exception.ExceptionCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h3>애플리케이션 예외 처리 실습 과제용 Solution 코드 포함</h3>
 * ErrorResponse는 애플리케이션에서 발생하는 예외를 이용해 클라이언트 측에 전달할 response body에 매핑되는 클래스입니다.
 * Solution 코드에 포함되는 코드는 아래와 같습니다.
 * <ul>
 *     <li>int status</li>
 *     <li>String message</li>
 *     <li>private ErrorResponse(int status, String message)</li>
 *     <li>{@link #of(ExceptionCode)}</li>
 *     <li>{@link #of(HttpStatus)}</li>
 * </ul>
 */
@Getter
public class ErrorResponse {
    /**
     * Http Status
     */
    private int status;
    /**
     * 에러 메시지
     */
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> violationErrors;

    /**
     * Http Status와 에러 메시지를 생성자 파라미터로 전달 받아 ErrorResponse의 status와 message 필드를 초기화 해줍니다.
     *
     * @param status    Http Status
     * @param message   에러 메시지
     */
    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private ErrorResponse(List<FieldError> fieldErrors,
                          List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    /**
     * {@link com.codestates.exception.ExceptionCode}를 통해 ErrorResponse의 status와 message를 제공합니다.
     *
     * @param exceptionCode 발생한 예외의 {@link com.codestates.exception.ExceptionCode} 정보
     * @return  ErrorResponse 객체
     */
    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    /**
     * <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html" target="_blank">
     *     HttpStatus
     * </a>를 통해 ErrorResponse의 status와 message를 제공합니다.
     * @param httpStatus 발생한 예외의 Http Status 정보
     * @return  ErrorResponse 객체
     */
    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    @Getter
    public static class FieldError {
        private String field;
        private Object rejectedValue;
        private String reason;

        private FieldError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<FieldError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                                                        bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                            "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue,
                                   String reason) {
            this.propertyPath = propertyPath;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        private static List<ConstraintViolationError> of(
                Set<ConstraintViolation<?>> constraintViolations) {
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()
                    )).collect(Collectors.toList());
        }
    }
}