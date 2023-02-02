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
    <div className="container m-auto">
      <h1 className="text-3xl font-bold">Welcome to Triple E-commerce!</h1>
      <p>The place to shop for tech products</p>
      <Search />
      <div>
        <ProductGroup
          title={carouselItems.carouselTitle}
          products={carouselItems.featuredProducts}
        />
        <CategoryGroup
          title={"Browse by category"}
          categories={categories}
        />
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
      categories: categoryList
    },
  };
}
