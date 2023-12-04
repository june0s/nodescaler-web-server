package com.demo.nodescaler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ClusterDto {
    private String name;
    private List<NodeDto> nodes = new ArrayList<>();
}
