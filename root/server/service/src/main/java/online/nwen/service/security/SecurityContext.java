package online.nwen.service.security;

import online.nwen.service.api.ISecurityContext;
import online.nwen.service.dto.author.GetAuthorDetailResultDTO;

/**
 * The security context which used to hold the current author.
 */
class SecurityContext implements ISecurityContext {
    private GetAuthorDetailResultDTO authorDetail;
    private String jwtToken;

    SecurityContext(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    String getJwtToken() {
        return this.jwtToken;
    }

    @Override
    public GetAuthorDetailResultDTO getAuthorDetail() {
        return authorDetail;
    }

    public void setAuthorDetail(GetAuthorDetailResultDTO authorDetail) {
        this.authorDetail = authorDetail;
    }
}
