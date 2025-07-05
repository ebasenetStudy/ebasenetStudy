package com.ebasenet.study.service;

import com.ebasenet.study.dao.jpql.ItemDaoJpql;
import com.ebasenet.study.dao.nativequery.ItemDaoNative;
import com.ebasenet.study.entity.Item;
import com.ebasenet.study.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDaoService {

	private final ItemDaoJpql itemDaoJpql;
	private final ItemDaoNative itemDaoNative;
	private final ItemRepository itemRepository;

	public ItemDaoService(ItemDaoJpql itemDaoJpql, ItemDaoNative itemDaoNative, ItemRepository itemRepository) {
		this.itemDaoJpql = itemDaoJpql;
		this.itemDaoNative = itemDaoNative;
		this.itemRepository = itemRepository;
	}

	public List<Item> findItemsByJpql(String name) {
		return itemDaoJpql.findByItemName(name);
	}

	public List<Item> findItemsByNative(String name) {
		return itemDaoNative.findByItemName(name);
	}

	public List<Item> findItemsByRepository(String name) {
		return itemRepository.findByItemName(name);
	}
}