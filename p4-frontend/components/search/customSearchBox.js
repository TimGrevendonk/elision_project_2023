import { connectSearchBox } from "react-instantsearch-dom";

function SearchBox({ refine }) {
  return (
    <form className="grid grid-cols-12" action="" role="search">
      <input
        id="algolia_search"
        type="search"
        className="w-full p-3 col-span-full md:col-start-2 md:col-end-12 rounded-sm"
        placeholder="Search products"
        onChange={(e) => refine(e.currentTarget.value)}
      />
    </form>
  );
}

export default connectSearchBox(SearchBox);
