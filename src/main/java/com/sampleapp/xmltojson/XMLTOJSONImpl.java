package com.sampleapp.xmltojson;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.JSONObject;
import org.json.XML;

public class XMLTOJSONImpl {
	public void convert() {
		File dir = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\");
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith("results.xml");
			}
		});
		try {
			for (File file : files) {
				String data = "";
				data = FileUtils.readFileToString(file, "UTF-8");
				JSONObject xmlJSONObj = XML.toJSONObject(data);
				indexData(xmlJSONObj);
			}
		} catch (Exception ex) {
			System.out.println("Failed to convert XML to JSON, Error while parsing the XML");

		}
	}

	private void indexData(JSONObject xmlJSONObj) {
		try (RestHighLevelClient client = getESClient()) {
			IndexRequest ind = new IndexRequest("ac").source(xmlJSONObj.toMap());
			client.index(ind, RequestOptions.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RestHighLevelClient getESClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
	}
}
