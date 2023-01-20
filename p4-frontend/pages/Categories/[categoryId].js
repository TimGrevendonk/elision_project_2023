import { getCategoryById } from "@/dummy-data";
import { useRouter } from "next/router";

export default function CategoryProducts() {
  const router = useRouter();

  const categoryId = router.query.categoryId;
  const category = getCategoryById(categoryId);

  if (!category) {
    return (
      <div>
        <h1 className="text-3xl font-bold">Not found</h1>
        <p>Category unavailable</p>
      </div>
    );
  }

  return (
    <div>
      <h1 className="text-3xl font-bold">Browsing {category.name}</h1>
      <p>{category.description}</p>
    </div>
  );
}
