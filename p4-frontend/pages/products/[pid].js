/* eslint-disable react/no-unescaped-entities */
import { getProductById, getProducts } from "@/data/querries";
export default function ProductDetailPage(props) {
  const id = props.productID;
  const info = props.productInfo;
  console.log(info);
  return (
    <div className="p-3">
      <div className="bg-slate-600 h-full w-full flex justify-center">
        <h1 className="font-bold text-xl">
          {id}: {info.title}
        </h1>
      </div>
      <div className="bg-slate-400 h-full w-full flex justify-center">
        <img src="/1.jpg" className="aspect-square object-cover w-1/3 h-1/3" />
        <p className="p-5">
          <span>Description:</span>
          <br />
          {info.description}
        </p>
      </div>
    </div>
  );
}

export async function getStaticProps(context) {
  const productId = context.params.pid;
  const productInfo = await getProductById(productId);
  console.log("product info:", productInfo);

  return {
    props: {
      productID: productId,
      productInfo: productInfo,
    },
  };
}

export async function getStaticPaths() {
  const products = await getProducts();

  const paths = products.map((product) => ({ params: { pid: product } }));
  return {
    paths,
    fallback: false,
  };
}
