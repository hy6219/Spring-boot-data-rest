package com.community.spring_boot_community.repository;

import com.community.spring_boot_community.domain.Board;
import com.community.spring_boot_community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findByUser(User user);
}
