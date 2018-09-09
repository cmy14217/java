package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    private List<Product> productList = new ArrayList<>();
    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        char[] readData = new char[1000];
        reader.read(readData);
        ObjectMapper mapper = new ObjectMapper();
        String content = String.valueOf(readData);
        productList = mapper.readValue(content, new TypeReference<List<Product>>(){});
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (barcodeContent == null){
            barcodeContent = "";
        }
        if (productList.size() == 0){
            throw new IllegalStateException();
        }

        String line = System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        builder.append("Receipts" + line);
        builder.append("------------------------------------------------------------").append(line);

        int sumPrice = 0;
        List<String> nameArray = new ArrayList<>();
        Integer[] priceArray = new Integer[productList.size()];
        Integer[] quantityArray = new Integer[productList.size()];
        int count = 0;
        String[] barcodes = barcodeContent.split("\"");
        for (int i = 1; i < barcodes.length; i+=2) {
            for (Product product : productList) {
                if (product.getId().equals(barcodes[i])){
                    if (nameArray.contains(product.getName())){
                        quantityArray[nameArray.indexOf(product.getName())]++;
                    }else{
                        nameArray.add(product.getName());
                        priceArray[count] = product.getPrice();
                        quantityArray[count++] = 1;
                    }
                    sumPrice += product.getPrice();
                }
            }
        }
        for (int i = 0; i < count; i++) {
            builder.append(String.format("%-32s", nameArray.get(i)));
            builder.append(String.format("%-11s", priceArray[i]));
            builder.append(String.format("%d", quantityArray[i]));
            builder.append(line);
        }

        builder.append("------------------------------------------------------------").append(line);
        builder.append("Price: ").append(sumPrice).append(line);
        return builder.toString();
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}