package com.project.usedtextbooks.repository;

import com.project.usedtextbooks.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 这里，我们目前不需要添加任何自定义方法。
    // JpaRepository已经为我们提供了大多数常见的CRUD操作。
    // 如果你需要一些特定的数据库操作，你可以在这里定义。
}