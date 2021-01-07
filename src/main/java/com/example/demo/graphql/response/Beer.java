package com.example.demo.graphql.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

    private Integer id;
    private String type;
    private Brand brand;
    private Integer brandId;
    private Set<Message> messages;

}
