package io.debezium.server.pubsub;

import io.debezium.server.TestConfigSource;
import org.apache.kafka.connect.runtime.standalone.StandaloneConfig;

import java.util.HashMap;
import java.util.Map;

public class PubSubLiteTestConfigSource extends TestConfigSource {

    public PubSubLiteTestConfigSource() {
        Map<String, String> pubsubLiteTest = new HashMap<>();

        pubsubLiteTest.put("debezium.sink.type", "pubsublite");
        pubsubLiteTest.put("debezium.source.connector.class", "io.debezium.connector.postgresql.PostgresConnector");
        pubsubLiteTest.put("debezium.source." + StandaloneConfig.OFFSET_STORAGE_FILE_FILENAME_CONFIG,
                OFFSET_STORE_PATH.toAbsolutePath().toString());
        pubsubLiteTest.put("debezium.source.offset.flush.interval.ms", "0");
        pubsubLiteTest.put("debezium.source.database.server.name", "testc");
        pubsubLiteTest.put("debezium.source.schema.include.list", "inventory");
        pubsubLiteTest.put("debezium.source.table.include.list", "inventory.customers");

        config = pubsubLiteTest;
    }

    @Override
    public int getOrdinal() {
        // Configuration property precedence is based on ordinal values and since we override the
        // properties in TestConfigSource, we should give this a higher priority.
        return super.getOrdinal() + 1;
    }

}
