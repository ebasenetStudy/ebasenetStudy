package com.ebasenet.study.dao.jpql;

import com.ebasenet.study.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoJpql {

	@PersistenceContext
	private EntityManager em;

	public List<Item> findByItemName(String itemName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Item i ");
		sb.append("WHERE i.itemName = :name");

		return em.createQuery(sb.toString(), Item.class)
			.setParameter("name", itemName)
			.getResultList();
	}
}