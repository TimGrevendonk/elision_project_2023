import { Inter } from "@next/font/google";
import ProductGroup from "@/components/layout/productGroup";
import Search from "@/components/search";

import { items, items2 } from "../public/Items.json";
import { Fragment } from "react";

const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  return (
    <div className="container m-auto">
      <Search />
      <div className="grid grid-cols-2">
        <ProductGroup title="For you" products={items} />
        <ProductGroup title="Deals" products={items2} />
      </div>
    </div>
  );
}
