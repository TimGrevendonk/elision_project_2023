// import { Inter } from "@next/font/google";
import ProductGroup from "../components/layout/productGroup";
import CategoryGroup from "../components/layout/categoryGroup";
import Search from "../components/search";
import { getCarouselItems, getAllCategories } from "../data/querries";

// const inter = Inter({ subsets: ["latin"] });

export default function Home(props) {
  const carouselItems = props.carouselItems;
  const categories = props.categories;

  return (
    <div className="grid grid-cols-12">
      <h1 className="col-span-12 text-3xl font-bold text-center">
        Welcome to Triple E-commerce!
      </h1>
      <p className="col-start-2 col-end-10">
        The place to shop for tech products
      </p>
      <Search />
      <div className="col-span-12 md:col-span-8 md:col-start-3">
        <ProductGroup
          title={carouselItems.carouselTitle}
          products={carouselItems.featuredProducts}
        />
        <CategoryGroup title={"Browse by category"} categories={categories} />
      </div>
    </div>
  );
}

export async function getStaticProps() {
  const items = await getCarouselItems();
  const categoryList = await getAllCategories();

  return {
    props: {
      carouselItems: items,
      categories: categoryList,
    },
  };
}
