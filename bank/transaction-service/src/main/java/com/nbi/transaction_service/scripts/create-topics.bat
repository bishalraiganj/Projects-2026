@echo off
set BROKER=localhost:9092

echo Creating topics...

call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.requested --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.reserved --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.reserve-failed --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.credited --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.credit-failed --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.requested-release --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.compensated --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.completed --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists
call kafka-topics.bat --create --bootstrap-server %BROKER% --topic transfer.failed --partitions 6 --replication-factor 1 --config retention.ms=604800000 --if-not-exists

echo Done. Listing topics:
call kafka-topics.bat --list --bootstrap-server %BROKER%

pause
