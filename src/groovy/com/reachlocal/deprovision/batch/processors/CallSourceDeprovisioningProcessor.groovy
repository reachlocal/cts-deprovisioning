package groovy.com.reachlocal.deprovision.batch.processors

import groovy.com.reachlocal.deprovision.domain.callsource.CallSourceCampaignData
import groovy.com.reachlocal.deprovision.domain.callsource.TrackingNumber

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

/**
 * Created by Umair on 25/01/16.
 */
@Component("callSourceDeprovisioningProcessor")
class CallSourceDeprovisioningProcessor implements ItemProcessor<CallSourceCampaignData, TrackingNumber> {

    TrackingNumber process(CallSourceCampaignData item) throws Exception {
        // TODO
    }
}
