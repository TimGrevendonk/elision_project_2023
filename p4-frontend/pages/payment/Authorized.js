import { useRecoilState } from "recoil";
import { recoilproductsToBuy } from "/store";
import { useRouter } from "next/router";

export default function AuthorizedPage() {
  const [productToBuy, setProductToBuy] = useRecoilState(recoilproductsToBuy);
  const router = useRouter();

  function handleReturnHomePage(event) {
    setProductToBuy({});
    router.push("/");
  }

  return (
    <div className="container m-auto">
      <h1 className="pb-1 text-center">Thank you for buying!</h1>
      {productToBuy.product && (
        <ol className=" bg-slate-800 rounded-lg p-2 my-2">
          <li>
            <div className=" font-medium">You bought</div>
            <div className=" text-slate-300">{productToBuy.product.title}</div>
          </li>
          <li>
            <div className=" font-medium">Product Price:</div>
            <div className=" text-slate-300">
              € {productToBuy.product.price}
            </div>
          </li>
          <li>
            <div className=" font-medium">Product quantity:</div>
            <div className=" text-slate-300">{productToBuy.quantity} items</div>
          </li>
        </ol>
      )}
      <p
        className="cursor-pointer bg-slate-800 rounded-sm text-center p-1 mt-2 hover:bg-slate-600 md:w-1/2 md:m-auto"
        onClick={handleReturnHomePage}
      >
        return to home page
      </p>
    </div>
  );
}
