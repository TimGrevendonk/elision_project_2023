package fact.it.p4_backend.controller;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.CreateCheckoutSessionRequest;
import com.adyen.model.checkout.CreateCheckoutSessionResponse;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/payment")
public class AdyenController {

    @GetMapping("/client")
    public ResponseEntity<CreateCheckoutSessionResponse> AdyenClient() throws IOException, ApiException {
        Client client = new Client(System.getenv("ADYEN_APIKEY"), Environment.TEST);

        Checkout checkout;
        checkout = new Checkout(client);

        CreateCheckoutSessionRequest checkoutSessionRequest = new CreateCheckoutSessionRequest();
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setValue(1000L);
        checkoutSessionRequest.setAmount(amount);
        checkoutSessionRequest.setMerchantAccount(System.getenv("ADYEN_CLIENTKEY"));
        checkoutSessionRequest.setReturnUrl("https://your-company.com/checkout?shopperOrder=12xy..");
        checkoutSessionRequest.setReference("YOUR_PAYMENT_REFERENCE");
        checkoutSessionRequest.setCountryCode("NL");
        CreateCheckoutSessionResponse checkoutSessionResponse = checkout.sessions(checkoutSessionRequest);

        return new ResponseEntity<>(checkoutSessionResponse, HttpStatus.OK);
    }

}
