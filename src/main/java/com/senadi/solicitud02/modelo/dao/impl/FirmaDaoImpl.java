package com.senadi.solicitud02.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.FirmaDao;
import com.senadi.solicitud02.modelo.entidades.Firma;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class FirmaDaoImpl implements FirmaDao {

    @Override
    public void crear(Firma f) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(f);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Firma actualizar(Firma f) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Firma merged = em.merge(f);
            tx.commit();
            return merged;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Firma ref = em.find(Firma.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Firma buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Firma.class, id); }
        finally { em.close(); }
    }

    @Override
    public Firma buscarPorSolicitud(Long idSolicitud) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.solicitud.id = :s", Firma.class);
            q.setParameter("s", idSolicitud);
            List<Firma> r = q.getResultList();
            return r.isEmpty() ? null : r.get(0);
        } finally { em.close(); }
    }

    @Override
    public List<Firma> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM Firma f ORDER BY f.id", Firma.class)
                     .getResultList();
        } finally { em.close(); }
    }
}
