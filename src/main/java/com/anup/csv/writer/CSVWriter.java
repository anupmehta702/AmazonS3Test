package com.anup.csv.writer;

import com.anup.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.anup.FileConstants.HEADERS;
import static com.anup.FileConstants.FILE_PATH;

public class CSVWriter {


    public static void main(String[] args) throws IOException {
        writeToCSVFile();
    }

    public static void writeToCSVFile() throws IOException {
        System.out.println("Writing to file ..");
        List<Customer> customerList = Customer.getCustomers();
        FileWriter out = new FileWriter(new File(FILE_PATH).getAbsolutePath());
        try(final CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))){
            customerList.forEach(record -> {
                try {
                    printer.printRecord(record.getId(),record.getName(),record.getEmail());
                } catch (IOException e) {
                    System.out.println("Error while writing record -->"+e.getMessage());
                    e.printStackTrace();
                }
            } );
        } catch (IOException e) {
            System.out.println("Error while creating CSVPrinter --> "+e.getMessage());
            e.printStackTrace();
        }

    }



}
