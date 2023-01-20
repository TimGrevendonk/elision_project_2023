import Link from "next/link";

export default function CategoriesPage(props) {
  const { categories } = props;

  return (
    <div>
      <h1 className="text-3xl font-bold">Categories</h1>
      <ul>
        {categories.map((category) => (
          <li key={category.sys.id}>
            <Link href={`/Categories/${category.sys.id}`}>{category.fields.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

const client = require("contentful").createClient({
  space: process.env.CONTENTFUL_SPACE_ID,
  accessToken: process.env.CONTENTFUL_ACCESS_TOKEN,
});

export async function getStaticProps() {
  const categories = await client.getEntries({content_type: 'category'});
  return {
    props: {
      categories: categories.items,
    }
  };
}
