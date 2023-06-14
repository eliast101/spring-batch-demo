package com.elias.batch.batch.step;

import com.elias.batch.model.StockInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

@Slf4j
public class StockInfoProcessor implements ItemProcessor<StockInfo, String> {

    @Override
    public String process(StockInfo stockInfo) throws Exception {
        System.out.println("Hello");
        String message = stockInfo.getStockName() + " is trading at  "
                + stockInfo.getStockPrice() + " on " + stockInfo.getMarket()+" at "+ new Date().toString()+ "!";
        log.info("printing '{}' to output file", message);
        return message;
    }
}
