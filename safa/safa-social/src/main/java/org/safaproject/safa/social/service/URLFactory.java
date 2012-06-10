package org.safaproject.safa.social.service;

public class URLFactory {

	private String domain;
	
	private String contentTemplate;
	
	private final String URL_SEPARATOR = "/";

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setContentTemplate(String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}
	
	public String generateAbsoluteContentURL(Long contentId) {
		return this.domain + URL_SEPARATOR + this.contentTemplate + contentId.toString();
	}
}
