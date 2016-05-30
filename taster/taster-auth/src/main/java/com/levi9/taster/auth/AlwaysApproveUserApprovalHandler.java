package com.levi9.taster.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;

/**
 * This approval handler is used to avoid asking for explicit approval for resource access.
 * 
 * @author d.gajic
 */
public class AlwaysApproveUserApprovalHandler implements UserApprovalHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.approval.UserApprovalHandler#updateBeforeApproval(org.springframework.security.oauth2.provider.AuthorizationRequest, org.springframework.security.core.Authentication)
	 */
	@Override
	public AuthorizationRequest updateBeforeApproval(
			AuthorizationRequest authorizationRequest,
			Authentication userAuthentication) {
		return authorizationRequest;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.approval.UserApprovalHandler#isApproved(org.springframework.security.oauth2.provider.AuthorizationRequest, org.springframework.security.core.Authentication)
	 */
	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest,
			Authentication userAuthentication) {
		return true;
	}

}