package com.senadi.solicitud02.modelo.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.senadi.solicitud02.modelo.dao.AccesoUsuarioDao;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;
import com.senadi.solicitud02.modelo.util.JPAUtil;

public class AccesoUsuarioDaoImpl implements AccesoUsuarioDao {

    @Override
    public void crear(AccesoUsuario a) {
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
    public AccesoUsuario actualizar(AccesoUsuario a) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            AccesoUsuario merged = em.merge(a);
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
            AccesoUsuario ref = em.find(AccesoUsuario.class, id);
            if (ref != null) em.remove(ref);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }

    @Override
    public AccesoUsuario buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(AccesoUsuario.class, id); }
        finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM AccesoUsuario a ORDER BY a.id", AccesoUsuario.class)
                     .getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitud(Long idSolicitud) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.solicitud.id = :s", AccesoUsuario.class);
            q.setParameter("s", idSolicitud);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorPermiso(Long idPermiso) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.permiso.id = :p", AccesoUsuario.class);
            q.setParameter("p", idPermiso);
            return q.getResultList();
        } finally { em.close(); }
    }
    
    @Override
    public List<AccesoUsuario> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.fechaCarga BETWEEN :desde AND :hasta ORDER BY a.fechaCarga", AccesoUsuario.class);
            q.setParameter("desde", java.sql.Timestamp.valueOf(desde));
            q.setParameter("hasta", java.sql.Timestamp.valueOf(hasta));
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudYPermiso(Long idSolicitud, Long idPermiso) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.solicitud.id = :s AND a.permiso.id = :p", AccesoUsuario.class);
            q.setParameter("s", idSolicitud);
            q.setParameter("p", idPermiso);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudYFechas(Long idSolicitud, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.solicitud.id = :s AND a.fechaCarga BETWEEN :desde AND :hasta", AccesoUsuario.class);
            q.setParameter("s", idSolicitud);
            q.setParameter("desde", java.sql.Timestamp.valueOf(desde));
            q.setParameter("hasta", java.sql.Timestamp.valueOf(hasta));
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorPermisoYFechas(Long idPermiso, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.permiso.id = :p AND a.fechaCarga BETWEEN :desde AND :hasta", AccesoUsuario.class);
            q.setParameter("p", idPermiso);
            q.setParameter("desde", java.sql.Timestamp.valueOf(desde));
            q.setParameter("hasta", java.sql.Timestamp.valueOf(hasta));
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudPermisoYFechas(Long idSolicitud, Long idPermiso, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<AccesoUsuario> q = em.createQuery(
                "SELECT a FROM AccesoUsuario a WHERE a.solicitud.id = :s AND a.permiso.id = :p AND a.fechaCarga BETWEEN :desde AND :hasta", AccesoUsuario.class);
            q.setParameter("s", idSolicitud);
            q.setParameter("p", idPermiso);
            q.setParameter("desde", java.sql.Timestamp.valueOf(desde));
            q.setParameter("hasta", java.sql.Timestamp.valueOf(hasta));
            return q.getResultList();
        } finally { em.close(); }
    }

}
