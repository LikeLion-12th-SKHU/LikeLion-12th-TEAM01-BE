package org.skhuton.fitpete.auth.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// 페이지네이션

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableUtil {
    public static Pageable of(int oneBasedPage, int size) {
        return PageRequest.of(oneBasedPage, size);
    }

    public static Pageable of(int oneBasedPage, int size, Sort sort) {
        return PageRequest.of(oneBasedPage, size, sort);
    }
}
