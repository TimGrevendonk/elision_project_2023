export async function getAllCategories() {
  const response = await fetch(
    "https://nextjs-course-88306-default-rtdb.firebaseio.com/categories.json"
  );
  const data = await response.json();

  const categories = [];

  for (const key in data) {
    categories.push({
      id: key,
      ...data[key]
    });
  }

  return categories;
}
