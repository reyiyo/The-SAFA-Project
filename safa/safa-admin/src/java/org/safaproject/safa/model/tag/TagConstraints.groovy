package org.safaproject.safa.model.tag

constraints = {
	value(blank:false, nullable: false)
	tagType(nullable: false)
	iconURL(url:true)
}

