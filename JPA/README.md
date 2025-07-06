# JPA

---

## ORM (Object-Relational Mapping)

- 자바 객체 ↔ 관계형 DB 테이블 매핑
- SQL 대신 자바 코드로 데이터 처리
- 객체 상태 변경 시 SQL 자동 생성
- 자바 외 다른 언어(Python, PHP 등)에서도 사용됨

---

## JPA와 EntityManager

- **ORM**: 객체-테이블 매핑 규칙
- **JPA**: ORM 규칙을 자바 표준으로 정의한 인터페이스 집합
- **EntityManager**: JPA 규칙서에서 ORM 작업을 실제 수행하는 주체
- **Hibernate**: EntityManager를 구현한 라이브러리

---

## 영속성 컨텍스트

- EntityManager가 관리하는 1차 캐시
- 동일 PK 엔티티 재조회 시 DB 쿼리 생략
- 엔티티 상태 변경 시 커밋 시점에 SQL 자동 생성 (Dirty Checking)
- SQL을 한 번에 모아 실행 (쓰기 지연)
- 자세한 내용은 [영속성 컨텍스트](./docs/persistence-context.md) 참고

## 동작 흐름

→ EntityManager (JPA 인터페이스)  
→ Hibernate EntityManagerImpl (구현체)  
→ DB SQL 실행

---

## JPA 쿼리 방식 

### JPQL
- EntityManager로 실행
- 엔티티명, 필드명 기준
- Hibernate가 SQL로 변환
- DB 독립적

```java
	public List<Item> findByItemName(String itemName) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Item i ");
		sb.append("WHERE i.itemName = :name");

		return em.createQuery(sb.toString(), Item.class)
			.setParameter("name", itemName)
			.getResultList();
	}
```

### Native Query
  - EntityManager로 실행
  - DB 고유 SQL 직접 작성
  - DB 종속적
```java
	public List<Item> findByItemName(String itemName) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM item ");
		sb.append("WHERE item_name = :name");

		return em.createNativeQuery(sb.toString(), Item.class)
			.setParameter("name", itemName)
			.getResultList();
	}
```

### Spring Data JPA Repository
  - Repository 인터페이스만 작성
  - Spring Data JPA가 구현체 생성
  - 메서드명 기반 쿼리 작성 가능

```java
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByItemName(String itemName);
}
```

---
## 핵심 요약
| 개념 | 설명 |
| --- | --- |
| ORM | 객체-테이블 매핑 기술 |
| JPA | ORM 규칙 정의 표준 인터페이스 |
| EntityManager | JPA 규칙에서 ORM 작업을 수행하는 실행 주체 |
| Hibernate | EntityManager를 실제 구현한 라이브러리 |
| 영속성 컨텍스트 | EntityManager가 관리하는 1차 캐시로, 같은 엔티티 중복 조회 방지 및 자동 SQL 생성 |
