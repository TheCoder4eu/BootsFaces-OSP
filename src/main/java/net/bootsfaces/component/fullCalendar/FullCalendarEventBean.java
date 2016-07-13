package net.bootsfaces.component.fullCalendar;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author henkej
 *
 */
public abstract class FullCalendarEventBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private Boolean allDay;
	private Date start;
	private Date end;
	private Boolean editable;
	private Boolean startEditable;
	private Boolean durationEditable;
	private String color;
	private String url;

	public FullCalendarEventBean(String title, Date start) {
		super();
		this.title = title;
		this.start = start;
	}

	protected String toJavascriptDate(Date date){
		StringBuilder buf = new StringBuilder("new Date(");
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		buf.append(cal.get(Calendar.YEAR)).append(",");
		buf.append(cal.get(Calendar.MONTH)).append(",");
		buf.append(cal.get(Calendar.DAY_OF_MONTH)).append(",");
		buf.append(cal.get(Calendar.HOUR_OF_DAY)).append(",");
		buf.append(cal.get(Calendar.MINUTE)).append(",");
		buf.append(cal.get(Calendar.SECOND));
		buf.append(")");
		return buf.toString();
	}
	
	/**
	 * add fields to json string
	 * 
	 * @param buf
	 */
	public abstract void addExtendedFields(StringBuilder buf);
	
	public String toJson() {
		StringBuilder buf = new StringBuilder("{");
		buf.append("title:'").append(title).append("',");
		if (allDay != null) {
			buf.append("allDay:").append(allDay).append(",");
		}
		if (editable != null) {
			buf.append("editable:").append(editable).append(",");
		}
		if (startEditable != null) {
			buf.append("startEditable:").append(startEditable).append(",");
		}
		if (durationEditable != null) {
			buf.append("durationEditable:").append(durationEditable).append(",");
		}
		if (color != null) {
			buf.append("color:'").append(color).append("',");
		}
		if (end != null) {
			buf.append("end:").append(toJavascriptDate(end)).append(",");
		}
		if (url != null) {
			buf.append("url:'").append(url).append("',");
		}
		addExtendedFields(buf);
		// this is the last element, we need no trailing ,
		buf.append("start:").append(toJavascriptDate(start));
		buf.append("}");
		return buf.toString();
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean getDurationEditable() {
		return durationEditable;
	}

	public void setDurationEditable(Boolean durationEditable) {
		this.durationEditable = durationEditable;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getStartEditable() {
		return startEditable;
	}

	public void setStartEditable(Boolean startEditable) {
		this.startEditable = startEditable;
	}
}
