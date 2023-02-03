import React from "react";
import { render, screen, fireEvent, getByTestId } from "../test-utils";
import Home from "../../pages/index";
import Search from "../../components/search";
import { getCarouselItems, getAllCategories } from "../../data/querries";

describe("Home page", () => {
  it("should render the home page", async() => {
    const carouselItems = await getCarouselItems();
    const categories = await getAllCategories();

    const { getByText } = render(<Home
      carouselItems={carouselItems}
      categories={categories}
    />);
    
    const productCarousel = getByText("Featured items");
    const categoryCarousel = getByText("Browse by category");

    expect(productCarousel).toBeInTheDocument();
    expect(categoryCarousel).toBeInTheDocument();
  });

  // it("should be possible to search products", () => {
  //   const inputElement = render(<Search />);

  //   fireEvent.change(inputElement, { target: { value: "samsung" } });

  //   expect(getByTestId("Hits")).toBeInTheDocument();
  // });
});
