package org.safaproject.safa.tagging.repository;

import java.util.Set;

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
	 *            - The TagType of tags that are wanted
	 * @return Tags matching the search
	 */
	@Query("START tagType=node({0}) MATCH tag-[:tagType]->tagType RETURN tag")
	Iterable<Tag> filterTags(Long tagTypeId);

	/**
	 * This query is used for filtering tags when the user has already selected
	 * another tags.
	 * 
	 * It starts from ALL the previously selected tags and returns all the Tag
	 * nodes of the TagType that are related to the previously selected tags
	 * through the FROM relationship on full deep.
	 * 
	 * @param selectedTagsIds
	 *            - The ids of the previously selected tags.
	 * @param tagTypeValue
	 *            - The TagType of the tags that are wanted
	 * @return Tags matching the search
	 */
	@Query("START selectedTags=node({0}) MATCH tag-[:FROM*]->selectedTags, "
			+ "tag-[:tagType]-type WHERE type.name={1} RETURN distinct tag")
	Iterable<Tag> filterTags(Set<Long> selectedTagsIds, String tagTypeValue);

}
