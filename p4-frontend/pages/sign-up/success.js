import Link from "next/link";

export default function SuccessSignUpPage() {
  function handleReturnToPageBeforeLogin(event) {
    window.history.go(-2);
  }
  return (
    <>
      <div className="container m-auto">
        <h1 className="pb-1 text-center">
          your account was created successfully!
        </h1>
        <p className="pb-1 text-center">
          Please log yourself in to confirm user creation.
        </p>
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
