package com.lonnie.center.parser.capture;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoleBlogParser extends BasicParser {

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#isResultParseable(org.jsoup.nodes.Document)
	 */
	@Override
	public boolean isResultParseable(Document document) {
		Elements title = document.getElementsByAttributeValue("class", "entry-header");
		return title != null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseTitle(org.jsoup.nodes.Document)
	 */
	@Override
	public String parseTitle(Document document) {
		Elements title = document.getElementsByAttributeValue("class", "entry-header");
		return title.get(0).text().trim();
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseContents(org.jsoup.nodes.Document)
	 */
	@Override
	public String parseContents(Document document) {
		Elements contentEl = document.getElementsByAttributeValue("class", "entry");
		return contentEl.html();
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseCategories(org.jsoup.nodes.Document)
	 */
	@Override
	public List<String> parseCategories(Document document) {
		List<String> categories = new ArrayList<String>();
		Elements category = document.getElementsByAttributeValue("rel", "category tag");
		for (Element element : category) {
			if (element.attr("href").contains("/category/")) {
				categories.add(element.text().trim());
			}
		}
		return categories;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseTags(org.jsoup.nodes.Document)
	 */
	@Override
	public List<String> parseTags(Document document) {
		List<String> tags = new ArrayList<String>();
		Elements tagsEl = document.getElementsByAttributeValue("class", "entry-meta-hide-on-mobile");
		for (Element element : tagsEl) {
			for (Element childEl : element.children()) {
				if (childEl.attr("href").contains("/tag/")) {
					tags.add(childEl.text().trim());
				}
			}
		}
		return tags;
	}

}
