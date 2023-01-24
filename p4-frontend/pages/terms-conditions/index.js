import { getTermsAndConditions } from "@/data/querries";

export default function TermsConditionsPage(props) {
  const info = props.termsAndConditionsInfo;
  return (
    <div className="justify-center w-screen grid grid-cols-3 gap-y-10 overflow-hidden">
      <div className="w-full bg-slate-500 text-center rounded-md h-full m-10 p-5 col-start-2 ">
        <p>{info}</p>
      </div>
    </div>
  );
}

export async function getStaticProps() {
  const termsAndConditions = await getTermsAndConditions();
  return {
    props: {
      termsAndConditionsInfo: termsAndConditions,
    },
  };
}
