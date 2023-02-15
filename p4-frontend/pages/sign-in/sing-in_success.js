import Link from "next/link";

export default function SuccessSignInPage() {
  return (
    <div>
      <p>You signed in successfully!</p>
      <Link href="/">Back to homepage</Link>
    </div>
  );
}
