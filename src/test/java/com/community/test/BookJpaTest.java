package com.community.test;

import com.community.test.domain.Book;
import com.community.test.repository.BookRepository;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookJpaTest {

    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager; // JPA CRUD 지원

    @Test
    public void Book_저장하기_테스트() {
        Book book = Book.builder()
                .title(BOOT_TEST_TITLE)
                .publishedAt(LocalDateTime.now())
                .build();

        testEntityManager.persist(book);

        assertThat(bookRepository.getOne(book.getIdx()), is(book));
    }

    @Test
    public void BookList_저장_검색_테스트() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE + "1").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder().title(BOOT_TEST_TITLE + "2").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        Book book3 = Book.builder().title(BOOT_TEST_TITLE + "3").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book3);

        List<Book> list = bookRepository.findAll();

        assertThat(list, hasSize(3));
        assertThat(list, contains(book1, book2, book3));
    }

    @Test
    public void BookList_저장_삭제_테스트() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE + "1").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder().title(BOOT_TEST_TITLE + "2").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        Book book3 = Book.builder().title(BOOT_TEST_TITLE + "3").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book3);

        bookRepository.deleteAll();

        assertThat(bookRepository.findAll(), IsEmptyCollection.empty());
    }
}
