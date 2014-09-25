package com.lonnie.center.parser.generator;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lonnie.center.task.CaptureTask;
import com.lonnie.center.task.TaskGenerateTrigger;
import com.lonnie.center.util.HttpConnectionUtil;


public class BoleTaskGenerateParser extends BasicTaskGenerateParser {

	/*
	 * (non-Javadoc)
	 * @see com.lonnie.center.parser.generator.BasicTaskGenerateParser#parseResult(org.jsoup.nodes.Document)
	 */
	@Override
	public List<CaptureTask> parseResult(String content, TaskGenerateTrigger taskGenerateTrigger) {
		List<CaptureTask> tasks = new ArrayList<CaptureTask>();
		Document document = Jsoup.parse(content);
		Elements els = document.getElementsByAttributeValue("class", "meta-title");
		for (Element element : els) {
			CaptureTask task = new CaptureTask(getParser(), element.attr("href"), getHttpMethod());
			task.setTitle(element.text());
			tasks.add(task);
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
	 * @see com.lonnie.center.parser.generator.BasicTaskGenerateParser#getHttpMethod()
	 */
	@Override
	public String getHttpMethod() {
		return HttpConnectionUtil.HTTP_GET;
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
