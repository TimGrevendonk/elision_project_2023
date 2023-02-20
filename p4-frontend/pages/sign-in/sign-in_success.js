import { useRouter } from "next/router";

export default function SuccessSignInPage() {
  const router = useRouter();

  function handleReturnToPageBeforeLogin(event) {
    // router.back();
    window.history.go(-2);
  }

  return (
    <>
      <div className="container m-auto">
        <h1 className="pb-1 text-center">You signed in successfully!</h1>
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
