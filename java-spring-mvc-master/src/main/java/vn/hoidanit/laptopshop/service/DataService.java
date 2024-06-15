package vn.hoidanit.laptopshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataService {
    @Autowired
    private RestTemplate rest;
    private ObjectMapper mapper = new ObjectMapper();
    // private String host = "";
    private String host = "http://localhost:8080";

    public void callUrl(String url) {
        rest.exchange(host + url, HttpMethod.GET, null, Void.class);
    }

    public <T> T getDataFromUrl(String url, Class<T> valueType) {
        try {
            var data = rest.exchange(host + url, HttpMethod.GET, null, String.class).getBody();
            return mapper.readValue(data, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getDataFromUrl(String url, TypeReference<T> valueTypeRef) {
        try {
            var data = rest.exchange(host + url, HttpMethod.GET, null, String.class).getBody();
            return mapper.readValue(data, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void postDataToUrl(String url, Object o) {
        try {
            String data = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(o);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(data, headers);
            rest.exchange(host + url, HttpMethod.POST, entity, Void.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T postDataToUrl(String url, T o, Class<T> valueType) {
        try {
            String data = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(o);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(data, headers);
            var newData = rest.exchange(host + url, HttpMethod.POST, entity, String.class).getBody();
            return mapper.readValue(newData, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}