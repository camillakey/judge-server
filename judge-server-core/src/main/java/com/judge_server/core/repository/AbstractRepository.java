package com.judge_server.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long> {
    Page<T> findAllByOrderByCreatedAsc(Pageable pageable);
    Page<T> findAllByOrderByCreatedDesc(Pageable pageable);
    Page<T> findAllByOrderByUpdatedAsc(Pageable pageable);
    Page<T> findAllByOrderByUpdatedDesc(Pageable pageable);
}
