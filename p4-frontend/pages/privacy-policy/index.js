import { getPrivacyPolicy } from "../../data/querries";

export default function PrivacyPolicyPage(props) {
  const info = props.privacyPolicy;
  return (
    <div className="justify-center w-screen grid grid-cols-3 gap-y-10 overflow-hidden">
      <div className="w-full bg-slate-500 text-center rounded-md h-full m-10 p-5 col-start-2 ">
        <h1 className="text-4xl font-bold">
          {info.fields.privacyPolicyTitle}
          <hr />
        </h1>
        {info.fields.privacyPolicyItems.map((item) => (
          <div key={item.sys.id}>
            <p className="font-bold">{item.fields.privacyTitle}</p>
            <p>{item.fields.privacyParagraph.content[0].content[0].value}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export async function getStaticProps() {
  const privacyInfo = await getPrivacyPolicy();
  return {
    props: {
      privacyPolicy: privacyInfo,
    },
  };
}
