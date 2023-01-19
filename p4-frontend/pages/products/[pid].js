/* eslint-disable react/no-unescaped-entities */
export default function ProductDetailPage(props) {
  const id = props.productID;
  return (
    <div className="p-3">
      <div className="bg-slate-600 h-full w-full flex justify-center">
        <h1 className="font-bold text-xl">{id}: Product title</h1>
      </div>
      <div className="bg-slate-400 h-full w-full flex justify-center">
        <img src="/1.jpg" className="aspect-square object-cover w-1/3 h-1/3" />
        <p className="p-5">
          <span>Description:</span>
          <br />
          Introducing the all-new XYZ Smart Thermostat! This state-of-the-art
          thermostat is designed to make your life easier by giving you complete
          control over your home's temperature from anywhere. With its sleek and
          modern design, it will blend seamlessly into any home decor. The XYZ
          Smart Thermostat features built-in WiFi connectivity, allowing you to
          control your thermostat from your smartphone or tablet with the
          easy-to-use XYZ app. You can also use voice commands with Amazon Alexa
          or Google Assistant for added convenience. The thermostat's advanced
          learning algorithm adapts to your schedule, automatically adjusting
          the temperature to your preferences. It also has a vacation mode that
          lets you set a schedule for when you're away, so you don't have to
          worry about wasting energy. Furthermore, the thermostat also has a
          'Energy saving mode' that will suggest you the best temperature
          setting to save energy and money on your bills. The XYZ Smart
          Thermostat is compatible with most HVAC systems, making it easy to
          install and set up. It's also ENERGY STAR certified, so you can trust
          that it will help you save on your energy bills. Upgrade your home
          with the latest in smart technology and enjoy complete control over
          your home's temperature with the XYZ Smart Thermostat!
        </p>
      </div>
    </div>
  );
}

export async function getStaticProps(context) {
  const productId = context.params.pid;

  return {
    props: {
      productID: productId,
    },
  };
}

export async function getStaticPaths() {
  const paths = [{ params: { pid: "1" } }];
  return {
    paths: paths,
    fallback: true,
  };
}
