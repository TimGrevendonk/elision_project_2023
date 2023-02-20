import { useRouter } from "next/router";
import { recoilLoggedIn } from "@/store";
import { useRecoilState } from "recoil";

export default function SuccessSignInPage() {
  const [loggedIn, setLoggedIn] = useRecoilState(recoilLoggedIn);

  const router = useRouter();

  function handleReturnToPageBeforeLogin(event) {
    window.history.go(-2);
  }

  return (
    <>
      <div className="container m-auto">
        {loggedIn && (
          <p className="pb-1 text-center">
            welcome{" "}
            <span className=" text-lg font-semibold">{loggedIn.userName}!</span>
          </p>
        )}
        <h1 className="pb-1 text-center">You signed in successfully</h1>
        <p
          className="cursor-pointer bg-slate-800 rounded-sm text-center p-1 mt-2 hover:bg-slate-600 md:w-1/2 md:m-auto"
          onClick={handleReturnToPageBeforeLogin}
        >
          return to previous page
        </p>
      </div>
    </>
  );
}
