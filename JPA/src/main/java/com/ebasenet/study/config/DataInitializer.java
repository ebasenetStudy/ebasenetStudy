package com.ebasenet.study.config;

import com.ebasenet.study.constant.ItemSellStatus;
import com.ebasenet.study.entity.Item;
import com.ebasenet.study.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

	private final ItemRepository itemRepository;

	public DataInitializer(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public void run(String... args) {
		itemRepository.deleteAll();

		itemRepository.save(createItem("ITM20240705001", "Galaxy S24 Ultra", 1599000, 30, "삼성"));
		itemRepository.save(createItem("ITM20240705002", "iPhone 15 Pro", 1450000, 25, "애플"));
		itemRepository.save(createItem("ITM20240705003", "Pixel 8 Pro", 999000, 40, "구글"));
		itemRepository.save(createItem("ITM20240705004", "Xiaomi 14 Ultra", 899000, 50, "샤오미"));
		itemRepository.save(createItem("ITM20240705005", "Galaxy Z Flip 5", 1399000, 20, "삼성"));

		System.out.println("더미데이터 생성완료");
	}

	private Item createItem(String itemNum, String itemName, int price, int stock, String detail) {
		Item item = new Item();
		item.setItemNum(itemNum);
		item.setItemName(itemName);
		item.setPrice(price);
		item.setStockNumber(stock);
		item.setItemDetail(detail);
		item.setItemSellStatus(ItemSellStatus.SELL);
		return item;
	}
}