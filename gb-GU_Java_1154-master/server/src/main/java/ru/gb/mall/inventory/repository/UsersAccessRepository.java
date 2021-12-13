package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.gb.mall.inventory.entity.UsersAccess;

import java.util.Optional;

public interface UsersAccessRepository extends PagingAndSortingRepository <UsersAccess, Long> {
   UsersAccess findByEmail(@Param("email") String email);
}
