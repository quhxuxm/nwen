package online.nwen.entry.common;

import online.nwen.entry.response.ApiResponse;

public class ApiResponseGenerator {
    public static final ApiResponseGenerator INSTANCE = new ApiResponseGenerator();

    private ApiResponseGenerator() {
    }

    public <PayloadType> ApiResponse<PayloadType> generate(PayloadType payload) {
        ApiResponse<PayloadType> result = new ApiResponse<>();
        result.setPayload(payload);
        result.setSuccess(true);
        return result;
    }
}
