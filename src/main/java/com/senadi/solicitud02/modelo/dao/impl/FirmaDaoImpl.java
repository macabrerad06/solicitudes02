package com.senadi.solicitud02.modelo.dao.impl;

import java.time.LocalDateTime;
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

    // Devuelve todas las firmas de una solicitud (1:N)
    @Override
    public List<Firma> buscarPorSolicitud(Long idSolicitud) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.solicitud.id = :s ORDER BY f.fechaFirma", Firma.class);
            q.setParameter("s", idSolicitud);
            return q.getResultList();
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

    @Override
    public List<Firma> buscarPorDescripcion(String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.descripcion = :d", Firma.class);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Firma> buscarPorFecha(LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.fechaFirma BETWEEN :desde AND :hasta ORDER BY f.fechaFirma", 
                Firma.class);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Firma> buscarPorSolicitudYFecha(Long idSolicitud, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.solicitud.id = :s AND f.fechaFirma BETWEEN :desde AND :hasta ORDER BY f.fechaFirma",
                Firma.class);
            q.setParameter("s", idSolicitud);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Firma> buscarPorSolicitudYDescripcion(Long idSolicitud, String descripcion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.solicitud.id = :s AND f.descripcion = :d ORDER BY f.fechaFirma",
                Firma.class);
            q.setParameter("s", idSolicitud);
            q.setParameter("d", descripcion);
            return q.getResultList();
        } finally { em.close(); }
    }

    @Override
    public List<Firma> buscarPorDescripcionYFecha(String descripcion, LocalDateTime desde, LocalDateTime hasta) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Firma> q = em.createQuery(
                "SELECT f FROM Firma f WHERE f.descripcion = :d AND f.fechaFirma BETWEEN :desde AND :hasta ORDER BY f.fechaFirma",
                Firma.class);
            q.setParameter("d", descripcion);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        } finally { em.close(); }
    }
}
