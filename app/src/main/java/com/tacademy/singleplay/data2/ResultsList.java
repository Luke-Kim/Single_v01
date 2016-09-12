package com.tacademy.singleplay.data2;

public class ResultsList<T> {
    private T results;
    private T result;
    private String code;
    private String error;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResults() {
        return this.results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
