import { getAllCategories, getItemById } from "@/data/querries";
import Link from "next/link";

export default function CategoryDetailPage(props) {
  const info = props.categoryInfo;
  return (
    <div className="p-3">
      <div className="bg-slate-600 h-full w-full flex justify-center">
        <h1 className="font-bold text-xl">Browsing {info.title}</h1>
      </div>
      <div className="bg-slate-400 h-full w-full flex justify-center">
        <img
          src={info.thumbnails.fields.file.url}
          className="aspect-square object-cover w-1/3 h-1/3"
        />
        <p className="p-5 my-auto">
          <span>Description:</span>
          <br />
          {info.description}
        </p>
      </div>
      <div>
        <h1 className="text-3xl font-bold py-2">Products</h1>
        <ul className="border-t-2 border-slate-700 rounded-sm">
          {info.products.map((product) => (
            <Link href={`/products/${product.sys.id}`} key={product.sys.id}>
              <li className="p-1 border-b-2 border-slate-700 bg-slate-800 hover:bg-slate-700">
                {product.fields.title} €{product.fields.price}
              </li>
            </Link>
          ))}
        </ul>
      </div>
    </div>
  );
}

export async function getStaticProps(context) {
  const categoryId = context.params.categoryId;
  const categoryInfo = await getItemById(categoryId);
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
