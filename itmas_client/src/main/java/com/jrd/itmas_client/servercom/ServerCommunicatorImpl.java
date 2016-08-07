package com.jrd.itmas_client.servercom;

import com.jrd.itmas_client.infrastructure.exceptions.ServerCommunicationException;
import com.jrd.itmas_client.servercom.dto.UserDTO;
import com.jrd.itmas_client.infrastructure.utils.*;
import com.jrd.itmas_server.api.rest.dto.TaskDTO;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2016-05-29.
 */
public class ServerCommunicatorImpl implements ServerCommunicator {

    private Configuration configuration;

    private HttpClient client;

    private String serverAddress;

    private final static Literals.Exceptions.ServerCommunicationExceptions exceptionLiterals
            = Literals.get().getExceptions().getServerCommunicationExceptions();

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

    @Override
    public UserDTO userAdd(UserDTO userDTO) throws ServerCommunicationException {
        authenticate();
        HttpPost post = getPostMethod("/api/register", "application/json");
        try {
            StringEntity userEnt = new StringEntity(convertUserDtoToJson(userDTO));
            post.setEntity(userEnt);
            client.execute(post);
        } catch (UnsupportedEncodingException e) {
            throw new ServerCommunicationException("error 1", e);  //TODO: add description
        } catch (ClientProtocolException e) {
            throw new ServerCommunicationException("error 2", e);  //TODO: add description
        } catch (IOException e) {
            throw new ServerCommunicationException("error 3", e);  //TODO: add description
        }

        UserDTO user = getUserDataFromServer(userDTO.getLogin());
        logout();

        return user;
    }

    @Override
    public void deleteUser(String userLogin) throws ServerCommunicationException {
        authenticate();
        String queryString = serverAddress + "/api/deleteAccount/" + userLogin;
        HttpGet get = new HttpGet(queryString);

        try {
            HttpResponse response = client.execute(get);
        } catch (IOException ex) {
            throw new ServerCommunicationException(exceptionLiterals.getDeleteUser(), ex);
        } finally {
            logout();
        }
    }

    @Override
    public List<TaskDTO> allUserTasks(String userLogin) throws ServerCommunicationException {
        authenticate();

        List<TaskDTO> tasks = new ArrayList<>();
        String queryString = serverAddress + "/api/allTasks/" + userLogin;
        HttpGet get = new HttpGet(queryString);

        try {
            HttpResponse response = client.execute(get);
            String json = EntityUtils.toString(response.getEntity());
            tasks = convertTasksJsonToDto(json);
        } catch (IOException ex) {
            throw new ServerCommunicationException(exceptionLiterals.getDeleteUser(), ex);
        } finally {
            logout();
        }

        return tasks;
    }

    private void authenticate() throws ServerCommunicationException {
        HttpPost post = getPostMethod("/api/authentication", "application/x-www-form-urlencoded");
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("username", configuration.getProperty(Configuration.Keys.USER)));
        urlParameters.add(new BasicNameValuePair("password", configuration.getProperty(Configuration.Keys.PASSWORD)));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
            client.execute(post);
        } catch (IOException ex) {
            throw new ServerCommunicationException(exceptionLiterals.getAuthentication(), ex);
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
            throw new ServerCommunicationException(exceptionLiterals.getUserDataGet(), ex);
        }
    }

    private UserDTO convertUserJsonToDto(String json) throws ServerCommunicationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserDTO userDTO = mapper.readValue(json, UserDTO.class);
            return userDTO;
        } catch (IOException ex) {
            throw new ServerCommunicationException(exceptionLiterals.getUserDataInterpretation(), ex);
        }
    }

    private String convertUserDtoToJson(UserDTO userDTO) throws ServerCommunicationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(userDTO);
            return  json;
        } catch (IOException e) {
            throw new ServerCommunicationException(exceptionLiterals.getUserDataConversion(), e);
        }
    }

    private List<TaskDTO> convertTasksJsonToDto(String json) throws ServerCommunicationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<TaskDTO> tasks = mapper.readValue(json,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, TaskDTO.class));
            return tasks;
        } catch (IOException ex) {
            throw new ServerCommunicationException(exceptionLiterals.getUserDataInterpretation() + ex.getMessage(), ex);
        }
    }

    private HttpPost getPostMethod(String url, String header) {
        HttpPost post = new HttpPost(serverAddress + url);
        post.setHeader("Content-Type", header);

        return post;
    }
}
