const client = require("contentful").createClient({
  space: process.env.CONTENTFUL_SPACE_ID,
  environment: "master",
  accessToken: process.env.CONTENTFUL_ACCESS_TOKEN,
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

export async function getAllCategories() {
  const categoryClient = [];
  await client
    .getEntries({ content_type: "category" })
    .then((response) => {
      return response.items.map((category) => {
        console.log(`category ${category}`), categoryClient.push(category);
      });
    })
    .catch(console.error);
  return categoryClient;
}

export async function getCategoryById(id) {
  const info = client
    .getEntry(id)
    .then((entry) => {
      return entry.fields;
    })
    .catch(console.error);

  return info;
}

export async function getAboutPageInfo() {
  const aboutUsInfo = await client
    .getEntry("3XDWTQ6OUSaoMUIoT9243u")
    .then((response) => {
      console.log(response);
      return response.fields;
    })
    .catch(console.error);
  return aboutUsInfo;
}
