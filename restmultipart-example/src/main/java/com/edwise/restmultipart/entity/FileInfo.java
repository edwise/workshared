package com.edwise.restmultipart.entity;

public class FileInfo {

    private String fileName;
    private String tempPath;

    public String getFileName() {
        return fileName;
    }

    public FileInfo setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getTempPath() {
        return tempPath;
    }

    public FileInfo setTempPath(String tempPath) {
        this.tempPath = tempPath;
        return this;
    }
}
