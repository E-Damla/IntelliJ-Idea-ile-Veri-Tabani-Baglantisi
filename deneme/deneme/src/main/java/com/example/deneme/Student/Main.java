package com.example.deneme.Student;

import com.google.gson.JsonElement;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Main {
    public static final RestTemplate localRestTemplate = new RestTemplate();

    static String infoURL = "http://localhost:8013/actuator/httptrace";

    private static final Ogrenci builder = new Ogrenci();

    public static void dene() {


        try {
            ResponseEntity<String> response = localRestTemplate.exchange(infoURL, HttpMethod.GET, null, String.class);
            if (null != response && response.getStatusCode().equals(HttpStatus.OK)) {
                var buildResponseBody = response.getBody();
                if (buildResponseBody != null && buildResponseBody.length() > 0) {
                    if (!"{}".equals(buildResponseBody)) {
                        Ogrenci json =  new Ogrenci();
                        var jelem = json.fromJson(buildResponseBody, JsonElement.class);
                        OgrenciController responseJson = jelem.getAsJsonObject();

                        if (null != responseJson && responseJson.hashCode("httptrace")) {
                            OgrenciController buildJson = responseJson.get("httptrace").getAsJsonObject();
                            String version = (buildJson.hashCode("version")) ? buildJson.get("version").getAsString() : "?";
                            builder.setVersion(version);
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }

    }

}

