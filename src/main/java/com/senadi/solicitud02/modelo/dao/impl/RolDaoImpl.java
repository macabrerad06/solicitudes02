package com.senadi.solicitud02.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.RolDao;
import com.senadi.solicitud02.modelo.entidades.Rol;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class RolDaoImpl implements RolDao {

    @Override
    public void crear(Rol r) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(r);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Rol actualizar(Rol r) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Rol merged = em.merge(r);
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
            Rol ref = em.find(Rol.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Rol buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Rol.class, id); }
        finally { em.close(); }
    }

    @Override
    public Rol buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery(
                "SELECT r FROM Rol r WHERE r.nombre = :n", Rol.class);
            q.setParameter("n", nombre);
            List<Rol> r = q.getResultList();
            return r.isEmpty() ? null : r.get(0);
        } finally { em.close(); }
    }

    @Override
    public List<Rol> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Rol r ORDER BY r.id", Rol.class)
                     .getResultList();
        } finally { em.close(); }
    }
    
    @Override
    public List<Rol> buscarPorDescripcion(String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery(
                "SELECT r FROM Rol r WHERE r.descripcion = :d", Rol.class);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Rol> buscarPorNombreODescripcion(String texto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Rol> q = em.createQuery(
                "SELECT r FROM Rol r WHERE r.nombre LIKE :t OR r.descripcion LIKE :t", Rol.class);
            q.setParameter("t", "%" + texto + "%");
            return q.getResultList();
        } finally { em.close(); }
    }

}
