package org.safaproject.safa.web.controller.content;

import java.util.List;

import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.service.ContentService;
import org.safaproject.safa.web.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/search", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody
	Content[] search(@RequestBody SearchRequest searchRequest) {
		System.out.println("+++++++++++++++++++++++++++"
				+ searchRequest.getSelectedTags().get(0).getValue());
		System.out.println("+++++++++++++++++++++++++++"
				+ searchRequest.getFirstResult());
		System.out.println("+++++++++++++++++++++++++++"
				+ searchRequest.getMaxResults());

		List<Content> result = contentService.search(
				searchRequest.getSelectedTags(),
				searchRequest.getFirstResult(), searchRequest.getMaxResults());

		if (result.size() < 1) {
			System.out.println("+++++++++++++++++++++++++++ No result");
		}

		for (Content content : result) {
			System.out.println("+++++++++++++++++++++++++++" + content);
		}

		return result.toArray(new Content[0]);
	}
}
