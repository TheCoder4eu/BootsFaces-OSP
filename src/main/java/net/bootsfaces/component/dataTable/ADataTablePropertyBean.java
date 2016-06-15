package net.bootsfaces.component.dataTable;

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
}
