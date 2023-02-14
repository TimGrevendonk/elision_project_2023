import { Fragment } from "react";
import Navigation from "./Navigation";
import Footer from "./footer";

export default function Layout(props) {
  return (
    <div className="flex flex-col h-screen justify-between">
      <Navigation></Navigation>
      <main>{props.children}</main>
      <Footer></Footer>
    </div>
  );
}
