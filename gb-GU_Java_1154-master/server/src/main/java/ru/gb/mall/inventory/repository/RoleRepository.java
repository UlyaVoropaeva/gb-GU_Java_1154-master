package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.UsersRole;

public interface RoleRepository extends PagingAndSortingRepository<UsersRole, Long> {

}
