package groovy.com.reachlocal.deprovision.batch

import groovy.com.reachlocal.deprovision.batch.processors.CallSourceDeprovisioningProcessor
import groovy.com.reachlocal.deprovision.batch.readers.CallSourceCampaignDataReader
import groovy.com.reachlocal.deprovision.batch.writers.CallSourceItemWriter
import groovy.com.reachlocal.deprovision.domain.callsource.CallSourceCampaignData
import groovy.com.reachlocal.deprovision.domain.callsource.TrackingNumber

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.sql.DataSource

/**
 * Created by Umair on 25/01/16.
 */

@Configuration
@EnableBatchProcessing
class CallSourceBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobs

    @Autowired
    private StepBuilderFactory steps

    @Value("${CALLSOURCE.CAMPAIGN.DATA.FETCH.QUERY}")
    private String callSourceFetchCampaignDataQuery

    @Bean
    ItemReader<CallSourceCampaignData> reader(@Qualifier("dataSource") DataSource dataSource) {
        return new CallSourceCampaignDataReader(dataSource, callSourceFetchCampaignDataQuery)
    }

    @Bean
    ItemProcessor<CallSourceCampaignData, TrackingNumber> processor() {
        return new CallSourceDeprovisioningProcessor()
    }

    @Bean
    @StepScope
    CallSourceItemWriter writer() {
        new CallSourceItemWriter()
    }

    @Bean(name = "callSourceDeprivisioningJob")
    Job callSourceDeprivisioningJob(@Qualifier("callSourceDeprivisioning") Step step) {
        jobs.get("callSourceDeprivisioningJob")
                .start(step)
                .build()
    }

    @Bean
    Step callSourceDeprivisionStep(ItemReader<CallSourceCampaignDataReader> reader, ItemProcessor<CallSourceDeprovisioningProcessor, TrackingNumber> processor,
                                   ItemWriter<CallSourceItemWriter> writer) {
        steps.get("callSourceDeprivisioning")
                .<CallSourceCampaignData>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build()
    }
}
