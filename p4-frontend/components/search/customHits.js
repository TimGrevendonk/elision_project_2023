import { connectStateResults } from "react-instantsearch-dom";
import Link from "next/link";

function Hits({ searchState, searchResults }) {
  const validQuery = searchState.query?.length >= 3;

  return (
    <div className="absolute z-50">
      {searchResults?.hits.length === 0 && validQuery && (
        <p>Aw snap! No search results were found.</p>
      )}
      {searchResults?.hits.length > 0 && validQuery && (
        <ol>
          {searchResults.hits.map((hit) => (
            <Link href={`/products/${hit.objectID}`} key={hit.objectID}>
              <li className="border-b border-gray-800 p-1 bg-slate-600 hover:bg-slate-700 rounded-sm  0 w-auto">
                {hit.title}
                &nbsp;&nbsp;€{hit.price}
              </li>
            </Link>
          ))}
        </ol>
      )}
    </div>
  );
}

export default connectStateResults(Hits);
