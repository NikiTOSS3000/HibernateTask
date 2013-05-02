package com.epam.hibernate.command;

import com.epam.hibernate.util.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class NoCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return MessageManager.getStr("INDEX_PAGE_PATH");
    }
}
