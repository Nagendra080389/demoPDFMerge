package com.deloitte.broker;

public class BigOpertaion {
    private String fileIds;
    private String accessToken;
    private String instanceURL;
    private boolean useSoap;

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getInstanceURL() {
        return instanceURL;
    }

    public void setInstanceURL(String instanceURL) {
        this.instanceURL = instanceURL;
    }

    public boolean isUseSoap() {
        return useSoap;
    }

    public void setUseSoap(boolean useSoap) {
        this.useSoap = useSoap;
    }
}
