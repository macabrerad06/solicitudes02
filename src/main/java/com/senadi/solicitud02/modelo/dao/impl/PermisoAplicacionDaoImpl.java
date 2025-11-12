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
    
    @Override
    public List<PermisoAplicacion> buscarPorDescripcion(String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.descripcion = :d", PermisoAplicacion.class);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorNombreODescripcion(String texto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.nombre LIKE :t OR p.descripcion LIKE :t", PermisoAplicacion.class);
            q.setParameter("t", "%" + texto + "%");
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionYNombre(Long idAplicacion, String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.aplicacion.id = :a AND p.nombre = :n", PermisoAplicacion.class);
            q.setParameter("a", idAplicacion);
            q.setParameter("n", nombre);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionYDescripcion(Long idAplicacion, String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.aplicacion.id = :a AND p.descripcion = :d", PermisoAplicacion.class);
            q.setParameter("a", idAplicacion);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionNombreYDescripcion(Long idAplicacion, String nombre, String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<PermisoAplicacion> q = em.createQuery(
                "SELECT p FROM PermisoAplicacion p WHERE p.aplicacion.id = :a AND p.nombre = :n AND p.descripcion = :d", PermisoAplicacion.class);
            q.setParameter("a", idAplicacion);
            q.setParameter("n", nombre);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

}
