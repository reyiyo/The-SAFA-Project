package org.safaproject.safa.tagging.repository;

import org.safaproject.safa.node.Tag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface TagRepository extends GraphRepository<Tag> {

	Tag findTagByValue(String value);

	/**
	 * This query is used for the first tag filter where the user has not
	 * selected any other tag before and the only parameter is the tagType.
	 * 
	 * It starts from the param TagType node and returns all the Tag nodes that
	 * are related to the TagType through a "tagType" relationship.
	 * 
	 * @param tagTypeId
	 * @return
	 */
	@Query("START tagType=node({0}) MATCH tag-[:tagType]->tagType RETURN tag")
	Iterable<Tag> filterTags(Long tagTypeId);

}
