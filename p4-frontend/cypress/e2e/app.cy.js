describe("Search", () => {
    it("should return all the possible hits for a query", () => {
      // Start from the index page
      cy.visit("http://localhost:3000/");
  
      // Type 'sam' in the search input field and check that the adjacent list is not empty 
      cy.get('input#algolia_search').type('sam');
      cy.get('form').siblings('ol').should('not.be.empty');
    });
  });

describe("About us page", () => {
  it("should navigate to the about page", () => {
    // Start from the index page
    cy.visit("http://localhost:3000/");

    // Find a link with an href attribute containing "about" and click it
    cy.get('a[href*="about"]').click();

    // The new url should include "/about"
    cy.url().should("include", "/about");

    // The new page should contain an h1 with "About page"
    cy.get("h1").contains("About us");
  });
});

describe("Categories page", () => {
  it("should navigate to the categories page and find all categories", () => {
    // Start from the index page
    cy.visit("http://localhost:3000/");

    // Find a link with an href attribute containing "about" and click it
    cy.get('a[href*="Categories"]').click();

    // The new url should include "/about"
    cy.url().should("include", "/Categories");

    // The new page should contain an h1 with "Categories" and all respective categories
    cy.get("h1").contains("Categories").siblings("ul").should("not.be.empty");
  });
});

describe("(Category detail) Products page", () => {
  it("should navigate to the smartphones category detail page and find all products", () => {
    // Start from the Categories page
    cy.visit("http://localhost:3000/Categories");

    // Find a link with an href attribute containing "Smartphones" and click it
    cy.contains("Smartphones").click();

    // The new url should include "/about"
    cy.url().should("include", "/7gHZfAyjSQjGyhJPetTsvu");

    // The new page should contain an h1 with "Categories" and all respective categories
    cy.get("h1").contains("Browsing Smartphones");
    cy.get("p").contains(
      "Need a new phone? This is where you can find all smartphones in our stock."
    );
    cy.get("h1").contains("Products").siblings("ul").should("not.be.empty");
  });
});
