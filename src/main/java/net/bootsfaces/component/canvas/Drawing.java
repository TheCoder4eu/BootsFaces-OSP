package net.bootsfaces.component.canvas;

public class Drawing {
	private String javaScript = "";

	public Drawing circle(int centerx, int centery, int radius) {
		javaScript += "ctx.beginPath();ctx.arc(" + centerx + ", " + centery + ", " + radius
				+ ",0,2*Math.PI);ctx.stroke();";
		return this;
	}

	public Drawing filledCircle(int centerx, int centery, int radius, String color) {
		javaScript += "ctx.beginPath();ctx.arc(" + centerx + ", " + centery + ", " + radius
				+ ",0,2*Math.PI);ctx.fillStyle = '" + color + "';ctx.fill();ctx.stroke();";
		return this;
	}

	public Drawing line(int x1, int y1, int x2, int y2) {
		javaScript += "ctx.beginPath();ctx.moveTo(" + x1 + ", " + y1 + ");ctx.lineTo(" + x2 + ", " + y2
				+ ");ctx.stroke();";
		return this;
	}

	public Drawing text(int x, int y, String text, String font) {
		javaScript += "ctx.font = '" + font + "';ctx.fillText('" + text + "'," + x + ", " + y + ");";
		return this;
	}

	public String getJavaScript() {
		return javaScript;
	}
}
