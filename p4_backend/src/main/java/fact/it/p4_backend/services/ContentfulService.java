package fact.it.p4_backend.services;

import com.contentful.java.cda.*;
import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import org.springframework.stereotype.Service;

@Service
public class ContentfulService {
    private final CDAClient client;


    public ContentfulService() {
        this.client =
                CDAClient
                        .builder()
                        .setToken("oxbuUp5Nh3Rd9EV2mhBj_QFF_IkcdWsRuYagNQi0iHs") // required
                        .setSpace("n80dqssuk9ot") // required
                        .build();
    }

    public CDAClient getClient() {
        return client;
    }

    public CDAArray getEntries() {
        return client.fetch(CDAEntry.class).all();
    }

    public CDAEntry getEntryById(String entryId) {
        return client.fetch(CDAEntry.class).one(entryId);
    }

}
