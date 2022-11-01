package com.prj.lms.repository.item;

import com.prj.lms.domain.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>,
        QuerydslPredicateExecutor<ItemEntity> {
    //Predicate란 조건이 맞다고 판단하는 근거를 함수로 제공하는 것.
    //Repository에 Predicate를 파라미터로 전달하기 위해 QueryDslPredicateExecuter 인터페이스를 상속받음.
    List<ItemEntity> findByItemNm(String itemNm);
    List<ItemEntity> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    List<ItemEntity> findByPriceLessThan(Integer price);
    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(Integer price);
    // 아래의 JPQL 문법으로 문자열 입력시 잘못 입력하면 컴파일 시점 에러 발견 x -> Querydsl 사용하기
    @Query("select i from ItemEntity i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<ItemEntity> findByItemDetail(@Param("itemDetail") String itemDetail);
}
