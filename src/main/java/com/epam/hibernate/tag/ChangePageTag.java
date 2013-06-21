package com.epam.hibernate.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ChangePageTag extends TagSupport {

    private int page;
    private int maxpage;
    private int perpage;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }
    
    public void setMaxpage(int maxpage) {
        this.maxpage = maxpage;
    }

    public int doStartTag() throws JspException {
        if (maxpage == 0) {
            return SKIP_BODY;
        }
        JspWriter out = pageContext.getOut();
        String path = "controller?command=list";
        try {
            if (page != 1) {
                out.write((page - 1) + "<a href=" + path + "&appropriatePage=" + (page - 1) + "&employeePerPage=" + perpage + "> << </a>");
            }
            out.write("&nbsp;&nbsp;&nbsp;" + page + "&nbsp;&nbsp;&nbsp;");
            if (page != maxpage) {
                out.write("<a href=" + path + "&appropriatePage=" + (page + 1) + "&employeePerPage=" + perpage + "> >> </a>" + (page + 1) + "...");
            }
            out.write(" " + maxpage + "<br/>");
        } catch (IOException e) {
            Logger logger = Logger.getLogger("com.epam.hibernate.tag");
            logger.error(e.getMessage());
        }
        return SKIP_BODY;
    }
}
