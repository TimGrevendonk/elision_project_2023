const contentful = require("contentful");

export const client = contentful.createClient({
  space: "n80dqssuk9ot",
  environment: "master", // defaults to 'master' if not set
  accessToken: "oxbuUp5Nh3Rd9EV2mhBj_QFF_IkcdWsRuYagNQi0iHs",
});

export async function getProducts() {
  const productsClient = [];
  await client
    .getEntries({ content_type: "product" })
    .then((response) => {
      return response.items.map((product) => {
        console.log(`productId: ${product.sys.id}`),
          productsClient.push(product.sys.id);
      });
    })
    .catch(console.error);
  return productsClient;
}

export async function getProductById(id) {
  const info = client
    .getEntry(id)
    .then((entry) => {
      return entry.fields;
    })
    .catch(console.error);

  return info;
}
