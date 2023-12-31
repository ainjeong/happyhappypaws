package com.example.animal.controller;

import com.example.animal.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class RestTestController {

    @Autowired
    TestService testService;

    @GetMapping("/api")
    public String getApi() throws IOException {
        StringBuilder result = new StringBuilder();

        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?"
                + "_type=json"
                + "&numOfRows=999"
                + "&serviceKey=P/hL5EXdd/Uh3HtYbHBtZI9PnkTMtbqImGmluwFnOgM/sLoypgqVKpFQ17t8zpHrnqoTMIh2ZJCJ2XOx7QFDnw==";
        URL url = new URL(apiUrl);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
        String returnLine;


        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n\r");
        }
            urlConnection.disconnect();
        testService.init(result.toString());

        return result.toString();

    }


}



