#!/bin/bash
BROKER=localhost:9092

create_topic() {
  kafka-topics.sh --create \
    --bootstrap-server $BROKER \
    --topic "$1" \
    --partitions 6 \
    --replication-factor 1 \
    --config retention.ms=604800000 \
    --if-not-exists
}

create_topic "transfer.requested"
create_topic "transfer.reserved"
create_topic "transfer.reserve-failed"
create_topic "transfer.credited"
create_topic "transfer.credit-failed"
create_topic "transfer.requested-release"
create_topic "transfer.compensated"
create_topic "transfer.completed"
create_topic "transfer.failed"

echo "Done. Listing topics:"
kafka-topics.sh --list --bootstrap-server $BROKER