import { recoilLoggedIn } from "@/store";
import { useRecoilState } from "recoil";

export default function RefusedSignUpPage() {
  const [loggedIn, setLoggedIn] = useRecoilState(recoilLoggedIn);

  function handleReturnToPreviousPage(event) {
    window.history.go(-2);
    setLoggedIn();
  }

  return (
    <div className="container m-auto">
      <h1 className="pb-1 text-center">This sign up was not accepted!</h1>
      <p
        className="cursor-pointer bg-slate-800 rounded-sm text-center p-1 mt-2 hover:bg-slate-600 md:w-1/2 md:m-auto"
        onClick={handleReturnToPreviousPage}
      >
        return to previous page
      </p>
    </div>
  );
}
