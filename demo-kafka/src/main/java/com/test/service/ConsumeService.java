package com.test.service;

import com.oracle.tools.packager.Log;
import com.test.common.Foo1;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

/**
 * @author wangliangliang
 */
@Slf4j
@Service
public class ConsumeService {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    public void sendMq(String what){

        this.template.executeInTransaction(kafkaTemplate -> {
            StringUtils.commaDelimitedListToSet(what).stream()
                    .map(s -> new Foo1(s))
                    .forEach(foo -> kafkaTemplate.send("topic2", foo));
            return null;
        });
    }

    public void handleTopic(Long second){
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.102.131.149:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "dev3-yangyunhe-topic001-group001");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);// 构建消费者
        String topic = "timeline_client_topic201918";

        consumer.subscribe(Arrays.asList(topic));
        Set<TopicPartition> assignment = new HashSet<>();// 获取消费者所分配到的分区信息

        while (assignment.size() == 0){
            consumer.poll(Duration.ofMillis(1000));
            assignment = consumer.assignment();
        }

        long millis = System.currentTimeMillis();
        Map<TopicPartition, Long> timestampsToSearch = consumer.endOffsets(assignment);// 获取指定分区的末尾的消息位
        for (TopicPartition tp : assignment){
            timestampsToSearch.put(tp, millis - 1*24*3600*1000);
            log.info(" tp : {} , offset : {} ", tp, tp.partition());
        }

        Map<TopicPartition, OffsetAndTimestamp> offset = consumer.offsetsForTimes(timestampsToSearch);// 获取每个partition指定时间之前的偏移量

        for (TopicPartition topicPartition : assignment){

            OffsetAndTimestamp offsetAndTimestamp = offset.get(topicPartition);
            if (offsetAndTimestamp != null){
                consumer.seek(topicPartition, offsetAndTimestamp.offset());
            }
        }

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> record : partitionRecords) {
                    //process the record.
                    System.out.println("partition = " + record.partition() + ", offset = " + record.offset() +", value = "+ record.value());
                }
            }
        }


    }
    /**
     *
     * @param second
     */
    public void handleTimeLineClientTopic(Long second){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put("group.id", "dev3-yangyunhe-topic001-group001");
        props.put("key.deserializer", StringDeserializer.class);
        props.put("value.deserializer", StringDeserializer.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        String topic = "timeline_client_salve_topic";

        // 设置一小时之前的记录


        try {

            // 获取topic的partition信息
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();

            Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            long nowTime = now.getTime();
            System.out.println("当前时间: " + df.format(now));
            long fetchDataTime = nowTime - second;

            // 配置偏移量值
            for(PartitionInfo partitionInfo : partitionInfos) {
                topicPartitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
                timestampsToSearch.put(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()), fetchDataTime);
            }

            consumer.assign(topicPartitions);// 手动设置分配

            // 获取每个partition指定时间之前的偏移量
            Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);

            OffsetAndTimestamp offsetTimestamp = null;
            System.out.println("开始设置各分区初始偏移量...");
            for(Map.Entry<TopicPartition, OffsetAndTimestamp> entry : map.entrySet()) {
                // 如果设置的查询偏移量的时间点大于最大的索引记录时间，那么value就为空
                offsetTimestamp = entry.getValue();
                if(offsetTimestamp != null) {
                    int partition = entry.getKey().partition();
                    long timestamp = offsetTimestamp.timestamp();
                    long offset = offsetTimestamp.offset();
                    System.out.println("partition = " + partition +
                            ", time = " + df.format(new Date(timestamp))+
                            ", offset = " + offset);
                    // 设置读取消息的偏移量位置
                    consumer.seek(entry.getKey(), offset);
                }
            }
            System.out.println("设置各分区初始偏移量结束...");

            while(true) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("partition = " + record.partition() + ", offset = " + record.offset() +", value = "+ record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
