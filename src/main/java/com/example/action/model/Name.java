package com.example.action.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("names")
public class Name {

    @PrimaryKey
    private Long id;
    private String firstname;
    private String lastname;
}
