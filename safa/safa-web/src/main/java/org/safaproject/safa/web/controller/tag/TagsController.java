package org.safaproject.safa.web.controller.tag;

import java.util.Set;

import org.apache.log4j.Logger;
import org.safaproject.safa.api.request.FilterTagsRequest;
import org.safaproject.safa.common.function.TagDTOToTag;
import org.safaproject.safa.common.function.TagToTagDTO;
import org.safaproject.safa.common.function.TagTypeToTagTypeDTO;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.safaproject.safa.node.dto.TagDTO;
import org.safaproject.safa.node.dto.TagTypeDTO;
import org.safaproject.safa.tagging.service.TaggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

@Controller
@RequestMapping("/tags")
public class TagsController {

	private static Logger logger = Logger.getLogger("TagsController");

	@Autowired
	private TaggingService taggingService;

	@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
	public @ResponseBody
	Set<TagTypeDTO> getTagTypes() {
		logger.debug("Received request to get All TagTypes");

		Set<TagType> tagTypes = taggingService.getAllTagTypes();

		return Sets.newHashSet(Collections2.transform(tagTypes,
				new TagTypeToTagTypeDTO()));
	}

	@RequestMapping(value = "/filterTags", method = RequestMethod.POST)
	@Transactional
	public @ResponseBody
	Set<TagDTO> getTagTypes(@RequestBody FilterTagsRequest request) {
		logger.debug("Received request to filter Tags:\n" + request.toString());

		Set<Tag> tags = taggingService.filterTags(
				new TagType(request.getTagType()),
				Sets.newHashSet(Collections2.transform(
						request.getSelectedTags(), new TagDTOToTag())));

		return Sets.newHashSet(Collections2.transform(tags, new TagToTagDTO()));
	}

	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public @ResponseBody
	Set<TagDTO> populateDb() {

		Set<Tag> tags = taggingService.populateDb();

		return Sets.newHashSet(Collections2.transform(tags, new TagToTagDTO()));
	}

}
