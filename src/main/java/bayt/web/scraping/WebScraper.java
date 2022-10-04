package bayt.web.scraping;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

/**
 * This is a sample web scrapper class to fetch individual links of the items
 * @author gjp
 *
 */
public class WebScraper {

	public static void main(String[] args) {

		String rootUrl = "https://www.bayut.com";
		String baseUrl = rootUrl + "/for-sale/apartments/dubai/";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {

			HtmlPage page = client.getPage(baseUrl);
			page.getBody();
			//get the HTML element from the Xpath corresponding to UL
			ArrayList<HtmlUnorderedList> htmlUnorderedList = (ArrayList<HtmlUnorderedList>) page
					.getByXPath("//*[@id=\"body-wrapper\"]/main/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/ul");

			for (HtmlUnorderedList htmlUnorderedItem : htmlUnorderedList) {
				DomNodeList<DomNode> domNodeList = htmlUnorderedItem.getChildNodes();
				for (DomNode domNodeItme : domNodeList) {
					if (domNodeItme.getNodeName().endsWith("li")) {

						DomNodeList<DomNode> domNodechildList = domNodeItme.getChildNodes();
						for (DomNode domNodechildItem : domNodechildList) {
							if (domNodechildItem.getNodeName().equals("article")) {
								DomNodeList<DomNode> domNodechildList1 = domNodechildItem.getChildNodes();
								for (DomNode domNodechildItem1 : domNodechildList1) {
									if (domNodechildItem1.getNodeName().equals("div")) {
										DomNodeList<DomNode> domNodechildanchor = domNodechildItem1.getChildNodes();
										for (DomNode domNodechildanchoritem : domNodechildanchor) {
											if (domNodechildanchoritem.getNodeName().equals("a")) {
												HtmlAnchor htmlAnchor = (HtmlAnchor) domNodechildanchoritem;
												System.out.println(rootUrl + htmlAnchor.getHrefAttribute());
											}
										}
									}
								}

							}
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
