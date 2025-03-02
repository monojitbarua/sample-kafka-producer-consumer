# kafka-ui
Connecting local bootstrap cluster
```
docker run -d --name=kafka-ui -p 8080:8080 \
  -e KAFKA_CLUSTERS_0_NAME=local \
  -e KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=<bootstrap-server-ip>:9092 \
  -e DYNAMIC_CONFIG_ENABLED=true \
  provectuslabs/kafka-ui:latest
```
> If kafka-ui not able to connect then update the kafka broker advertised listner below
  ```
  advertised.listeners=PLAINTEXT://<bootstrap-server-ip>:9092
  ```