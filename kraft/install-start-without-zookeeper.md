# Apache Kafka Installation Guide (KRaft Mode) with JDK 21

This guide outlines the steps to install and configure Apache Kafka on a Linux system without using ZooKeeper, utilizing KRaft mode for metadata management. It also includes instructions to install OpenJDK 21.

## Prerequisites

- **Java Development Kit (JDK) 21**: Ensure that JDK 21 is installed on your system. You can verify the installation by running:
  ```bash
  java -version
  ```
    - **Installing OpenJDK 21** (if not present install)
        ```
        sudo apt install openjdk-21-jdk
        ```
    - **Set Environment Variables**

        To set the JAVA_HOME environment variable, add the following line to your ~/.bashrc or ~/.profile
        ```
        #
        export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
        export PATH=$JAVA_HOME/bin:$PATH
        ```
    - **Apply changes**
        ```
        source ~/.bashrc
        ```

- **Installing Apache Kafka in KRaft Mode**

    - **Download Apache Kafka**
        Visit the Apache Kafka downloads page and download the latest binary release suitable for your system

    - **Extract the Kafka Archive**
        ```tar -xzf kafka_2.13-<version>.tgz```
    - **Format the Storage Directory** 
        Before starting the Kafka broker in KRaft mode, format the storage directory to initialize the metadata
        ```
        ./bin/kafka-storage.sh format -t $(./bin/kafka-storage.sh random-uuid) -c config/kraft/server.properties
        ```
    - **Launch the Kafka broker using the configured settings**
        ```
        ./bin/kafka-server-start.sh config/kraft/server.properties
        ```

- **Verification**

    - **Create a test topic**
        ```
        ./bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092
        ```
    - **Produce Messages**
        ```
        ./bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
        ```
    - **Consume Messages**
        ```
        ./bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
        ```




