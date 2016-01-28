package groovy.com.reachlocal.deprovision.batch.writers

import groovy.com.reachlocal.deprovision.domain.callsource.CallSourceCampaignData
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

/**
 * Created by Umair on 25/01/16.
 */
@Component("callSourceWriter")
class CallSourceItemWriter implements ItemWriter<CallSourceCampaignData> {

    @Override
    void write(List<? extends CallSourceCampaignData> data) throws Exception {
        //TODO
    }
}
