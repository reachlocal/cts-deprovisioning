package groovy.com.reachlocal.deprovision.batch.readers

import groovy.com.reachlocal.deprovision.domain.callsource.CallSourceCampaignData
import groovy.com.reachlocal.deprovision.domain.callsource.TrackingNumber
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component

import javax.sql.DataSource
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Created by Umair on 25/01/16.
 */
@Component("callSourceDataReader")
class CallSourceCampaignDataReader extends JdbcCursorItemReader<CallSourceCampaignData> {

    CallSourceCampaignDataReader(DataSource dataSource, String query) {
        super();
        setDataSource(dataSource)
        setSql(query)
        setRowMapper(new RowMapper<TrackingNumber>() {
            @Override
            TrackingNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO
                return
            }
        })
    }

}
