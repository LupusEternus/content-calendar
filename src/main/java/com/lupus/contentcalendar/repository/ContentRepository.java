package com.lupus.contentcalendar.repository;

import com.lupus.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
}
