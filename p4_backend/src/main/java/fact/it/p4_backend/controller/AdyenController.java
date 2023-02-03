package fact.it.p4_backend.controller;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.*;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/payment")
public class AdyenController {
    private final Checkout checkout;

    private final String adyenApiKey=System.getenv("ADYEN_APIKEY");
    private final String adyenMerchantAccount=System.getenv("ADYEN_MERCHANT_ACCOUNT");

    public AdyenController() {
        this.checkout = new Checkout(new Client(getAdyenApiKey(), Environment.TEST));
    }

    public Checkout getCheckout() { return checkout; }

    public String getAdyenApiKey() { return adyenApiKey; }

    public String getAdyenMerchantAccount() { return adyenMerchantAccount; }

    /**
     * Get session for the adyen payments
     *
     * @return response wit the session.
     * @throws IOException  Input/output exception.
     * @throws ApiException Api call error.
     */
    @GetMapping("/session")
    public ResponseEntity<CreateCheckoutSessionResponse> AdyenClient(Double price, Integer quantity) throws IOException, ApiException {
        CreateCheckoutSessionRequest checkoutSessionRequest = new CreateCheckoutSessionRequest();

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setValue((long) (price * quantity * 100));

        checkoutSessionRequest.setAmount(amount);
        checkoutSessionRequest.setMerchantAccount(getAdyenMerchantAccount());
        checkoutSessionRequest.setReturnUrl("https://localhost:3000/");
        checkoutSessionRequest.setReference("YOUR_PAYMENT_REFERENCE");
        checkoutSessionRequest.setCountryCode("NL");
        CreateCheckoutSessionResponse checkoutSessionResponse = getCheckout().sessions(checkoutSessionRequest);

        return new ResponseEntity<>(checkoutSessionResponse, HttpStatus.CREATED);
    }

    /**
     * get all the hard coded payment methods connected to the merchant.
     *
     * @return response wit the payment methods.
     * @throws IOException  Input/output exception.
     * @throws ApiException Api call error.
     */
    @PostMapping("/paymentMethods")
    public ResponseEntity<PaymentMethodsResponse> paymentMethods() throws IOException, ApiException {
        PaymentMethodsRequest paymentRequest = new PaymentMethodsRequest();
        paymentRequest.setMerchantAccount(getAdyenMerchantAccount());
        paymentRequest.setChannel(PaymentMethodsRequest.ChannelEnum.WEB);

        PaymentMethodsResponse response = getCheckout().paymentMethods(paymentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/initializePayment")
    public ResponseEntity<PaymentsResponse> initPayments(@RequestBody PaymentsRequest body) throws IOException, ApiException {

        PaymentsRequest paymentRequest = new PaymentsRequest();
        paymentRequest.setMerchantAccount(getAdyenMerchantAccount());
        paymentRequest.setChannel(PaymentsRequest.ChannelEnum.WEB);

        Amount amount = new Amount().currency("EUR").value(1000L);
        String orderRef = UUID.randomUUID().toString();
        paymentRequest.setShopperReference(orderRef);
        paymentRequest.setReturnUrl("http://localhost:8080/payment/redirect,orderRef=" + orderRef);

        paymentRequest.setBrowserInfo(body.getBrowserInfo());
        paymentRequest.setPaymentMethod(body.getPaymentMethod());

        PaymentsResponse response = getCheckout().payments(paymentRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public ResponseEntity<PaymentsDetailsRequest> redirect(@RequestParam(required = false) String redirectResult, @RequestParam(required = false) String orderRef) throws IOException, ApiException {
        PaymentsDetailsRequest detailsRequest = new PaymentsDetailsRequest();
        if (redirectResult != null && !redirectResult.isEmpty()) {
            detailsRequest.setDetails(Collections.singletonMap("redirectResult", redirectResult));
        }
//        TODO: fill orderRef with database querry of the order.
        detailsRequest.setPaymentData(orderRef);

        return new ResponseEntity<>(detailsRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/redirect", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<PaymentsDetailsRequest> redirect(@RequestParam("MD") String md, @RequestParam("PaRes") String paRes, @RequestParam String orderRef) throws IOException, ApiException {
        PaymentsDetailsRequest detailsRequest = new PaymentsDetailsRequest();

//        TODO: fill orderRef with database querry of the order.
        detailsRequest.setPaymentData(orderRef);

        return new ResponseEntity<>(detailsRequest, HttpStatus.OK);
    }
}
