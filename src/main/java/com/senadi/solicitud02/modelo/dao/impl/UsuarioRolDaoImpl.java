package com.senadi.solicitud02.modelo.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.UsuarioRolDao;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class UsuarioRolDaoImpl implements UsuarioRolDao {

    @Override
    public void crear(UsuarioRol ur) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ur);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public UsuarioRol actualizar(UsuarioRol ur) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            UsuarioRol merged = em.merge(ur);
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
            UsuarioRol ref = em.find(UsuarioRol.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public UsuarioRol buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(UsuarioRol.class, id); }
        finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT ur FROM UsuarioRol ur ORDER BY ur.id", UsuarioRol.class)
                     .getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> buscarPorUsuario(Long idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.usuario.id = :u", UsuarioRol.class);
            q.setParameter("u", idUsuario);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> buscarPorRol(Long idRol) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.rol.id = :r", UsuarioRol.class);
            q.setParameter("r", idRol);
            return q.getResultList();
        } finally { em.close(); }
    }
    
    @Override
    public List<UsuarioRol> buscarPorUsuarioYRol(Long idUsuario, Long idRol) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.usuario.id = :u AND ur.rol.id = :r", UsuarioRol.class);
            q.setParameter("u", idUsuario);
            q.setParameter("r", idRol);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.fechaAsignacion BETWEEN :desde AND :hasta ORDER BY ur.fechaAsignacion", UsuarioRol.class);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> buscarPorUsuarioConFecha(Long idUsuario, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.usuario.id = :u AND ur.fechaAsignacion BETWEEN :desde AND :hasta", UsuarioRol.class);
            q.setParameter("u", idUsuario);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<UsuarioRol> buscarPorRolConFecha(Long idRol, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<UsuarioRol> q = em.createQuery(
                "SELECT ur FROM UsuarioRol ur WHERE ur.rol.id = :r AND ur.fechaAsignacion BETWEEN :desde AND :hasta", UsuarioRol.class);
            q.setParameter("r", idRol);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }

}
