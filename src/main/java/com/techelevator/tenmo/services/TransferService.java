package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TransferService {

    public String authToken = null;
    private static final String API_BASE_URL = "http://localhost:8080/transfer";
    private static RestTemplate restTemplate = new RestTemplate();

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    //    public Transfer[] getTransfersByAccountId() {
//        Transfer[] transfers = null;
//        ResponseEntity<Transfer[]> response =
//                restTemplate.exchange(API_BASE_URL + "transfer/transfer/", HttpMethod.GET,
//                        makeAuthorizedEntity(), Transfer[].class);
//        transfers = response.getBody();
//        return transfers;
//    }

    public Transfer[] getAllTransfers() {
        Transfer[] transfers = null;
        try {
           ResponseEntity<Transfer[]> response = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Transfer[].class);
           transfers = response.getBody();
       } catch (Exception e) {
           e.getMessage();
        }
        return transfers;
    }

//    public Transfer newTransfer(Transfer transfer) {
//        HttpEntity<Transfer> entity = makeAuctionEntity(transfer);
//        Transfer newTransfer = null;
//        newTransfer = restTemplate.postForObject(API_BASE_URL + "/sendTransfer", entity, Transfer.class);
//        return newTransfer;
//    }

//    public List<Transfer> listTransfers(int id) {
//        List<Transfer> transfers = new ArrayList<>()
//
//        try {
//            balance = restTemplate.exchange(API_BASE_URL + "transfer/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthorizedEntity(), BigDecimal.class).getBody();
//            System.out.println("Your current balance is : $" + balance);
//        } catch (RestClientException e) {
//            System.out.println("Error retrieving balance. Please try again.");
//        }
//        return balance;
//    }
//    public Transfer transfersList() {
//        Transfer output = null;
//        try {
//            output = restTemplate.exchange(API_BASE_URL + "/transfer", HttpMethod.GET, makeAuthorizedEntity(), Transfer.class).getBody();
//            System.out.println("-------------------------------------------/r/n" +
//                    "Transfers/r/n" +
//                    "ID          From/To                 Amount/r/n" +
//                    "-------------------------------------------/r/n");
//            String fromTo = "";
//            String name = "";
//            for (Transfer i : output) {
//                if (currentUser.getUser().getId() == i.getAccountFrom()) {
//                    fromTo = "From: ";1
//                    name = String.valueOf(i.getAccountTo());
//                }
//                else {
//                    fromTo = "To: ";
//                    name = String.valueOf(i.getAccountFrom());
//                }
//                System.out.println(i.getId() + "/t/t" + fromTo + name + "/t/t" + i.getAmount());
//            }
//            System.out.print("-------------------------------------------/r/n" +
//                    "Transfer Details/r/n" +
//                    " Id: " +
//        }
//    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}



