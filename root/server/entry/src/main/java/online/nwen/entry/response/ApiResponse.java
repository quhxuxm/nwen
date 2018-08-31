package online.nwen.entry.response;

public class ApiResponse<PayloadType> {
    private PayloadType payload;

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }
}
