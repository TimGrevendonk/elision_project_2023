import { getAllCategories } from "../../data/querries";
import Link from "next/link";

export default function CategoriesPage(props) {
  const { categories } = props;

  return (
    <div className="container m-auto">
      <h1 className="text-3xl font-bold pb-1 text-center mb-2">Categories</h1>
      <ul className="text-xl pb-1 text-center border-t-2 border-slate-700 rounded-sm">
        {categories.map((category) => (
          <Link href={`/Categories/${category.sys.id}`} key={category.sys.id}>
            <li className="p-1 border-b-2 border-slate-700 bg-slate-800 hover:bg-slate-700">
              {category.fields.title}
            </li>
          </Link>
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
