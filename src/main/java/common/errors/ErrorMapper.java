package common.errors;

import jakarta.ws.rs.core.Response;

public class ErrorMapper {

    public static Response.ResponseBuilder toResponse(ErrorCode errorCode) {
        ErrorResponseDto error = ErrorResponseDto
                .builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .errorCode(errorCode.getErrorCode())
                .build();
        return Response
                .status(errorCode.getStatusCode())
                .entity(error);
    }
}
