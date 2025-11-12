package com.senadi.solicitud02.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.PermisoAplicacionDao;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class PermisoAplicacionDaoImpl implements PermisoAplicacionDao {

    @Override
    public void crear(PermisoAplicacion p) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public PermisoAplicacion actualizar(PermisoAplicacion p) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            PermisoAplicacion merged = em.merge(p);
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
            PermisoAplicacion ref = em.find(PermisoAplicacion.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public PermisoAplicacion buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(PermisoAplicacion.class, id); }
        finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM PermisoAplicacion p ORDER BY p.id", PermisoAplicacion.class)
                     .getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacion(Long idAplicacion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.aplicacion.id = :a", PermisoAplicacion.class);
            q.setParameter("a", idAplicacion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.nombre = :n", PermisoAplicacion.class);
            q.setParameter("n", nombre);
            return q.getResultList();
        } finally { em.close(); }
    }
}
