package com.boxedfolder.shubidu.persistence.repository;

import com.boxedfolder.shubidu.persistence.domain.URL;
import org.springframework.data.repository.CrudRepository;

public interface URLRepository extends CrudRepository<URL, Long> {
    URL findUrlByShortLink(String shortLink);
    URL findUrlByLink(String link);
}
