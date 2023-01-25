import Head from "next/head";
import Image from "next/image";
import { Inter } from "@next/font/google";
import styles from "@/styles/Home.module.css";
import ProductGroup from "@/components/layout/productGroup";
import Search from "@/components/search";

import { items, items2 } from "../public/Items.json";
import { Fragment } from "react";

const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  return (
    <Fragment>
      <Search />
      <div className="grid grid-cols-2">
        <ProductGroup title="For you" products={items} />
        <ProductGroup title="Deals" products={items2} />
      </div>
    </Fragment>
  );
}
