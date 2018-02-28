package org.lioncruise.clustermocker.utils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.*;
import org.lioncruise.clustermocker.entities.*;

public class CSVOperation {
    public static List<Pod> readInfo() {
        String filePath = "E:\\Alibaba-clusterdata\\container_event.csv";
        List<Pod> podList = new ArrayList<Pod>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));

            List<String[]> lines = reader.readAll();
            for (String[] line: lines) {
                if (line[1].equals("Create")) {
                    int cpuRequest = Integer.parseInt(line[4]);
                    double memRequest = Double.parseDouble(line[5]);
                    podList.add(new Pod(cpuRequest, memRequest));
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return podList;
    }

    public static void writeInfo(Cluster cluster, List<Pod> podList) {
        String machineResult = "E:\\result\\machine_result.csv";
        String podResult = "E:\\result\\pod_result.csv";
        try {
            Writer machineWiter = new FileWriter(new File(machineResult));
            CSVWriter csvWriter = new CSVWriter(machineWiter, ',');

            List<Machine> machines = cluster.getMachines();
            for (Machine machine : machines) {
                String[] strs = {Integer.toString(machine.getId()), Integer.toString(machine.getCpuUsed()),
                                    Double.toString(machine.getMemUsed())};

                csvWriter.writeNext(strs);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Writer podWiter = new FileWriter(new File(podResult));
            CSVWriter csvWriter = new CSVWriter(podWiter, ',');

            for (Pod pod : podList) {
                String[] strs = {Integer.toString(pod.getId()), Boolean.toString(pod.isScheduled()),
                                Integer.toString(pod.getCpuRequest()), Double.toString(pod.getMemRequest())};

                csvWriter.writeNext(strs);

            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
