package com.lonnie.center.parser.generator;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lonnie.center.exception.UnableToParseResultException;
import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;


public class BoleTaskGenerateParser extends BasicTaskGenerateParser {

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.parser.generator.BasicTaskGenerateParser#parseResult(org.jsoup.nodes.Document)
	 */
	@Override
	public List<CaptureTask> parseResult(String content, TaskGenerateTrigger taskGenerateTrigger) throws UnableToParseResultException {
		List<CaptureTask> tasks = new ArrayList<CaptureTask>();
		Document document = Jsoup.parse(content);
		try {
			Elements els = document.getElementsByAttributeValue("class", "meta-title");
			if (els.isEmpty()) {
				throw new UnableToParseResultException("[" + this.getClass().getName() + "]Unable to parse result html");
			}
			for (Element element : els) {
				CaptureTask task = new CaptureTask(getParser(), element.attr("href"), getHttpMethod());
				task.setTitle(element.text());
				tasks.add(task);
			}
		} catch (Exception e) {
			throw new UnableToParseResultException("[" + this.getClass().getName() + "]Unable to parse result html");
		}
		return tasks;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.parser.generator.BasicTaskGenerateParser#getParser()
	 */
	@Override
	public String getParser() {
		return "BoleBlogParser";
	}

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.parser.generator.BasicTaskGenerateParser#updateCaptureTaskTrigger(com.lonnie.center.task.TaskGenerateTrigger)
	 */
	@Override
	public void updateCaptureTaskTrigger(TaskGenerateTrigger taskGenerateTrigger) {
		// TODO Auto-generated method stub
		
	}

}
