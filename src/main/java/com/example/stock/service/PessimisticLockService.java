package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockService {

    private final StockRepository stockRepository;

    public PessimisticLockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock byIdWithPessimisticLock = stockRepository.findByIdWithPessimisticLock(id);

        byIdWithPessimisticLock.decrease(quantity);

        stockRepository.saveAndFlush(byIdWithPessimisticLock);
    }
}
