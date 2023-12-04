package com.demo.nodescaler.controller;

import com.demo.nodescaler.dto.ClusterDto;
import com.demo.nodescaler.dto.ClusterListDto;
import com.demo.nodescaler.dto.NodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cluster")
@RequiredArgsConstructor
public class ClusterController {

    @GetMapping("/list")
    public ResponseEntity<ClusterListDto> clusters() {
        log.info("GET /api/cluster/list");

        // dummy data
        List<String> names = new ArrayList<>();
        names.add("cubectl");
        ClusterListDto dto = new ClusterListDto(names);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{cluster}")
    public ResponseEntity<ClusterDto> cluster(@PathVariable("cluster") String cluster) {
        log.info("GET /api/cluster/{" + cluster + "}");

        String name = "cubectl";
        List<NodeDto> nodes = dummy();
        ClusterDto dto = new ClusterDto(name, nodes);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private List<NodeDto> dummy() {

        List<NodeDto> nodes = new ArrayList<>();
        nodes.add(new NodeDto("master1", "192.168.0.137", "Ready", "8 Core", "3%", "15.4 GB", "22%", "9/110", "N"));
        nodes.add(new NodeDto("master2", "192.168.0.138", "Ready", "8 Core", "2%", "15.4 GB", "19%", "8/110", "N"));
        nodes.add(new NodeDto("master3", "192.168.0.139", "Ready", "8 Core", "12%", "15.4 GB", "56%", "23/110", "N"));
        nodes.add(new NodeDto("worker1", "192.168.0.190", "Ready", "8 Core", "12%", "15.4 GB", "46%", "24/110", "N"));
        nodes.add(new NodeDto("worker2", "192.168.0.191", "Ready", "8 Core", "4%", "15.4 GB", "54%", "20/110", "N"));
        nodes.add(new NodeDto("cube-worker", "192.168.0.192", "Ready", "4 Core", "13%", "15.4 GB", "42%", "17/110", "Y"));

        return nodes;
    }
}
