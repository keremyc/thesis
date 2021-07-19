package yuce.kerem.thesis.dto.payload.response;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/21/21 8:14 PM
 */
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
