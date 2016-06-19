package com.jrd.itmas_client.servercom;


import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.infrastructure.utils.*;
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
public class ServerCommunicatorImpl implements ServerCommunicator {

    private Configuration configuration;

    private HttpClient client;

    private String serverAddress;

    public ServerCommunicatorImpl(Configuration configuration) {
        client = HttpClientBuilder.create().build();
        this.configuration = configuration;
        this.serverAddress = configuration.getProperty(Configuration.Keys.SERVER);
    }

    @Override
    public UserDTO getUserDataFromServer(String userLogin) throws ServerCommunicationException {
        authenticate();

        String json = retreiveUserJson(userLogin);
        UserDTO userDTO = convertUserJsonToDto(json);

        logout();
        return userDTO;
    }

    private void authenticate() throws ServerCommunicationException {
        HttpPost post = new HttpPost(serverAddress + "/api/authentication");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("username", configuration.getProperty(Configuration.Keys.USER)));
        urlParameters.add(new BasicNameValuePair("password", configuration.getProperty(Configuration.Keys.PASSWORD)));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            client.execute(post);
        } catch (IOException ex) {
            throw new ServerCommunicationException("There is problem with authentication.", ex);
        }
    }

    private void logout() {
        //TODO
    }

    private String retreiveUserJson(String userLogin) throws ServerCommunicationException {
        String queryString = serverAddress + "/api/account";
        if (userLogin != null && !userLogin.isEmpty()) {
            queryString += "ByLogin/" + userLogin;
        }

        HttpGet get = new HttpGet(queryString);

        try {
            HttpResponse response = client.execute(get);
            String json = EntityUtils.toString(response.getEntity());
            return json;
        } catch (IOException ex) {
            throw new ServerCommunicationException("There is problem with get user data from server", ex);
        }
    }

    private UserDTO convertUserJsonToDto(String json) throws ServerCommunicationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserDTO userDTO = mapper.readValue(json, UserDTO.class);
            return userDTO;
        } catch (IOException ex) {
            throw new ServerCommunicationException("There is problem with interpret user data from server", ex);
        }
    }
}
