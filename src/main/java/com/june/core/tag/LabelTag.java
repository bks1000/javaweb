package com.june.core.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 自定义标签
 * 参考：http://blog.csdn.net/lw001x/article/details/7589302
 * @author lenovo
 *
 */
public class LabelTag extends TagSupport {
	
	private String text;
	
	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text 要设置的 text
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int doStartTag() throws JspException {
		
		JspWriter writer = this.pageContext.getOut();
		try {
			writer.println(String.format("<span>%s</span>",text));
		} catch (IOException e) {
			 throw new JspException(e.getMessage());
		}
		
		return SKIP_BODY;
	}
	
	@Override
	public void release() {
		super.release();
		this.text="";
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
