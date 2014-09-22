package com.lonnie.center.capture.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CSDNBlogParser extends BasicParser {

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#isResultParseable(org.jsoup.nodes.Document)
	 */
	@Override
	public boolean isResultParseable(Document document) {
		Elements title = document.getElementsByAttributeValue("class", "link_title");
		return null != title;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseTitle(org.jsoup.nodes.Document)
	 */
	@Override
	public String parseTitle(Document document) {
		Elements title = document.getElementsByAttributeValue("class", "link_title");
		return title.get(0).text().trim();
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseContents(org.jsoup.nodes.Document)
	 */
	@Override
	public String parseContents(Document document) {
		Element contentEl = document.getElementById("article_content");
		return contentEl.html();
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.capture.parser.BasicParser#parseCategories(org.jsoup.nodes.Document)
	 */
	@Override
	public List<String> parseCategories(Document document) {
		List<String> categories = new ArrayList<String>();
		Elements category = document.getElementsByAttributeValue("class", "link_categories");
		for (Element element : category) {
			for (Element childEl : element.children()) {
					categories.add(childEl.text().replace("分类", "").trim());
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
		Elements tagsEl = document.getElementsByAttributeValue("class", "tag2box");
		for (Element element : tagsEl) {
			for (Element childEl : element.children()) {
				tags.add(childEl.text().trim());
			}
		}
		return tags;
	}

}
