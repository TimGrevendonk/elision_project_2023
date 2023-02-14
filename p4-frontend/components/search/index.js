// .components/Search/index.js

import algoliasearch from "algoliasearch/lite";
import { InstantSearch } from "react-instantsearch-dom";
import CustomHits from "./customHits";
import CustomSearchBox from "./customSearchBox";

const searchClient = algoliasearch(
  process.env.NEXT_PUBLIC_ALGOLIA_APP_ID,
  process.env.NEXT_PUBLIC_ALGOLIA_SEARCH_API_KEY
);

export default function Search() {
  return (
    <div className="p-2 mt-4">
      <InstantSearch searchClient={searchClient} indexName="d1_tm_team_b3">
        <CustomSearchBox />
        <CustomHits />
      </InstantSearch>
    </div>
  );
}
