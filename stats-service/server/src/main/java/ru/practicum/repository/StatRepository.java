package ru.practicum.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.ViewStats;
import ru.practicum.model.Stat;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Integer> {
    @Query("select new ru.practicum.dto.ViewStats(s.app, s.uri, count(s.ip) as hits) " +
            "from Stat as s " +
            "where s.timestamp between ?1 and ?2 " +
            "group by s.app, s.uri ")
    List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query("select new ru.practicum.dto.ViewStats(s.app, s.uri, count(distinct s.ip) as hits) " +
            "from Stat as s " +
            "where s.timestamp between ?1 and ?2 " +
            "group by s.app, s.uri ")
    List<ViewStats> getUniqueIpStats(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query("select new ru.practicum.dto.ViewStats(s.app, s.uri, count(s.ip) as hits) " +
            "from Stat as s " +
            "where s.timestamp between ?1 and ?2 " +
            "and s.uri in ?3 " +
            "group by s.app, s.uri ")
    List<ViewStats> getStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uris, Sort sort);

    @Query("select new ru.practicum.dto.ViewStats(s.app, s.uri, count(distinct s.ip) as hits) " +
            "from Stat as s " +
            "where s.timestamp between ?1 and ?2 " +
            "and s.uri in ?3 " +
            "group by s.app, s.uri ")
    List<ViewStats> getUniqueIpStatsByUris(LocalDateTime start, LocalDateTime end, List<String> uris, Sort sort);
}
