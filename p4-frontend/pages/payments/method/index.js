import AdyenCheckout from "@adyen/adyen-web";
import "@adyen/adyen-web/dist/adyen.css";

export default function PaymentMethodPage() {
  const configuration = {
    environment: "test", // Change to one of the environment values specified in step 4.
    clientKey: "test_870be2...", // Public key used for client-side authentication: https://docs.adyen.com/development-resources/client-side-authentication
    analytics: {
      enabled: true, // Set to false to not send analytics data to Adyen.
    },
    session: {
      id: "CSD9CAC3...", // Unique identifier for the payment session.
      sessionData: "Ab02b4c...", // The payment session data.
    },
    onsubmit: (state, component) => {},
    onPaymentCompleted: (result, component) => {
      console.info(result, component);
      if (result.isValid) {
        handleSubmission(result, component, "/payment/initiatePayment");
      }
    },
    onError: (error, component) => {
      console.error(error.name, error.message, error.stack, component);
    },
    // Any payment method specific configuration. Find the configuration specific to each payment method:  https://docs.adyen.com/payment-methods
    // For example, this is 3D Secure configuration for cards:
    paymentMethodsConfiguration: {
      card: {
        hasHolderName: true,
        holderNameRequired: true,
        billingAddressRequired: true,
      },
    },
  };

  const checkout = AdyenCheckout(configuration);
  const dropinComponent = checkout.create("dropin").mount("#dropin-container");

  async function callServer(url, data) {
    const response = await fetch(url, {
      method: "POST",
      body: data ? JSON.stringify(data) : "",
      headers: {
        "Content-Type": "application/json",
      },
    });
    return await response.json();
  }

  async function handleSubmission(state, component, url) {
    try {
      let response = await callServer(url, state.data);
      handleServerResponse(response, component);
    } catch (error) {
      console.error(console.error);
    }
  }
  /**
   *
   * @param response the response from the backend.
   * @param component
   */
  function handleServerResponse(response, component) {
    if (response.action) {
      component.handleAction(response.action);
    } else {
    }
  }
  return (
    <>
      <h1>PaymentMethodPage</h1>
      <div id="dropin-container">{dropinComponent}</div>
    </>
  );
}
