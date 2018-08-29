package online.nwen.entry.request;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest<PayloadType> {
    private Map<String, String> header;
    private PayloadType payload;

    public ApiRequest() {
        this.header = new HashMap<>();
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }
}
