package com.epam.hibernate.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ChangePageTag extends TagSupport {

    private int page;
    private int itemsCount;
    private int perpage;
    private String controller;
    private String command;

    public void setController(String controller) {
        this.controller = controller;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    
    public void setPage(int page) {
        this.page = page;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }
    
    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int doStartTag() throws JspException {
        int maxpage = (itemsCount - 1) / perpage + 1;
        if (maxpage == 0) {
            return SKIP_BODY;
        }
        JspWriter out = pageContext.getOut();
        String path = controller + "?command=" + command;
        try {
            if (page != 1) {
                out.write((page - 1) + "<a href=" + path + "&appropriatePage=" + (page - 1) + "&itemsPerPage=" + perpage + "> << </a>");
            }
            out.write("&nbsp;&nbsp;&nbsp;" + page + "&nbsp;&nbsp;&nbsp;");
            if (page != maxpage) {
                out.write("<a href=" + path + "&appropriatePage=" + (page + 1) + "&itemsPerPage=" + perpage + "> >> </a>" + (page + 1) + "...");
            }
            out.write(" " + maxpage + "<br/>\n");
            String js = "return validate()";
            out.write("<form id=\"pagination\" action=\"" + controller + "\" onsubmit=\"" + js + "\" style=\"position: relative; text-align: center;\">\n" +
            "Items per page <br>\n" +
            "<input type=\"text\" name=\"itemsPerPage\" value=\"" + perpage + "\" id=\"itemsPerPage\"/><br>\n" +
            "Go to appropriate page <br>\n" +
            "<input type=\"text\" name=\"appropriatePage\" value=\"" + page + "\" id=\"appropriatePage\"/><br>\n" +
            "<span class=\"pageError\" id=\"pageError\" style=\"color: red;\"></span><br>\n" +
            "<input type=\"submit\" value=\"go!\"/>\n" +
            "<input type=\"hidden\" name=\"command\" value=\"" + command + "\"/>\n" +
        "</form>\n");
        } catch (IOException e) {
            Logger logger = Logger.getLogger("com.epam.hibernate.tag");
            logger.error(e.getMessage());
        }
        return SKIP_BODY;
    }
}
