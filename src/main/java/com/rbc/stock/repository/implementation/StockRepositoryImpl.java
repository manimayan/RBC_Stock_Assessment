package com.rbc.stock.repository.implementation;

import com.rbc.stock.model.StockPrediction;
import com.rbc.stock.repository.StockRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Manimaran Palani
 * @since 16-Feb-2023
 */

@Repository
public class StockRepositoryImpl implements StockRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(StockPrediction stock) {
        entityManager.persist(stock);
    }

    @Override
    public List<StockPrediction> getStockByTicker(String stockTicker) {
        TypedQuery<StockPrediction> query = entityManager.createQuery("from StockPrediction WHERE stockId.stock =:stock", StockPrediction.class);
        query.setParameter("stock", stockTicker);
        return query.getResultList();
    }

    @Override
    public Optional<StockPrediction> findByStockAndDate(StockPrediction stockInputData) {

        TypedQuery<StockPrediction> query = entityManager.createQuery("SELECT stockRecord FROM StockPrediction stockRecord " +
                "WHERE stockId.stock =:stock AND stockId.date =:date", StockPrediction.class);
        query.setParameter("stock", stockInputData.getStockId().getStock());
        query.setParameter("date", stockInputData.getStockId().getDate());
        return query.getResultList().stream().findFirst();
    }
}
