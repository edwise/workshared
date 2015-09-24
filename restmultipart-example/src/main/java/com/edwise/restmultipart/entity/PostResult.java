package com.edwise.restmultipart.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostResult {

    private Map<String, String> originalPayload;
    private List<FileInfo> paths;

    public PostResult() {
        originalPayload = new HashMap<>();
        paths = new ArrayList<>();
    }

    public Map<String, String> getOriginalPayload() {
        return originalPayload;
    }

    public List<FileInfo> getPaths() {
        return paths;
    }

    public void addPath(FileInfo fileInfo) {
        paths.add(fileInfo);
    }

    public void addOriginalPayloadEntry(String key, String value) {
        originalPayload.put(key, value);
    }
}
