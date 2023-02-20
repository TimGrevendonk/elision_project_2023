import AdyenCheckout from "@adyen/adyen-web";
import "@adyen/adyen-web/dist/adyen.css";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { useRecoilState } from "recoil";
import { recoilproductsToBuy } from "/store";
import { callServerPost, callServerGet } from "../../../data/serverCallHelpers";
import { logDOM } from "@testing-library/react";

export default function PaymentMethodPage(props) {
  const [productToBuy, setProductToBuy] = useRecoilState(recoilproductsToBuy);
  const router = useRouter();

  function handleReturnPreviousPage(event) {
    router.back();
    setProductToBuy({});
  }

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
          console.info("[debug] handleServerResponse Authorized");
          router.push("/payment/Authorized");
          break;
        case "Pending":
        case "Received":
          console.info("[debug] handleServerResponse Pending/Received");
          router.push("/payment/pending");
          break;
        case "Refused":
          console.info("[debug] handleServerResponse Refused");
          router.push("/payment/refused");
          break;
        default:
          console.info("[debug] handleServerResponse default");
          // TODO find route
          router.push("/");
      }
    }
  }

  useEffect(() => {
    const data = async () => {
      const paymentMethodsResponse = await callServerPost(
        "/payment/paymentMethods"
      );
      console.log("[debug] payment methods response: ", paymentMethodsResponse);

      const sessionResult = await callServerGet(
        `/payment/session?price=${productToBuy.product.price}&quantity=${productToBuy.quantity}`
      );
      console.log("[debug] sessionresult:  ", sessionResult);

      console.log("[debug] C-key\n\n", process.env.ADYEN_CLIENT_KEY);

      const configuration = {
        environment: "test", // Change to one of the environment values specified in step 4.
        clientKey: process.env.ADYEN_CLIENT_KEY,
        // clientKey: "nah",
        // disabled payment methods for the user (all).
        paymentMethodsResponse: paymentMethodsResponse,
        analytics: {
          enabled: false, // Set to false to not send analytics data to Adyen.
        },
        session: {
          id: sessionResult.id, // Unique identifier for the payment session.
          sessionData: sessionResult.sessionData, // The payment session data.
        },
        onPaymentCompleted: (result, component) => {
          console.info("[debug] info", result, component);
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
            blockedPaymentMethods: ["ideal", "bcmc", "bcmc_mobile"],
          },
        },
      };

      const checkout = await AdyenCheckout(configuration);
      const dropinComponent = checkout
        .create("dropin")
        .mount("#dropin-container");
    };
    data();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="container m-auto">
      <h1 className="pb-1 text-center">Transact your payment here</h1>
      <ol className=" bg-slate-800 rounded-lg p-2 my-2">
        <h2 className="text-xl">Product info</h2>
        <li>
          <div className=" font-medium">Product full name:</div>
          {productToBuy.product ? (
            <div className=" text-slate-300">{productToBuy.product.title}</div>
          ) : (
            <div className=" font-extralight text-red-300">
              no product title found !
            </div>
          )}
        </li>
        <li>
          <div className=" font-medium">Individual Product Price:</div>
          {productToBuy.product ? (
            <div className=" text-slate-300">
              € {productToBuy.product.price}
            </div>
          ) : (
            <div className=" font-extralight text-red-300">
              no product price found !
            </div>
          )}
        </li>
        <li>
          <div className=" font-medium">Product quantity:</div>
          {productToBuy.quantity ? (
            <div className=" text-slate-300">{productToBuy.quantity} items</div>
          ) : (
            <div className=" font-extralight text-red-300">
              no product quantity found !
            </div>
          )}
        </li>
      </ol>
      {productToBuy.product && productToBuy.quantity ? (
        // the adyen payment card box.
        <div id="dropin-container"></div>
      ) : (
        <p
          className="cursor-pointer bg-slate-800 rounded-sm text-center p-1 mt-2 hover:bg-slate-600 md:w-1/2 md:m-auto text-red-300"
          onClick={handleReturnPreviousPage}
        >
          Error: return to previous page
        </p>
      )}
    </div>
  );
}
