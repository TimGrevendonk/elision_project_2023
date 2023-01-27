import AdyenCheckout from "@adyen/adyen-web";
import "@adyen/adyen-web/dist/adyen.css";
import { callServer as callServerhelper } from "../../../data/Adyen_helpers";

export async function getStaticProps() {
  const paymentMethodsResponse = await callServerhelper(
    "/payment/paymentMethods"
  );

  console.log("[debug] paymentMethodsRes", paymentMethodsResponse);

  const configuration = {
    environment: "test", // Change to one of the environment values specified in step 4.
    clientKey: "test_870be2...",
    paymentMethodsResponse: paymentMethodsResponse,
    analytics: {
      enabled: true, // Set to false to not send analytics data to Adyen.
    },
    session: {
      id: "CSD9CAC3...", // Unique identifier for the payment session.
      sessionData: "Ab02b4c...", // The payment session data.
    },
    onsubmit: (state, component) => {
      if (result.isValid) {
        handleSubmission(result, component, "/payment/initiatePayment");
      }
    },
    onAdditionalDetails: (result, component) => {
      console.info(result, component);
      handleSubmission(result, component, "/payment/additionalDetails");
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
  console.log("[debug]", configuration);

  //   TODO get the froms to load, in the serverside props it cannotn find the document and in de main function cannot do async/await.
  const checkout = await AdyenCheckout(configuration);
  const dropinComponent = checkout.create("dropin").mount("#dropin-container");

  return {
    props: {
      paymentMethodsResponse: paymentMethodsResponse,
      checkout: checkout,
      configuration: configuration,
      dropinComponent: dropinComponent,
    },
  };
}

export default function PaymentMethodPage(props) {
  async function handleSubmission(state, component, url) {
    try {
      let response = await callServerhelper(url, state.data);
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
      // do the action that is defined in the response action.
      component.handleAction(response.action);
    } else {
      switch (response.resultCode) {
        case "Authorized":
          console.log("[debug] handleServerResponse Authorized");
          window.location.href = "/";
          break;
        case "Pending":
        case "Received":
          console.log("[debug] handleServerResponse Pending/Received");
          window.location.href = "/about";
          break;
        case "Refused":
          console.log("[debug] handleServerResponse Refused");
          window.location.href = "/about";
          break;
        default:
          console.log("[debug] handleServerResponse default");
          window.location.href = "/about";
          break;
      }
    }
  }

  //   console.log("[debug] props", props.checkout);

  return (
    <>
      <h1>PaymentMethodPage</h1>
      <div id="dropin-container"></div>
    </>
  );
}
