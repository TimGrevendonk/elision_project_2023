/* eslint-disable react/no-unescaped-entities */
import { getItemById, getProducts } from "@/data/querries";
import { recoilproductsToBuy, recoilLoggedIn } from "../../store";
import { useRouter } from "next/router";
import { useState } from "react";
import Link from "next/link";
import { useRecoilState } from "recoil";

export default function ProductDetailPage(props) {
  const info = props.productInfo;
  const id = props.productID;
  const [quantity, setQuantity] = useState(1);
  const [productToBuy, setProductToBuy] = useRecoilState(recoilproductsToBuy);
  const [loggedIn, setLoggedIn] = useRecoilState(recoilLoggedIn);
  const router = useRouter();

  function quantityUp() {
    setQuantity(quantity + 1);
  }
  function quantityDown() {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    } else {
      setQuantity(1);
    }
  }

  function handleGoToLogin() {
    router.push("/sign-in");
  }

  function buyHandler(quantity) {
    setProductToBuy({ product: info, quantity: quantity });
    router.push({ pathname: "/payment/method" });
  }

  return (
    <div className="p-3">
      <div className="bg-slate-600  flex justify-center">
        <h1 className="font-bold text-xl p-2">{info.title}</h1>
      </div>
      <div className="bg-slate-400 flex justify-center">
        {info.thumbnails.map((image) => (
          <img
            src={image.fields.file.url}
            key={image.fields.file.url}
            className="aspect-square object-cover w-1/3 h-1/3"
          />
        ))}
        <p className="p-5">€&nbsp;{info.price.toFixed(2)}</p>
        <p className="p-5">
          Description:
          <br />
          {info.description}
        </p>
      </div>
      <div className="w-full sm:w-1/2 lg:w-1/3 mt-1">
        {loggedIn && (
          <>
            <label className="w-full text-white text-sm font-semibold">
              Quantity
            </label>
            <div className="flex flex-row h-10 w-full rounded-lg relative bg-transparent mt-1">
              <button
                onClick={quantityDown}
                className=" bg-gray-300 text-gray-600 hover:text-gray-700 hover:bg-gray-400 h-full w-20 rounded-l cursor-pointer outline-none"
              >
                -
              </button>
              <span className="outline-none focus:outline-none text-center w-full bg-gray-300 font-semibold text-md hover:text-black focus:text-black  md:text-basecursor-default flex items-center text-gray-700 justify-center">
                {quantity}
              </span>
              <button
                onClick={quantityUp}
                className="bg-gray-300 text-gray-600 hover:text-gray-700 hover:bg-gray-400 h-full w-20 rounded-r cursor-pointer"
              >
                +
              </button>
            </div>
            <div>
              <button
                onClick={() => buyHandler(quantity)}
                className="bg-slate-500 rounded w-full hover:bg-slate-600 hover:text-gray-300"
              >
                BUY NOW
              </button>
            </div>
          </>
        )}
        {!loggedIn && (
          <div>
            <button
              onClick={handleGoToLogin}
              className="bg-slate-500 rounded w-full hover:bg-slate-600 hover:text-gray-300"
              href="/sign-in"
            >
              log in to Buy this product!
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export async function getStaticProps(context) {
  const productId = context.params.pid;
  const productInfo = await getItemById(productId);

  return {
    props: {
      productID: productId,
      productInfo: productInfo,
    },
    // for demo purposes:
    revalidate: 6,
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
