package online.nwen.entry.model;

/**
 * A factory used to success the api response with a given payload
 */
public class ApiResponseGenerator {
    public static final ApiResponseGenerator INSTANCE = new ApiResponseGenerator();

    private ApiResponseGenerator() {
    }

    /**
     * Generate a success response.
     *
     * @param payload       The response payload
     * @param <PayloadType> The response payload type.
     * @return The api response
     */
    public <PayloadType> ApiResponse<PayloadType> success(PayloadType payload) {
        ApiResponse<PayloadType> result = new ApiResponse<>();
        result.setPayload(payload);
        result.setSuccess(true);
        return result;
    }

    /**
     * Generate a fail response.
     *
     * @param payload       The response payload
     * @param <PayloadType> The response payload type.
     * @return The api response
     */
    public <PayloadType> ApiResponse<PayloadType> fail(PayloadType payload) {
        ApiResponse<PayloadType> result = new ApiResponse<>();
        result.setPayload(payload);
        result.setSuccess(false);
        return result;
    }
}
