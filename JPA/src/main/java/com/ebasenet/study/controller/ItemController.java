package com.ebasenet.study.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ebasenet.study.dto.ItemSearchRequest;
import com.ebasenet.study.entity.Item;
import com.ebasenet.study.service.ItemDaoService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private final ItemDaoService itemDaoService;

	public ItemController(ItemDaoService itemDaoService) {
		this.itemDaoService = itemDaoService;
	}

	@GetMapping("/jpql")
	public List<Item> getItemsByJpql(@RequestParam String name) {
		return itemDaoService.findItemsByJpql(name);
	}

	@PostMapping("/native")
	public List<Item> getItemsByNative(@RequestBody ItemSearchRequest request) {
		return itemDaoService.findItemsByNative(request.getName());
	}

	@GetMapping("/repository")
	public List<Item> getItemsByRepository(@RequestParam String name) {
		return itemDaoService.findItemsByRepository(name);
	}
}