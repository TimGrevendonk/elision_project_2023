import React from "react";
import { render, screen, fireEvent, getByTestId } from "../test-utils";
import Home from "../../pages/index";
import Search from "../../components/search";

describe("Home page", () => {
  it("should render the home page", () => {
    render(<Home />);
    const heading1 = screen.getByText("For you");
    const heading2 = screen.getByText("Deals");

    expect(heading1).toBeInTheDocument();
    expect(heading2).toBeInTheDocument();
  });

  it("should be possible to search products", () => {
    render(<Home />);
    const inputElement = render(<Search />);

    fireEvent.change(inputElement, { target: { value: "samsung" } });

    expect(getByTestId("Hits")).toBeInTheDocument();
  });
});
