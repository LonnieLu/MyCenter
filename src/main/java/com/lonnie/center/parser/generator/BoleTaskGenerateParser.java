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
		for (CaptureTask task : tasks) {
			System.out.println(task.getTitle());
		}
		return tasks;
	}

	@Override
	public String getParser() {
		return "BoleBlogParser";
	}

	@Override
	public String getHttpMethod() {
		return HttpConnectionUtil.HTTP_GET;
	}

}
