package com.senadi.solicitud02.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.AuditoriaDao;
import com.senadi.solicitud02.modelo.entidades.Auditoria;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class AuditoriaDaoImpl implements AuditoriaDao {

    @Override
    public void crear(Auditoria a) {
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
    public Auditoria actualizar(Auditoria a) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Auditoria merged = em.merge(a);
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
            Auditoria ref = em.find(Auditoria.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public Auditoria buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Auditoria.class, id); }
        finally { em.close(); }
    }

    @Override
    public List<Auditoria> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Auditoria a ORDER BY a.id", Auditoria.class)
                     .getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Auditoria> buscarPorUsuario(Long idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Auditoria> q = em.createQuery(
                "SELECT a FROM Auditoria a WHERE a.usuario.id = :u", Auditoria.class);
            q.setParameter("u", idUsuario);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Auditoria> buscarPorAccion(String accion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Auditoria> q = em.createQuery(
                "SELECT a FROM Auditoria a WHERE a.accion = :ac", Auditoria.class);
            q.setParameter("ac", accion);
            return q.getResultList();
        } finally { em.close(); }
    }
}
