package common.errors;

import jakarta.ws.rs.core.Response;

public class ErrorMapper {

    public static Response.ResponseBuilder toResponse(ErrorCode errorCode) {
        return Response
                .status(errorCode.getStatusCode())
                .entity(errorCode);
    }
}
