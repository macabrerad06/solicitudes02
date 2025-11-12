package com.senadi.solicitud02.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.AplicacionDao;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class AplicacionDaoImpl implements AplicacionDao {

    @Override
    public void crear(Aplicacion a) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Aplicacion actualizar(Aplicacion a) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Aplicacion merged = em.merge(a);
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
            Aplicacion ref = em.find(Aplicacion.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Aplicacion buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Aplicacion.class, id); }
        finally { em.close(); }
    }

    @Override
    public Aplicacion buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aplicacion> q = em.createQuery(
                "SELECT a FROM Aplicacion a WHERE a.nombre = :n", Aplicacion.class);
            q.setParameter("n", nombre);
            List<Aplicacion> r = q.getResultList();
            return r.isEmpty() ? null : r.get(0);
        } finally { em.close(); }
    }

    @Override
    public List<Aplicacion> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Aplicacion a ORDER BY a.id", Aplicacion.class)
                     .getResultList();
        } finally { em.close(); }
    }
}
