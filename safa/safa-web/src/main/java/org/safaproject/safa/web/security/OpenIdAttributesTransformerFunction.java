package org.safaproject.safa.web.security;

import org.springframework.security.openid.OpenIDAttribute;

import com.google.common.base.Function;

public class OpenIdAttributesTransformerFunction implements
		Function<OpenIDAttribute, String> {

	@Override
	public String apply(OpenIDAttribute attr) {
		return attr.getName();
	}

}
