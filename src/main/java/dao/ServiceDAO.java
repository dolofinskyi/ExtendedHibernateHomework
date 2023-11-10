package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceDAO<T> {
    private final Class<T> entityClass;

    public ServiceDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T get(Session session, Object id){
        return session.get(entityClass, id);
    }

    public List<T> getAll(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(entityClass);
        Root<T> root = cr.from(entityClass);
        cr.select(root);
        Query<T> query = session.createQuery(cr);
        return query.getResultList();
    }

    public T getLast(Session session) {
        List<T> entities = getAll(session);
        if (entities.isEmpty()) {
            return null;
        }
        return entities.get(entities.size() - 1);
    }

    public int size(Session session) {
        return getAll(session).size();
    }

    public void add(Session session, T entity) {
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
    }

    public void update(Session session, T entity) {
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    public void delete(Session session, T entity) {
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }
}
