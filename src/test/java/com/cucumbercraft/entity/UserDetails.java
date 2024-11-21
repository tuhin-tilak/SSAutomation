package com.cucumbercraft.entity;

import com.cucumbercraft.framework.PropertyConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;


@Builder
@Getter
@ToString
public class UserDetails {
    private final Map<String, Object> userDetails;
    PropertyConfig config;


    public static UserDetails getUserDetails(String username) {
        String url = "https://apim-anpost-statesavings.dev-anpost.com/ss-uat/ss-myaccount-s3/api/v3/account/get-user-details";
        Map<String, String> headerMap = Map.of("Ocp-Apim-Subscription-Key", "8de68fd7a9354781aa1005d998e733e8",
                "X-StateSavings-ApiKey", "0bd65072-1ef5-4f5a-9714-0cbc6263225e");
//        String url="https://preprodssapp.corp.anpost.com/api/v3/account/get-user-details";
//        Map<String,String >headerMap=Map.of("Ocp-Apim-Subscription-Key","8de68fd7a9354781aa1005d998e733e8","X-StateSavings-ApiKey","0bd65072-1ef5-4f5a-9714-0cbc6263225e");
        Response response = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .headers(headerMap)
                .baseUri(url)
                .queryParam("username", username)
                .get();


        return UserDetails.builder()
                .userDetails(response.jsonPath().getMap(""))
                .build();
    }


}
