package online.nwen.entry.request;

public class ApiRequest<PayloadType> {
    private String token;
    private PayloadType payload;

    public PayloadType getPayload() {
        return payload;
    }

    public void setPayload(PayloadType payload) {
        this.payload = payload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
