package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Create an implementation of a Rest API client.
 * Prints out how many records exists for each gender and save this file to s3 bucket
 * API endpoint=> https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda
 * AWS s3 bucket => interview-digiage
 */
public class TASK4 {
    public static final String API_URL = "https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda";
    public static final String BUCKET_NAME = "interview-digiage";
    public static final String FILE_PATH = "C:\\temp\\gender_counts.txt";
    private static final String ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("AWS_SECRET_KEY");

    public static void main(String[] args) throws IOException {
        String jsonResponse = callApi(API_URL);

        String genderCounts = processApiResponse(jsonResponse);

        saveToFile(genderCounts, FILE_PATH);

        uploadToS3(FILE_PATH, BUCKET_NAME);
    }

    private static String callApi(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            } else {
                throw new IOException("No response from server");
            }
        }
    }

    private static String processApiResponse(String jsonResponse) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);

        int maleCount = 0;
        int femaleCount = 0;

        for (JsonElement element : jsonArray) {
            JsonObject record = element.getAsJsonObject();
            String gender = record.get("gender").getAsString();

            if ("M".equalsIgnoreCase(gender)) {
                maleCount++;
            } else if ("F".equalsIgnoreCase(gender)) {
                femaleCount++;
            }
        }

        return "Male: " + maleCount + "\nFemale: " + femaleCount;
    }

    private static void saveToFile(String data, String filePath) throws IOException {

        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        }
    }

    private static void uploadToS3(String filePath, String bucketName) {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);

        S3Client s3Client = S3Client.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();

        File file = new File(filePath);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getName())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        System.out.println("File uploaded successfully to S3");
    }

}