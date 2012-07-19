package org.safaproject.safa.model.content

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.URL;
import org.safaproject.safa.model.tag.Tag;

constraints = {
	url(url:true, nullable: false, blank:false)
	resourceType(nullable: false)
	size(min:0L, nullable: false)
}

