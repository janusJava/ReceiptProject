package com.alibou.security.project.image.google;


import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alibou.security.project.image.utils.ParseText.*;


@Service
@Slf4j
public class GoogleCloudServiceImpl implements GoogleCloudService {

    @Value("${project.id}")
    private String projectId;
    @Value("${bucket.name}")
    private String bucketName;

    @Override
    public BlobInfo uploadObjectFromMemory(byte[] imageBytes) throws IOException {
        String objectName = generateObjectName();
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
        storage.createFrom(blobInfo, new ByteArrayInputStream(imageBytes));
        log.info("Object " + objectName + " uploaded to bucket " + bucketName);
        return blobInfo;
    }

    private String generateObjectName() {
        int receiptCounter = 0;
        receiptCounter++;
        return "receipt_" + receiptCounter + ".jpg";
    }

    @Override
    public String detectText(String objectName) {
        List<AnnotateImageRequest> visionRequests = new ArrayList<>();
        String gcsPath = String.format("gs://%s/%s", bucketName, objectName);
        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature textFeature = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest visionRequest = AnnotateImageRequest.newBuilder().addFeatures(textFeature).setImage(img).build();
        visionRequests.add(visionRequest);
        AnnotateImageResponse visionResponse;
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            visionResponse = client.batchAnnotateImages(visionRequests).getResponses(0);
            if (!visionResponse.hasFullTextAnnotation()) {
                return visionResponse.getError().getMessage();
            }
            if (visionResponse.hasError()) {
                return visionResponse.getError().getMessage();
            }
        } catch (IOException e) {
            return e.getMessage();
        }
        String text = visionResponse.getFullTextAnnotation().getText();
        //TODO: USUNĄĆ FINALNIE W PRACY - TYLKO DO TESTÓW!!!!!!!!!!
        System.out.println("Suma: " + getAmountOfMoney(text));
        System.out.println("Data: " + getDate(text));
        System.out.println("Sklep: " + getShopName(text));
        return text;
    }

}