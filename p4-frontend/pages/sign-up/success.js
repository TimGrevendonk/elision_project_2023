import Link from "next/link";

export default function SuccessSignUpPage() {
  return (
    <div>
      <p>your account was created successfully!</p>
      <p>Please log yourself in to confirm user creation.</p>
      <Link href="/">Back to homepage</Link>
    </div>
  );
}
