import { Fragment } from "react";
import Navigation from "./Navigation";
import Footer from "./footer";

export default function Layout(props) {
  return (
    <Fragment>
      <Navigation></Navigation>
      <main>{props.children}</main>
      <Footer></Footer>
    </Fragment>
  );
}
