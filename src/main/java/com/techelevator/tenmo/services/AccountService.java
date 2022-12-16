package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import io.cucumber.java.bs.A;
import okhttp3.Response;
import org.springframework.http.HttpEntity;
import com.techelevator.tenmo.model.AuthenticatedUser;

import java.math.BigDecimal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


public class AccountService {

    public String authToken = null;
    private static final String API_BASE_URL = "http://localhost:8080/";
    private AuthenticatedUser currentUser;
    private static RestTemplate restTemplate = new RestTemplate();

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public BigDecimal getAccountBalance(int id) {
        BigDecimal balance = new BigDecimal(0);

        try {
            balance = restTemplate.exchange(API_BASE_URL + "account/" + id, HttpMethod.GET,
                    makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current balance is : $" + balance);
        } catch (RestClientException e) {
            System.out.println("Error retrieving balance. Please try again.");
        }
        return balance;
    }

    public int getAccountId(int id) {
//        Account account = null;
//        ResponseEntity<Integer> response =
//                restTemplate.exchange(API_BASE_URL + "account/account/" + id, HttpMethod.GET,
//                        makeAuthEntity(), Integer.class);
//        account = response.getBody();
//        int accountId = account.getId();
//        return accountId;
        int accountId = 10;
        try {
            accountId = restTemplate.getForObject(API_BASE_URL + "account/account/" + id, Integer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return accountId;
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
