package net.bootsfaces.component.dataTable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author jottyfan
 *
 */
public abstract class ADataTablePropertyBean {
	private Integer pageLength;
	private Integer currentPage;
	private String searchTerm;
	private String orderString;

	public ADataTablePropertyBean(Integer pageLength, Integer currentPage, String searchTerm, String orderString) {
		this.pageLength = pageLength;
		this.currentPage = currentPage;
		this.searchTerm = searchTerm;
		this.orderString = orderString;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

	public void setJson(String jsonString) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(jsonString);
		pageLength = ensureInteger(json.get("pageLength"));
		currentPage = ensureInteger(json.get("currentPage"));
		searchTerm = ensureString(json.get("searchTerm"));
		orderString = ensureString(json.get("orderString"));
	}

	@SuppressWarnings("unchecked")
	public String getJson() {
		JSONObject json = new JSONObject();
		json.put("pageLength", pageLength);
		json.put("currentPage", currentPage);
		json.put("searchTerm", searchTerm);
		json.put("orderString", orderString);
		return json.toJSONString();
	}

	private final Integer ensureInteger(Object o) throws ParseException {
		if (o == null) {
			return null;
		} else if (o instanceof Integer) {
			return (Integer) o;
		} else if (o instanceof Long) {
			return ((Long) o).intValue();
		} else if (o instanceof String) {
			return Integer.valueOf((String) o);
		} else {
			throw new ParseException(ParseException.ERROR_UNEXPECTED_CHAR,
					"cannot convert " + o.getClass().getName() + " to Integer");
		}
	}

	private final String ensureString(Object o) {
		if (o == null) {
			return null;
		} else {
			return o.toString();
		}
	}
}
