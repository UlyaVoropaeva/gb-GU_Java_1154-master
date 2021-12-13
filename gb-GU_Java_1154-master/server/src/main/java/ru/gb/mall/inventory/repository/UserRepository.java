package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User getById (Long id);

}
