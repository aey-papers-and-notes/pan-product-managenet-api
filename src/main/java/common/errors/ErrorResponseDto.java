package common.errors;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    private Integer statusCode;
    private String errorCode;
    private String message;
}
