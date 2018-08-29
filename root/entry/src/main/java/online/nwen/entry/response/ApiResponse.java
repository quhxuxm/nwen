package online.nwen.entry.response;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse<PayloadType> {
    public enum Status {
        SUCCESS,
        FAIL
    }

    private Status status;
    private Map<String, String> header;
    private PayloadType payload;

    public ApiResponse() {
        this.status = Status.SUCCESS;
        this.header = new HashMap<>();
    }

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }
}
