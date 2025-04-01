package hello.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }


    @Test
    void save(){
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): ex.유효한 계정으로 사용자가 로그인한 상태에서
        Item item = new Item("itemA", 10000, 20);

        //when(동작시): ex.사용자가 로그인 버튼을 클릭했을 때
        Item savaItem = itemRepository.save(item);

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savaItem);
    }

    @Test
    void findAll(){
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): ex.유효한 계정으로 사용자가 로그인한 상태에서
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        //when(동작시): ex.사용자가 로그인 버튼을 클릭했을 때
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //BDD (Behavior-Driven Development)
        //given(주어진 상태): ex.유효한 계정으로 사용자가 로그인한 상태에서
        Item item = new Item("item1", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        //when(동작시): ex.사용자가 로그인 버튼을 클릭했을 때
        Item updateParam = new Item("item1", 10000, 20);
        itemRepository.update(itemId, updateParam);

        //then(결과): ex.사용자가 로그인되었음을 확인해야 한다
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}