package online.nwen.entry.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The api response
 *
 * @param <PayloadType>
 */
public class ApiResponse<PayloadType> {
    /**
     * Whether the response is a success response
     */
    private boolean success;
    /**
     * The response header
     */
    private Map<String, String> header;
    /**
     * The response payload
     */
    private PayloadType payload;

    public ApiResponse() {
        this.header = new HashMap<>();
        this.success = true;
    }

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
