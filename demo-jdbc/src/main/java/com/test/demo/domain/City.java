package com.test.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.PipedReader;
import java.io.Serializable;

/**
 * Description
 * <p>
 * DATE 2019/5/15.
 *
 * @author wangliangliang.
 */

@Table(name = "city")
@Entity
@Data
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String map;

    private String name;

    private String state;
}
