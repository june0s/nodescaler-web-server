package com.demo.nodescaler.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class NodeDto {
    private String name;
    private String ip;
    private String status;
    private String cpuTotal;
    private String cpuUsage;
    private String memTotal;
    private String memUsage;
    private String podCnt;
    private String scaleYn;

    @Builder
    public NodeDto(String name, String ip, String status, String cpuTotal, String cpuUsage, String memTotal, String memUsage, String podCnt, String scaleYn) {
        this.name = name;
        this.ip = ip;
        this.status = status;
        this.cpuTotal = cpuTotal;
        this.cpuUsage = cpuUsage;
        this.memTotal = memTotal;
        this.memUsage = memUsage;
        this.podCnt = podCnt;
        this.scaleYn = scaleYn;
    }
}
