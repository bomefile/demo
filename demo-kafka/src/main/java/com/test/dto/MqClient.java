package com.test.dto;

import lombok.Data;

import java.util.Date;


/**
 * @author wangliangliang
 */
@Data
public class MqClient {

    /**
     * 消费的topic
     */
    private String topic;

    /**
     * 开始消费时间
     */
    private Date startTime;
}
