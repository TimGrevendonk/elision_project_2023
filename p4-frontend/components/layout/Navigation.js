import Link from "next/link";
import Search from "../search";
import { useRouter } from "next/router";
import { useRecoilState } from "recoil";
import { recoilLoggedIn } from "@/store";
export default function Navigation() {
  const [loggedIn, setLoggedIn] = useRecoilState(recoilLoggedIn);
  const router = useRouter();

  function logOutHandler() {
    if (router.pathname == "/payment/method") {
      router.back();
    }
    setLoggedIn();
  }
  return (
    <nav className="relative w-full flex flex-wrap items-center justify-between py-3 bg-gray-900 text-gray-200 shadow-lg navbar navbar-expand-lg navbar-light border-gray-800 rounded border-b-2">
      <div className="container flex flex-wrap items-center justify-between mx-auto">
        <Link href="/" className="flex items-center m-1">
          <img
            src="/images/elision.png"
            alt="elision logo"
            className="w-40 h-20"
          ></img>
        </Link>
        <div className="flex-grow items-center">
          <Search />
        </div>
        <button
          className="navbar-toggler text-gray-200 border-0 hover:shadow-none hover:no-underline py-2 px-2.5 bg-transparent focus:outline-none focus:ring-0 focus:shadow-none focus:no-underline"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent1"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <svg
            aria-hidden="true"
            focusable="false"
            data-prefix="fas"
            data-icon="bars"
            className="w-6"
            role="img"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 448 512"
          >
            <path
              fill="currentColor"
              d="M16 132h416c8.837 0 16-7.163 16-16V76c0-8.837-7.163-16-16-16H16C7.163 60 0 67.163 0 76v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm0 160h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16z"
            ></path>
          </svg>
        </button>
        <div
          className="collapse navbar-collapse flex-grow items-center"
          id="navbarSupportedContent1"
        >
          <ul className="navbar-nav flex flex-col pl-0 list-style-none mr-auto">
            <li className="nav-item p-2">
              <Link
                className="nav-link text-white opacity-60 hover:opacity-80 focus:opacity-80 p-0"
                href="/about"
              >
                About
              </Link>
            </li>
            <li className="nav-item p-2">
              <Link
                className="nav-link text-white opacity-60 hover:opacity-80 focus:opacity-80 p-0"
                href="/Categories"
              >
                Categories
              </Link>
            </li>
            <li className="nav-item p-2">
              {!loggedIn && (
                <>
                  <button
                    className="mx-4 nav-link bg-blue-500 hover:bg-blue-600 text-white font-bold py-4 rounded text-center w-full"
                    onClick={() => router.push("/sign-in")}
                  >
                    log in
                  </button>
                </>
              )}
              {loggedIn && (
                <>
                  <button
                    className="mx-4 nav-link bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 rounded text-center w-full"
                    onClick={logOutHandler}
                  >
                    log out
                    <p className=" text-xs">
                      {loggedIn.userMail.length > 30
                        ? loggedIn.userMail
                        : loggedIn.userMail.slice(0, 17) + "..."}
                    </p>
                  </button>
                </>
              )}
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
