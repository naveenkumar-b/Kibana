package com.sampleapp.xmltojson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


public class HTMLTOJson {

	public void convertHTMLToJson() throws JDOMException
	{
		InputStream isInHtml = null;
	    URL url = null;
	    URLConnection connection = null;
	    DataInputStream disInHtml = null;
	    FileOutputStream fosOutHtml = null;
	    FileWriter fwOutXml = null;
	    FileReader frInHtml = null;
	    BufferedWriter bwOutXml = null;
	    BufferedReader brInHtml = null;
	    try {
	        // url = new URL("www.climb.co.jp");
	        // connection = url.openConnection();
	        // isInHtml = connection.getInputStream();

	        frInHtml = new FileReader("D:\\Second.html");
	        brInHtml = new BufferedReader(frInHtml);
	        @SuppressWarnings("deprecation")
			SAXBuilder saxBuilder = new SAXBuilder(
	                "org.ccil.cowan.tagsoup.Parser", false);
	        org.jdom2.Document jdomDocument = saxBuilder.build(brInHtml);

	        XMLOutputter outputter = new XMLOutputter();
	        org.jdom2.output.Format newFormat = outputter.getFormat();
	        String encoding = "iso-8859-2";
	        newFormat.setEncoding(encoding);
	        outputter.setFormat(newFormat);

	        try {
	            outputter.output(jdomDocument, System.out);
	            fwOutXml = new FileWriter("D:\\Second.xml");
	            bwOutXml = new BufferedWriter(fwOutXml);
	            outputter.output(jdomDocument, bwOutXml);
	            System.out.flush();
	        } catch (IOException e) {
	        }

	    } catch (IOException e) {
	    } finally {
	        System.out.flush();
	        try {
	            isInHtml.close();
	            disInHtml.close();
	            fosOutHtml.flush();
	            fosOutHtml.getFD().sync();
	            fosOutHtml.close();
	            fwOutXml.flush();
	            fwOutXml.close();
	            bwOutXml.close();
	        } catch (Exception w) {

	        }
	    }

	}
}
