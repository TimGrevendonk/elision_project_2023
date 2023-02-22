import { callServerPostNoJson } from "@/data/serverCallHelpers";
import { recoilLoggedIn } from "@/store";
import Link from "next/link";
import { useRouter } from "next/router";
import { useRecoilState } from "recoil";

export default function SingInPage() {
  const router = useRouter();
  const [loggedIn, setLoggedIn] = useRecoilState(recoilLoggedIn);

  async function submitHandler(event) {
    event.preventDefault();
    const loginData = {
      mail: event.target.email.value,
      password: event.target.password.value,
    };
    const response = await callServerPostNoJson("/api/user/sign-in", loginData);

    if (response.responseStatus.status == 200) {
      setLoggedIn(response.userData);
      router.push("/sign-in/sign-in_success");
    } else {
      router.push("/sign-in/sign-in_refused");
    }
  }
  return (
    <section className=" bg-gray-900">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <div className="w-full  rounded-lg shadow  border md:mt-0 sm:max-w-md xl:p-0  bg-gray-800  border-gray-700">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 className="text-xl font-bold leading-tight tracking-tight  md:text-2xl  text-white text-center">
              Sign in to your account
            </h1>
            <p className="p-2 bg-slate-900 rounded-md border border-red-700">
              If the sql is connected in the backend and the backend is running:
              <br />
              mail: &quot;TimGrevendonk@mail.com&quot;
              <br />
              password: &quot;tim&quot;
            </p>
            <form className="space-y-4 md:space-y-6" onSubmit={submitHandler}>
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
                  className=" border   sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  bg-gray-700  border-gray-600  placeholder-gray-400  text-white  focus:ring-blue-500  focus:border-blue-500"
                  placeholder="name@company.com"
                  required=""
                />
              </div>
              <div>
                <label
                  htmlFor="password"
                  className="block mb-2 text-sm font-medium   text-white"
                >
                  Password
                </label>
                <input
                  type="password"
                  name="password"
                  id="password"
                  placeholder="••••••••"
                  className="  border    sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5  bg-gray-700  border-gray-600  placeholder-gray-400  text-white  focus:ring-blue-500  focus:border-blue-500"
                  required=""
                />
              </div>
              {/* <div className="flex items-center justify-between">
                <div className="flex items-start"></div>
                <Link
                  href="/sign-in/forgot-password"
                  className="text-sm font-medium text-primary-600 hover:underline  text-primary-500"
                >
                  Forgot password?
                </Link>
              </div> */}
              <button
                type="submit"
                className="bg-blue-700 hover:bg-blue-800 text-white font-bold py-2 px-4 rounded text-center w-full"
              >
                Sign in
              </button>
              <p className="text-sm font-light    text-gray-400">
                Need an account?{" "}
                <Link
                  href="/sign-up"
                  className="font-medium text-primary-600 hover:underline  text-primary-500"
                >
                  Sign up here
                </Link>
              </p>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
}
