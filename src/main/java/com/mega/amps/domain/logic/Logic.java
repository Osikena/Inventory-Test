package com.mega.amps.domain.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class Logic {

    public boolean isMatchesInteger(String number){
            Pattern pattern = Pattern.compile("^[0-9]{1,45}$");
            return pattern.matcher(number).matches();
    }

    public boolean isMatchesDouble(String number){
        Pattern pattern = Pattern.compile("^[0-9]*\\.?[0-9]+{1,45}$");
        return pattern.matcher(number).matches();
    }

    public String generateBarcode(){
        String randomcode = null;

        try{

            ThreadLocalRandom random = ThreadLocalRandom.current();
            long barcode = random.nextLong(100_000_000_0L);
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String[] add = dateTime.format(dateTimeFormatter).split(" ");
            String[] date = add[0].split("-");
            String totalDate = date[0] + date[1] + date[2];

            String[] time = add[1].split(":");
            String totalTime = time[0] + time[1] + time[2];

            long totalDateTime = Long.parseLong(totalDate + totalTime);

            randomcode = Long.toString(barcode + totalDateTime);


        }catch (Exception e){
            System.out.println(e);
        }

        return randomcode;
    }



}
