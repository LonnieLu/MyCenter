package com.lonnie.center.capture.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lonnie.center.capture.WebResult;
import com.lonnie.center.capture.task.CaptureTask;
import com.lonnie.center.exception.UnableToParseResultException;

public class CSDNBlogParser extends BasicParser {

	/**
	 * Parse html through Jsoup
	 * @param content
	 * @param task
	 * @return result object
	 * @throws UnableToParseResultException 
	 */
	@Override
	public List<WebResult> parseResult(String content, CaptureTask task) throws UnableToParseResultException {
		List<WebResult> webResults = new ArrayList<WebResult>();
		Document document = Jsoup.parse(content);
		//Check whether result can parse
		Elements title = document.getElementsByAttributeValue("class", "link_title");
		if (null != title) {
			//Parse header info
			String titleText = title.get(0).text().trim();
			
			//Parse category info
			List<String> categories = new ArrayList<String>();
			Elements category = document.getElementsByAttributeValue("class", "link_categories");
			for (Element element : category) {
				for (Element childEl : element.children()) {
						categories.add(childEl.text().replace("分类", "").trim());
				}
			}
			
			//Parse tag info
			List<String> tags = new ArrayList<String>();
			Elements tagsEl = document.getElementsByAttributeValue("class", "tag2box");
			for (Element element : tagsEl) {
				for (Element childEl : element.children()) {
					tags.add(childEl.text().trim());
				}
			}
			
			//Parse blog content
			Element contentEl = document.getElementById("article_content");
			
			//Build result object
			WebResult result = new WebResult();
			result.setTitle(titleText);
			result.setCategories(categories);
			result.setTags(tags);
			result.setContent(contentEl.html());
			result.setUrl(task.getUrl());
			result.setParser(task.getParser());
			webResults.add(result);
			
			System.out.println(result.toString());
		} else {
			throw new UnableToParseResultException("Can't parser result for task [" + task.getUrl() + "]!!");
		}
		return webResults;
	}

}
