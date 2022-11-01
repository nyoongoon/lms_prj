package com.prj.lms.repository.item;

import com.prj.lms.domain.item.ItemEntity;
import com.prj.lms.domain.item.QItemEntity;
import com.prj.lms.domain.item.itemConstant.ItemSellStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    // 테스트 상품 리스트 생성 코드
    public List<ItemEntity> createItemList() {
        List<ItemEntity> savedItemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            savedItemList.add(itemRepository.save(item));
        }
        return savedItemList;
    }

    public void createItemList2(){
        for (int i = 1; i <= 5; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
        for (int i = 6; i <= 10; i++) {
            ItemEntity item = new ItemEntity();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT); //sold_out 케이스
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 저장 테스트 - 상태 SELL")
    public void createItemTest() {
        itemRepository.deleteAll();
        ItemEntity item = new ItemEntity();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        LocalDateTime now = LocalDateTime.now();
        item.setRegTime(now);
        item.setUpdateTime(now);
        ItemEntity saveItem = itemRepository.save(item);

        assertEquals("테스트 상품", saveItem.getItemNm());
        assertEquals(10000, saveItem.getPrice());
        assertEquals("테스트 상품 상세 설명", saveItem.getItemDetail());
        assertEquals(ItemSellStatus.SELL, saveItem.getItemSellStatus());
        assertEquals(100, saveItem.getStockNumber());
        assertEquals(now, saveItem.getRegTime());
        assertEquals(now, saveItem.getUpdateTime());
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        itemRepository.deleteAll();
        List<ItemEntity> savedItemList = this.createItemList();
        for (ItemEntity savedItem : savedItemList) {
            List<ItemEntity> findItemList = itemRepository.findByItemNm(savedItem.getItemNm());
            for (ItemEntity findItem : findItemList) {
                assertEquals(findItem.getItemNm(), savedItem.getItemNm());
            }
        }
    }

    @Test
    @DisplayName("상품명 or 상품상세설명 테스트")
    public void findByItemNmOrItemDetailTest() {
        itemRepository.deleteAll();
        this.createItemList();
//        savedItemList.get(1)
        List<ItemEntity> findItemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        assertEquals("테스트 상품1", findItemList.get(0).getItemNm());
        assertEquals("테스트 상품5", findItemList.get(1).getItemNm());
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        itemRepository.deleteAll();
        List<ItemEntity> savedItemList = this.createItemList();
        int price = 10005;
        List<ItemEntity> itemList = itemRepository.findByPriceLessThan(price);
        List<ItemEntity> savedLessThanList = new ArrayList<>();
        for (ItemEntity savedItem : savedItemList) {
            if (savedItem.getPrice() < price) {
                savedLessThanList.add(savedItem);
            }
        }
        assertEquals(savedLessThanList.size(), itemList.size());
        for (int i = 0; i < savedLessThanList.size(); i++) {
            assertEquals(savedLessThanList.get(i).getItemNm(), itemList.get(i).getItemNm());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest() {
        itemRepository.deleteAll();
        List<ItemEntity> savedItemList = this.createItemList();
        int price = 10005;
        List<ItemEntity> itemList =
                itemRepository.findByPriceLessThanOrderByPriceDesc(price);
        List<ItemEntity> savedLessThanList = new ArrayList<>();
        for (ItemEntity savedItem : savedItemList) {
            if (savedItem.getPrice() < price) {
                savedLessThanList.add(savedItem);
            }
        }
        assertEquals(savedLessThanList.size(), itemList.size());
        for (int i = 0; i < savedLessThanList.size(); i++) {
            assertEquals(savedLessThanList.get(i).getItemNm(), itemList.get(savedLessThanList.size() - 1 - i).getItemNm());
        }
    }

    @Test
    @DisplayName("Query를 이용한 상품 조회 테스트")
    void findByItemDetailTest() {
        itemRepository.deleteAll();
        //given
        List<ItemEntity> savedItemList = this.createItemList();
        ItemEntity item = new ItemEntity();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세하지 않은 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        LocalDateTime now = LocalDateTime.now();
        item.setRegTime(now);
        item.setUpdateTime(now);
        savedItemList.add(itemRepository.save(item));
        //when
        List<ItemEntity> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        //then
        assertEquals(savedItemList.size() - 1, itemList.size());
    }

    @Test
    @DisplayName("Querydsl 조회 테스트 1")
    void queryDslTest(){
        itemRepository.deleteAll();
        //given
        List<ItemEntity> savedItemList = this.createItemList();
        ItemEntity item = new ItemEntity();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세하지 않은 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        LocalDateTime now = LocalDateTime.now();
        item.setRegTime(now);
        item.setUpdateTime(now);
        savedItemList.add(itemRepository.save(item));
        //when
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItemEntity qItem = QItemEntity.itemEntity;
        JPAQuery<ItemEntity> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
                .orderBy(qItem.price.desc());
        List<ItemEntity> itemList = query.fetch();
        //then
        assertEquals(savedItemList.size() - 1, itemList.size());
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    void queryDslTest2(){
        //given
        this.createItemList2();
        //when
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItemEntity item = QItemEntity.itemEntity;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat = "SELL";
        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(itemSellStat.equals(ItemSellStatus.SELL.toString())){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }
        Pageable pageable = PageRequest.of(0, 5);
        Page<ItemEntity> itemPagingResult =
                itemRepository.findAll(booleanBuilder, pageable);
        //then
        System.out.println("total elements : " +
                itemPagingResult.getTotalElements());

        List<ItemEntity> resultItemList = itemPagingResult.getContent();
        for (ItemEntity resultItem: resultItemList){
            System.out.println(resultItem.toString());
        }
    }

}


