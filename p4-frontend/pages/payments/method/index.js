import AdyenCheckout from "@adyen/adyen-web";
import "@adyen/adyen-web/dist/adyen.css";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { callServerPost, callServerGet } from "../../../data/Adyen_helpers";

export async function getStaticProps() {
  const paymentMethodsResponse = await callServerPost(
    "/payment/paymentMethods"
  );
  //   console.log("[debug] paymentMethodsRes", paymentMethodsResponse);

  return {
    props: {
      paymentMethodsResponse: paymentMethodsResponse,
    },
  };
}

export default function PaymentMethodPage(props) {
  const router = useRouter();

  /**
   *
   * @param response the response from the backend.
   * @param component The name of the variable where you created the instance of Drop-in.
   */
  function handleResponse(response, component) {
    if (response.action) {
      // do the action that is defined in the response action.
      component.handleAction(response.action);
    } else {
      switch (response.resultCode) {
        case "Authorised":
          console.log("[debug] handleServerResponse Authorized");
          router.push("/payment/Authorized");
          break;
        case "Pending":
        case "Received":
          console.log("[debug] handleServerResponse Pending/Received");
          router.push("/payment/pending");
          break;
        case "Refused":
          console.log("[debug] handleServerResponse Refused");
          router.push("/payment/refused");
          break;
        default:
          console.log("[debug] handleServerResponse default");
          // TODO find route
          router.push("/");
      }
    }
  }

  useEffect(() => {
    const data = async () => {
      const sessionResult = await callServerGet("/payment/session");
      console.log("[debug] session result", sessionResult);

      console.log(
        "[debug] merchantAccount",
        process.env.NEXT_PUBLIC_ADYEN_MERCHACC
      );

      const configuration = {
        environment: "test", // Change to one of the environment values specified in step 4.
        // clientKey: process.env.NEXT_PUBLIC_ADYEN_MERCHACC,
        clientKey: "test_K6Q77VJ2OZFHZO4LYQEHIXWXSQKKUFVS",
        paymentMethodsResponse: props.paymentMethodsResponse,
        analytics: {
          enabled: true, // Set to false to not send analytics data to Adyen.
        },
        session: {
          id: sessionResult.id, // Unique identifier for the payment session.
          sessionData: sessionResult.sessionData, // The payment session data.
        },
        onPaymentCompleted: (result, component) => {
          console.info(result, component);
          handleResponse(result, component);
        },
        onError: (error, component) => {
          console.error(error.name, error.message, error.stack, component);
        },
        // Any payment method specific configuration. Find the configuration specific to each payment method:  https://docs.adyen.com/payment-methods
        // For example, this is 3D Secure configuration for cards:
        paymentMethodsConfiguration: {
          card: {
            hasHolderName: false,
            holderNameRequired: false,
            billingAddressRequired: false,
          },
        },
      };

      const checkout = await AdyenCheckout(configuration);
      const dropinComponent = checkout
        .create("dropin")
        .mount("#dropin-container");
    };
    data();
  }, []);

  return (
    <>
      <h1>PaymentMethodPage</h1>
      <div id="dropin-container"></div>
    </>
  );
}
