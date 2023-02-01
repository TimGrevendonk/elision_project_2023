import Link from "next/link";

export default function ProductList({ props }) {
  const { items } = props;
  return (
    <ul>
      {items.map((item) => (
        <li key={item.sys.id}>
          <Link href={`/products/${item.sys.id}`}>{item.fields.title}</Link>
        </li>
      ))}
    </ul>
  );
}
