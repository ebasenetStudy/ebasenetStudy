# 영속성 컨텍스트 (Persistence Context)

---

## 개념

- EntityManager 내부에 존재하는 **1차 캐시**
- 엔티티 객체를 관리하는 공간
- 동일 트랜잭션 안에서 같은 PK로 조회 시 **DB 쿼리 없이 캐시에서 재사용**
- 엔티티 상태 변화를 감지해 **변경된 데이터만 UPDATE SQL로 생성** (Dirty Checking)
- SQL 실행을 모아두었다가 커밋 시점에 한 번에 실행 (쓰기 지연)

---

## 주요 기능

---

### 1. 1차 캐시

- EntityManager는 조회한 엔티티를 **영속성 컨텍스트**에 저장
- 동일 엔티티 재조회 시 **DB를 다시 조회하지 않음**

예시:

```java
Member m1 = em.find(Member.class, 1L);
Member m2 = em.find(Member.class, 1L);

System.out.println(m1 == m2); // true
```

---

### 2. 동일성(identity) 보장

- 동일 트랜잭션 내에서 같은 엔티티는 **동일 객체**를 반환
- 객체 비교 시 equals()가 아니라 `==` 비교도 true

---

### 3. Dirty Checking (변경 감지)

- 영속 상태 엔티티의 값이 바뀌면 커밋 시점에 **UPDATE SQL 자동 실행**
- 개발자가 명시적으로 UPDATE SQL 작성할 필요 없음

예시:

```java
Member m = em.find(Member.class, 1L);
m.setName("변경됨");

// 트랜잭션 커밋 시 자동으로 SQL 실행:
// UPDATE member SET name = '변경됨' WHERE id = 1
```

---

### 4. 쓰기 지연 (Write-Behind)

- persist() 호출해도 즉시 INSERT SQL 실행하지 않음
- SQL을 모아두었다가 커밋 시 한 번에 실행 → Batch 처리 가능

예시:

```java
Member m1 = new Member("A");
Member m2 = new Member("B");

em.persist(m1);
em.persist(m2);

// INSERT 실행 안 됨

em.flush(); 
// INSERT 실행됨
```

**장점:**
- SQL batch 처리 가능
- DB round-trip 감소

---

### 5. 플러시(Flush)

- 영속성 컨텍스트 → DB로 쿼리 전송
- 커밋 시 자동으로 flush
- 개발자가 필요할 때 수동으로 호출 가능

예시:

```java
em.flush();
```

---

## 영속 상태 vs 준영속 상태

- **영속 상태**
  - EntityManager가 엔티티 관리 중
- **준영속 상태**
  - EntityManager가 엔티티 관리에서 분리(detach)
  - Dirty Checking 작동 안 함

---

## 주의사항

- 영속성 컨텍스트가 커지면 **메모리 사용량 증가**
- 트랜잭션을 너무 길게 유지하지 말 것

---