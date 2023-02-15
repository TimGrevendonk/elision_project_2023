import { recoilproductsToBuy } from "/store";
import { useRouter } from "next/router";
import { useRecoilState } from "recoil";

export default function RefusedPage() {
  const [productToBuy, setProductToBuy] = useRecoilState(recoilproductsToBuy);
  const router = useRouter();

  function handleReturnHomePage(event) {
    router.back();
    router.back();
    setProductToBuy({});
  }

  return (
    <div className="container m-auto">
      <h1 className="pb-1 text-center">The transaction was not accepted!</h1>
      <p
        className="cursor-pointer bg-slate-800 rounded-sm text-center p-1 mt-2 hover:bg-slate-600 md:w-1/2 md:m-auto"
        onClick={handleReturnHomePage}
      >
        return to previous page
      </p>
    </div>
  );
}
