import Link from "next/link";

export default function RefusedSignUpPage() {
  return (
    <div>
      <p>Something went wrong please try again</p>
      <Link href="/sign-in">Back to sign-in page</Link>
    </div>
  );
}
