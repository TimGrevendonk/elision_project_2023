import { Fragment } from "react";
import Navigation from "./Navigation";

export default function Layout(props) {
  return (
    <Fragment>
      <Navigation></Navigation>
      <main>{props.children}</main>
    </Fragment>
  );
}
