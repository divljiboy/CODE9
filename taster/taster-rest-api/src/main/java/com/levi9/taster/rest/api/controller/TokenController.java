package com.levi9.taster.rest.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for OAuth tokens.
 *
 * @author m.zorboski
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {
	
	@Inject
	private ConsumerTokenServices consumerTokenServices;
	
	@Inject
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	/**
	 * Gets the token.
	 * Used to check if user is already authorized.
	 *
	 * @return the token
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> getToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof OAuth2Authentication) {
			return new ResponseEntity<>(authorizationServerTokenServices.getAccessToken((OAuth2Authentication) authentication).getValue(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * Revokes the token.
	 * Used for logout functionality.
	 *
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> revokeToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication instanceof OAuth2Authentication) {
			consumerTokenServices.revokeToken(authorizationServerTokenServices.getAccessToken((OAuth2Authentication) authentication).getValue());
			SecurityContextHolder.clearContext();
		} else {
			new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
