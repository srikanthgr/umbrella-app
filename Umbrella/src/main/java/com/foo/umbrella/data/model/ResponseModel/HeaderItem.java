package com.foo.umbrella.data.model.ResponseModel;

public class HeaderItem implements DisplayableItem {

    public String getHeaderTitle() {
        return headerTitle;
    }

    public HeaderItem(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    private String headerTitle;


}
