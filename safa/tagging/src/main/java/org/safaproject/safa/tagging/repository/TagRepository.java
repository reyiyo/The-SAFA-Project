package org.safaproject.safa.tagging.repository;

import org.safaproject.safa.tagging.node.Tag;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TagRepository extends GraphRepository<Tag> {
	
	Tag findTagByValue(String value);

}
