import "@/styles/globals.css";
import Layout from "@/components/layout/layout";
import { RecoilRoot } from "recoil";
import { useEffect } from "react";

export default function App({ Component, pageProps }) {
  useEffect(() => {
    const use = async () => {
      (await import("tw-elements")).default;
    };
    use();
  }, []);
  return (
    <RecoilRoot>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </RecoilRoot>
  );
}
