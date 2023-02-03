import { getAllCategories } from "@/data/querries";
import Link from "next/link";

export default function CategoriesPage(props) {
  const { categories } = props;

  return (
    <div>
      <h1 className="text-3xl font-bold">Categories</h1>
      <ul>
        {categories.map((category) => (
          <li key={category.sys.id}>
            <Link href={`/Categories/${category.sys.id}`}>
              {category.fields.title}
            </Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export async function getStaticProps() {
  const categoryList = await getAllCategories();
  return {
    props: {
      categories: categoryList,
    },
  };
}
