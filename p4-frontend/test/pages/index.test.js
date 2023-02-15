import React, { Component } from "react";
import { render, screen, fireEvent, getByTestId } from "../test-utils";
import Home from "../../pages";
import CategoriesPage from "../../pages/Categories";
import PrivacyPolicyPage from "../../pages/privacy-policy";
import TermsConditionsPage from "../../pages/terms-conditions";
import Search from "../../components/search";
import { getCarouselItems, getAllCategories, getPrivacyPolicy, getTermsAndConditions } from "../../data/querries";

describe("App", () => {
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

  it("should be possible to search for products", async() => {
    const { getByText } = render(<Search />);
    fireEvent.change(document.getElementById('algolia_search'), {target: {value: "OnePlus"}});

    console.log(document.querySelectorAll("ol li"));
    // expect(document.querySelectorAll("ol li")).not.toHaveLength(0);

    // expect(productCarousel).toBeInTheDocument();
    // expect(categoryCarousel).toBeInTheDocument();
  });

  it("should render the categories page", async() => {
    const categories = await getAllCategories();

    const { getByText } = render(<CategoriesPage categories={categories}/>);

    const heading = getByText("Categories");
    expect(heading).toBeInTheDocument();

    categories.forEach((category) => {
      const categoryName = getByText(category.fields.title);
      expect(categoryName).toBeInTheDocument();
    });
  });

  it("should render the privacy policy page", async() => {
    const info = await getPrivacyPolicy();

    const { getByText } = render(<PrivacyPolicyPage
      info={info}
    />);
    
    info.fields.privacyPolicyItems.forEach((item) => {
      const privacyPolicyTitle = getByText(item.fields.privacyPolicyTitle);
      const privacyTitle = getByText(item.fields.privacyTitle);
      expect(privacyPolicyTitle).toBeInTheDocument();
      expect(privacyTitle).toBeInTheDocument();
      expect(item.fields.privacyParagraph.content[0].content[0].value).toBeInTheDocument();
    });
  });

  it("should render the terms and conditions", async() => {
    const info = await getTermsAndConditions();

    const { getByText } = render(<TermsConditionsPage info={info} />);

    info.fields.termsAndConditions.forEach((item) => {
      const termsAndConditionsTitle = getByText(item.fields.termsAndConditionsTitle);
      expect(termsAndConditionsTitle).toBeInTheDocument();
    });
  });
});
