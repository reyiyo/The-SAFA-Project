package org.safaproject.safa.model.tag

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

constraints = {
	tagName(blank:false, nullable: false, unique:true)
	tagDataType(nullable: false)
}

