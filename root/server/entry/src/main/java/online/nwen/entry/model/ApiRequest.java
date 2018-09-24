package online.nwen.entry.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The api request
 *
 * @param <PayloadType>
 */
public class ApiRequest<PayloadType> {
    /**
     * Api request header
     */
    private Map<String, String> header;
    /**
     * Api request payload
     */
    private PayloadType payload;

    public ApiRequest() {
        this.header = new HashMap<>();
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
}
