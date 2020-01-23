package api.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResponse {

    private Status status;

    private Integer innerCode;

    private boolean success;

    private Integer code;

    private String error;

}

