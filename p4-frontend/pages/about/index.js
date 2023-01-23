import { getAboutPageInfo } from "@/data/querries";

export default function AboutPage(props) {
  const aboutUsInfo = props.aboutUsInfo;
  console.log(aboutUsInfo);
  return (
    <div className="justify-center w-screen grid grid-cols-3 gap-y-10 overflow-hidden">
      <div className="w-full bg-slate-500 text-center rounded-md h-full m-10 p-5 col-start-2 ">
        <h1 className="text-lg font-bold">{aboutUsInfo.aboutTitle}</h1>
        <p>{aboutUsInfo.aboutDescription}</p>
        <br />
        <p>
          {aboutUsInfo.contactTitle}: {aboutUsInfo.contactInfo}
        </p>
      </div>
      <div className="w-full bg-slate-500 text-center rounded-md h-full m-10 p-5 col-start-2">
        <h2 className="text-lg font-bold">{aboutUsInfo.historyTitle}</h2>
        <p>{aboutUsInfo.historyDescription}</p>
      </div>
    </div>
  );
}

export async function getStaticProps() {
  const aboutUs = await getAboutPageInfo();
  console.log("about us:", aboutUs);

  return {
    props: {
      aboutUsInfo: aboutUs,
    },
  };
}
