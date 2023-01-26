import { getAllCategories, getItemById } from "@/data/querries";
import Link from "next/link";

export default function CategoryDetailPage(props) {
  const info = props.categoryInfo;
  console.log(info);
  console.log(info.products);
  return (
    <div className="p-3">
      <div className="bg-slate-600 h-full w-full flex justify-center">
        <h1 className="font-bold text-xl">Browsing {info.title}</h1>
      </div>
      <div className="bg-slate-400 h-full w-full flex justify-center">
        <img src="/1.jpg" className="aspect-square object-cover w-1/3 h-1/3" />
        <p className="p-5">
          <span>Description:</span>
          <br />
          {info.description}
        </p>
      </div>
      <div>
        <h1 className="text-3xl font-bold">Products</h1>
        <ul>
          {info.products.map((product) => (
            <li key={product.sys.id}>
              <Link href={`/products/${product.sys.id}`}>
                {product.fields.title}
              </Link>
            </li>
          ))}
        </ul>
        {/* <ProductList props={info.products} /> */}
      </div>
    </div>
  );
}

export async function getStaticProps(context) {
  const categoryId = context.params.categoryId;
  const categoryInfo = await getItemById(categoryId);
  console.log("category info:", categoryInfo);

  return {
    props: {
      categoryID: categoryId,
      categoryInfo: categoryInfo,
    },
  };
}

export async function getStaticPaths() {
  const categories = await getAllCategories();
  const paths = categories.map((category) => ({
    params: { categoryId: category.sys.id },
  }));

  return {
    paths: paths,
    fallback: false,
  };
}
