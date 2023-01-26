package fact.it.p4_backend.controller;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.*;
import com.adyen.model.nexo.PaymentResponse;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class AdyenController {
    private final Logger log = LoggerFactory.getLogger(AdyenController.class);
    @Value("${ADYEN_MERCHACC}")
    private String merchantAccount;
    private final Checkout checkout;

    public AdyenController(@Value("${ADYEN_APIKEY}") String apiKey){
        this.checkout = new Checkout(new Client(System.getenv("ADYEN_APIKEY"), Environment.TEST));
    }

    @GetMapping("/clientTest")
    public CreateCheckoutSessionResponse AdyenClient() throws IOException, ApiException {
        Client client = new Client(System.getenv("ADYEN_APIKEY"), Environment.TEST);

        Checkout checkout;
        checkout = new Checkout(client);

        CreateCheckoutSessionRequest checkoutSessionRequest = new CreateCheckoutSessionRequest();
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setValue(1000L);
        checkoutSessionRequest.setAmount(amount);
        checkoutSessionRequest.setMerchantAccount(System.getenv("ADYEN_MERCHACC"));
        checkoutSessionRequest.setReturnUrl("https://your-company.com/checkout?shopperOrder=12xy..");
        checkoutSessionRequest.setReference("YOUR_PAYMENT_REFERENCE");
        checkoutSessionRequest.setCountryCode("NL");
        CreateCheckoutSessionResponse checkoutSessionResponse = checkout.sessions(checkoutSessionRequest);

        return checkoutSessionResponse;
    }

    @PostMapping("/paymentMethods")
    public ResponseEntity<PaymentMethodsResponse> paymentMethods() throws IOException, ApiException {
        PaymentMethodsRequest paymentRequest = new PaymentMethodsRequest();
        paymentRequest.setMerchantAccount(merchantAccount);
        paymentRequest.setChannel(PaymentMethodsRequest.ChannelEnum.WEB);

        PaymentMethodsResponse response = checkout.paymentMethods(paymentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/initializePayment")
    public ResponseEntity<PaymentsResponse> payments(@RequestBody PaymentsRequest body) throws IOException, ApiException {

        PaymentsRequest paymentRequest = new PaymentsRequest();
        paymentRequest.setMerchantAccount(merchantAccount);
        paymentRequest.setChannel(PaymentsRequest.ChannelEnum.WEB);

        Amount amount = new Amount().currency("EUR").value(1000L);
        String orderRef = UUID.randomUUID().toString();
        paymentRequest.setShopperReference(orderRef);

        paymentRequest.setBrowserInfo(body.getBrowserInfo());
        paymentRequest.setPaymentMethod(body.getPaymentMethod());

        PaymentsResponse response = checkout.payments(paymentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
