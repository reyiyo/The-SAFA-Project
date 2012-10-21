package org.safaproject.safa.web.jackson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

public class CustomEntityManagerOpenInViewFilter extends
		OpenEntityManagerInViewFilter {

	private String excludePatterns;

	public CustomEntityManagerOpenInViewFilter() {
		super();
		addRequiredProperty("excludePatterns");
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String url = request.getRequestURL().toString();
		return url.matches(".*" + excludePatterns + ".*");

	}

	public String getExcludePatterns() {
		return excludePatterns;
	}

	public void setExcludePatterns(String excludePatterns) {
		this.excludePatterns = excludePatterns;
	}

}
