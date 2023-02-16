export default function ForgotPasswordPage() {
  return (
    <section className="   bg-gray-900">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <div className="w-full p-6 bg-white rounded-lg shadow  border md:mt-0 sm:max-w-md  bg-gray-800  border-gray-700 sm:p-8">
          <h1 className="mb-1 text-xl font-bold leading-tight tracking-tight  md:text-2xl  text-white">
            Forgot your password?
          </h1>
          <p className="font-light    text-gray-400">
            Enter your email below to reset your password.
          </p>
          <form
            className="mt-4 space-y-4 lg:mt-5 md:space-y-5"
            onSubmit={submitHandler}
          >
            <div>
              <label
                htmlFor="email"
                className="block mb-2 text-sm font-medium   text-white"
              >
                Your email
              </label>
              <input
                type="email"
                name="email"
                id="email"
                className="  border    sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  bg-gray-700  border-gray-600  placeholder-gray-400  text-white  focus:ring-blue-500  focus:border-blue-500"
                placeholder="name@company.com"
                required=""
              />
            </div>
            <button
              type="submit"
              className="bg-blue-700 hover:bg-blue-800 text-white font-bold py-2 px-4 rounded text-center w-full"
            >
              Reset password
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}

function submitHandler(event) {
  event.preventDefault();
}
