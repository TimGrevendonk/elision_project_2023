import { getAboutPageInfo } from "@/data/querries";

export default function AboutPage(props) {
  const aboutUsInfo = props.aboutUsInfo;
  return (
    <div className="justify-center grid grid-cols-12 gap-y-10 overflow-hidden mt-3 mb-3">
      <div className="w-full bg-slate-500 col-start-2 col-span-10 text-center rounded-md h-full p-5">
        <h1 className="text-lg font-bold">{aboutUsInfo.aboutTitle}</h1>
        <p>{aboutUsInfo.aboutDescription}</p>
        <br />
        <p className="font-bold">{aboutUsInfo.contactTitle}</p>
        <p>{aboutUsInfo.contactInfo}</p>
      </div>
      <div className="w-full bg-slate-500 col-start-2 col-span-10 text-center rounded-md h-full p-5">
        <h2 className="text-lg font-bold">{aboutUsInfo.historyTitle}</h2>
        <p>{aboutUsInfo.historyDescription}</p>
      </div>
    </div>
  );
}

export async function getStaticProps() {
  const aboutUs = await getAboutPageInfo();

  return {
    props: {
      aboutUsInfo: aboutUs,
    },
  };
}
