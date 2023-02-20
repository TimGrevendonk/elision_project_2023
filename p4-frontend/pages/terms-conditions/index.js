import { getTermsAndConditions } from "../../data/querries";

export default function TermsConditionsPage(props) {
  const info = props.termsAndConditionsInfo;
  return (
    <div className="justify-center w-screen grid grid-cols-3 gap-y-10 overflow-hidden">
      <div className="w-full bg-slate-500 text-center rounded-sm h-full m-10 p-5 col-start-2 ">
        <h1 className="text-4xl font-bold">
          {info.fields.termsAndConditionsTitle}
          <hr />
        </h1>
        {info.fields.termsAndConditions.map((item) => (
          <div key={item.sys.id}>
            <p className="font-bold text-xl pt-5">{item.fields.termTitle}</p>
            <p>{item.fields.termParagraph.content[0].content[0].value}</p>
          </div>
        ))}
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
    // for demo purposes:
    revalidate: 6,
  };
}
