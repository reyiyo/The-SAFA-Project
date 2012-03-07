package org.safaproject.safa.web.controller.tag;

import java.util.List;

import org.apache.log4j.Logger;
import org.safaproject.safa.model.tag.TagType;
import org.safaproject.safa.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tags")
public class TagsController {

	private static Logger logger = Logger.getLogger("TagsController");

	@Autowired
	private TagService tagService;

	@RequestMapping(value = "/getTypes", method = RequestMethod.GET)
	public @ResponseBody
	List<TagType> getTagTypes() {
		logger.debug("Received request to get All TagTypes");
		return tagService.getAllTagTypes();
	}

}
