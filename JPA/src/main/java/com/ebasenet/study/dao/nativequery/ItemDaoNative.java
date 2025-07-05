package com.ebasenet.study.dao.nativequery;

import com.ebasenet.study.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoNative {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Item> findByItemName(String itemName) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM item ");
		sb.append("WHERE item_name = :name");

		return em.createNativeQuery(sb.toString(), Item.class)
			.setParameter("name", itemName)
			.getResultList();
	}
}