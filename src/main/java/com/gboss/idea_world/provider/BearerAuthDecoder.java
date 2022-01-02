/*
 * Copyright 2017-2018, Redux Software.
 *
 * File: BearerAuthDecoder.java
 * Date: Nov 10, 2017
 * Author: P7107311
 * URL: www.redux.com
 */
package com.gboss.idea_world.provider;

import com.gboss.idea_world.exception.RestErrorCode;
import com.gboss.idea_world.exception.UnauthorizedException;

import static com.gboss.idea_world.constants.ResourceConstants.BEARER_AUTH_PREFIX;

/**
 * Provider to decode bearer token.
 */
public class BearerAuthDecoder {

	private final String accessToken;

	public BearerAuthDecoder(final String bearerToken) {
		if (!bearerToken.startsWith(BEARER_AUTH_PREFIX)) {
			throw new UnauthorizedException(RestErrorCode.ATH_003);
		}

		final String[] bearerTokens = bearerToken.split(BEARER_AUTH_PREFIX);
		if (bearerTokens.length != 2) {
			throw new UnauthorizedException(RestErrorCode.ATH_004);
		}
		this.accessToken = bearerTokens[1];
	}

	public String getAccessToken() {
		return accessToken;
	}

}