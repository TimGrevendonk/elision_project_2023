// build-search.js
const dotenv = require("dotenv");
const algoliasearch = require("algoliasearch/lite");

const client = require("contentful").createClient({
  space: "n80dqssuk9ot",
  environment: "master",
  accessToken: "oxbuUp5Nh3Rd9EV2mhBj_QFF_IkcdWsRuYagNQi0iHs",
});

async function getProducts() {
  const productsClient = [];
  await client
    .getEntries({ content_type: "product" })
    .then((response) => {
      return response.items.map((product) => {
        productsClient.push(product);
      });
    })
    .catch(console.error);
  return productsClient;
}

function transformProductsToSearchObjects(products) {
  const transformed = products.map((product) => {
    return {
      objectID: product.sys.id,
      title: product.fields.title,
      description: product.fields.description,
      price: product.fields.price,
      rating: product.fields.rating,
      ratings: product.fields.rates,
    };
  });

  return transformed;
}

(async function () {
  dotenv.config();

  try {
    const products = await getProducts();
    const transformed = transformProductsToSearchObjects(products);

    // initialize the client with your environment variables
    const client = algoliasearch(
      "EL070LM1BO",
      "28d547e1454fe21df43b2adc325c9775"
    );

    // initialize the index with your index name
    const index = client.initIndex("d1_tm_team_b3");

    // save the objects!
    const algoliaResponse = await index.saveObjects(transformed);

    // we have data ready for Algolia!
  } catch (error) {
    console.error(error);
  }
})();
