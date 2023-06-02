package com.bulletinBoard.system.bl.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ServiceUtil {

    public static Pageable getPagable(int page, int size) {
        if (page > 0) {
            --page;
        }
        return PageRequest.of(page, size);
    }
}
