package com.tacademy.singleplay.data;

public class Keyword {
    private int totalItems;
    private int startIndex;
    private int itemsPerPage;
    private KeywordPaging paging;
    private KeywordResults[] results;

    public int getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getItemsPerPage() {
        return this.itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public KeywordPaging getPaging() {
        return this.paging;
    }

    public void setPaging(KeywordPaging paging) {
        this.paging = paging;
    }

    public KeywordResults[] getResults() {
        return this.results;
    }

    public void setResults(KeywordResults[] results) {
        this.results = results;
    }
}
