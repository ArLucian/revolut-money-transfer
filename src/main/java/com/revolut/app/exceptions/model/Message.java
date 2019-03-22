package com.revolut.app.exceptions.model;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private List<Info> infoList;

    public Message(final int type, final String path, final String exceptionMessage) {
        infoList = new ArrayList<>();
        final Info information = new Info(type, path, exceptionMessage);
        infoList.add(information);

    }

    public List<Info> getInfo() {
        return infoList;
    }

    public void setInfo(final List<Info> info) {
        this.infoList = info;
    }
}