package com.ebasenet.study.entity;

import com.ebasenet.study.constant.ItemSellStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;              // PK

	@Column(nullable = false, length = 50, unique = true)
	private String itemNum;       // 상품코드

	@Column(nullable = false, length = 100)
	private String itemName;      // 상품명

	@Column(nullable = false)
	private int price;            // 가격

	@Column(nullable = false)
	private int stockNumber;      // 재고 수량

	@Lob
	@Column(nullable = false)
	private String itemDetail;    // 상품 상세 설명

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ItemSellStatus itemSellStatus; // 판매 상태


	private LocalDateTime regTime;      // 등록 시간

	private LocalDateTime updateTime;   // 수정 시간



	@PrePersist
	protected void onCreate() {
		this.regTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public ItemSellStatus getItemSellStatus() {
		return itemSellStatus;
	}

	public void setItemSellStatus(ItemSellStatus itemSellStatus) {
		this.itemSellStatus = itemSellStatus;
	}

	public String getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
}