// .components/Search/index.js

import algoliasearch from "algoliasearch/lite";
import { InstantSearch } from "react-instantsearch-dom";
import CustomHits from "./customHits";
import CustomSearchBox from "./CustomSearchBox";

const searchClient = algoliasearch(
  process.env.NEXT_PUBLIC_ALGOLIA_APP_ID,
  process.env.NEXT_PUBLIC_ALGOLIA_SEARCH_API_KEY
);

export default function Search() {
  return (
    <>
      <InstantSearch searchClient={searchClient} indexName="d1_tm_team_b3">
        <CustomSearchBox />
        <CustomHits />
      </InstantSearch>
    </>
  );
}