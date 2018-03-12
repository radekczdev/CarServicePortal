package com.czajor.carserviceportal;

import com.czajor.carserviceportal.model.RepairOrder;
import com.czajor.carserviceportal.model.RepairOrderType;

import java.util.ArrayDeque;
import java.util.Queue;

public class OrderQueue {
    private Queue<RepairOrder> queue = new ArrayDeque<>();
    private RepairOrderType queueType;
}
