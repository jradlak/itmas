package com.jrd.itmas_client.serververcom;


import com.jrd.itmas_client.serververcom.dto.UserDTO;
import com.jrd.itmas_client.utils.Configuration;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-05-29.
 */
public class ServerCommunicator {

    private Configuration configuration;

    private HttpClient client;

    private String serverAddress;

    public ServerCommunicator(Configuration configuration) {
        client = HttpClientBuilder.create().build();
        this.configuration = configuration;
        this.serverAddress = configuration.getProperty(Configuration.Keys.SERVER);
    }

    public UserDTO getUserDataFromServer(String userLogin) throws IOException {
        authenticate();

        String json = retreiveUserJson(userLogin);
        UserDTO userDTO = convertUserJsonToDto(json);

        logout();
        return userDTO;
    }

    private void authenticate() throws IOException {
        HttpPost post = new HttpPost(serverAddress + "/api/authentication");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("username", configuration.getProperty(Configuration.Keys.USER)));
        urlParameters.add(new BasicNameValuePair("password", configuration.getProperty(Configuration.Keys.PASSWORD)));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
    }

    private void logout() {
        //TODO
    }

    private String retreiveUserJson(String userLogin) throws IOException {
        String queryString = serverAddress + "/api/account";
        if (userLogin != null && !userLogin.isEmpty()) {
            queryString += "ByLogin/" + userLogin;
        }

        HttpGet get = new HttpGet(queryString);

        HttpResponse response = client.execute(get);
        String json = EntityUtils.toString(response.getEntity());
        return json;
    }

    private UserDTO convertUserJsonToDto(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = mapper.readValue(json, UserDTO.class);
        return userDTO;
    }
}
