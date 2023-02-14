import { connectStateResults } from "react-instantsearch-dom";
import Link from "next/link";

function Hits({ searchState, searchResults }) {
  const validQuery = searchState.query?.length >= 3;

  return (
    <div className="">
      {searchResults?.hits.length === 0 && validQuery && (
        <p>Aw snap! No search results were found.</p>
      )}
      {searchResults?.hits.length > 0 && validQuery && (
        <ol>
          {searchResults.hits.map((hit) => (
            <li
              key={hit.objectID}
              className="border-b border-gray-800 p-1 hover:bg-slate-700 rounded-sm"
            >
              <Link href={`/products/${hit.objectID}`}>{hit.title}</Link>
              &nbsp;&nbsp;€{hit.price}
            </li>
          ))}
        </ol>
      )}
    </div>
  );
}

export default connectStateResults(Hits);
