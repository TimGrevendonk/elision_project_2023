import { getAllCategories } from "@/dummy-data";
import Link from "next/link";

export default function CategoriesPage(props) {
  const { categories } = props;

  return (
    <div>
      <h1 className="text-3xl font-bold">Categories</h1>
      <ul>
        {categories.map((category) => (
          <li key={category.id}><Link href={`/Categories/${category.id}`}>{category.name}</Link></li>
        ))}
      </ul>
    </div>
  );
}

export async function getStaticProps() {
  const categories = await getAllCategories();

  return {
    props: {
      categories: categories,
    },
    revalidate: 60,
  };
}
