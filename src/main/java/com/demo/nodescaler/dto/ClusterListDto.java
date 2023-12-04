package com.demo.nodescaler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ClusterListDto {
    private List<String> names = new ArrayList<>();
}
