import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css";
export default function ProductGroup({ title, products }) {
  const { responsive } = products;
  return (
    <div
      className={
        "flex flex-col justify-center items-center overflow-hidden p-2"
      }>
      <div className="bg-slate-200 w-full rounded-t-lg">
        <h3 className="text-center text-black">{title}</h3>
      </div>
      <Carousel
        showArrows={true}
        showIndicators={false}
        infiniteLoop={true}
        dynamicHeight={false}
        showThumbs={false}
        autoPlay={true}
        interval={5000}
        className={"w-full"}>
        {responsive.map((item) => (
          <div
            key={item.id}
            className={
              "text-center text-lg text-white flex flex-col justify-center items-center bg-gradient-to-r from-slate-400 to-slate-500 rounded-b-lg"
            }>
            <div className={"w-4/5 p-1 mt-2"}>
              <img
                src={item.imageUrl}
                alt="slides"
                className="object-cover aspect-square object-center"
              />
            </div>
            <div>
              <h2>{item.title}</h2>
              <p>{item.text}</p>
            </div>
          </div>
        ))}
      </Carousel>
    </div>
  );
}
