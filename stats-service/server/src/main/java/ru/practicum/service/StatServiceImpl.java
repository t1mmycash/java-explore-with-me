package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.EndpointHit;
import ru.practicum.dto.ViewStats;
import ru.practicum.mapper.StatMapper;
import ru.practicum.model.Stat;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;
    private final StatMapper statMapper;

    @Override
    @Transactional
    public void addEndpointHit(EndpointHit endpointHit) {
        Stat savedStat = statRepository.save(statMapper.mapEndpointHitToStat(endpointHit));
        log.info("Stat was saved: {}", savedStat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewStats> getStats(LocalDateTime start, LocalDateTime end, Boolean isUnique, List<String> uris) {
        Sort sort = Sort.by(Sort.Direction.DESC, "hits");
        if (isUnique) {
            if (uris.isEmpty()) {
                return statRepository.getUniqueIpStats(start, end, sort);
            } else {
                return statRepository.getUniqueIpStatsByUris(start, end, uris, sort);
            }
        } else {
            if (uris.isEmpty()) {
                return statRepository.getStats(start, end, sort);
            } else {
                return statRepository.getStatsByUris(start, end, uris, sort);
            }
        }
    }
}
