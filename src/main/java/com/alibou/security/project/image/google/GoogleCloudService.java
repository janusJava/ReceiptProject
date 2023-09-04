package com.alibou.security.project.image.google;

import com.google.cloud.storage.BlobInfo;

import java.io.IOException;

public interface GoogleCloudService {

    BlobInfo uploadObjectFromMemory(byte[] imageBytes) throws IOException;

    String detectText(String filename);

}
