/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.Alumno;
import Logica_Negocio.Grado;
import Logica_Negocio.Turno;
import Logica_Negocio.AlumnoProfesor;
import Logica_Negocio.Matricula;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoseM
 */
public class MatriculaJpaController implements Serializable {

    public MatriculaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Matricula matricula) throws PreexistingEntityException, Exception {
        if (matricula.getAlumnoProfesorList() == null) {
            matricula.setAlumnoProfesorList(new ArrayList<AlumnoProfesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno idAlumno = matricula.getIdAlumno();
            if (idAlumno != null) {
                idAlumno = em.getReference(idAlumno.getClass(), idAlumno.getIdAlumno());
                matricula.setIdAlumno(idAlumno);
            }
            Grado idGrado = matricula.getIdGrado();
            if (idGrado != null) {
                idGrado = em.getReference(idGrado.getClass(), idGrado.getIdGrado());
                matricula.setIdGrado(idGrado);
            }
            Turno idTurno = matricula.getIdTurno();
            if (idTurno != null) {
                idTurno = em.getReference(idTurno.getClass(), idTurno.getIdTurno());
                matricula.setIdTurno(idTurno);
            }
            List<AlumnoProfesor> attachedAlumnoProfesorList = new ArrayList<AlumnoProfesor>();
            for (AlumnoProfesor alumnoProfesorListAlumnoProfesorToAttach : matricula.getAlumnoProfesorList()) {
                alumnoProfesorListAlumnoProfesorToAttach = em.getReference(alumnoProfesorListAlumnoProfesorToAttach.getClass(), alumnoProfesorListAlumnoProfesorToAttach.getIdAlumnoProfesor());
                attachedAlumnoProfesorList.add(alumnoProfesorListAlumnoProfesorToAttach);
            }
            matricula.setAlumnoProfesorList(attachedAlumnoProfesorList);
            em.persist(matricula);
            if (idAlumno != null) {
                idAlumno.getMatriculaList().add(matricula);
                idAlumno = em.merge(idAlumno);
            }
            if (idGrado != null) {
                idGrado.getMatriculaList().add(matricula);
                idGrado = em.merge(idGrado);
            }
            if (idTurno != null) {
                idTurno.getMatriculaList().add(matricula);
                idTurno = em.merge(idTurno);
            }
            for (AlumnoProfesor alumnoProfesorListAlumnoProfesor : matricula.getAlumnoProfesorList()) {
                Matricula oldIdMatriculaOfAlumnoProfesorListAlumnoProfesor = alumnoProfesorListAlumnoProfesor.getIdMatricula();
                alumnoProfesorListAlumnoProfesor.setIdMatricula(matricula);
                alumnoProfesorListAlumnoProfesor = em.merge(alumnoProfesorListAlumnoProfesor);
                if (oldIdMatriculaOfAlumnoProfesorListAlumnoProfesor != null) {
                    oldIdMatriculaOfAlumnoProfesorListAlumnoProfesor.getAlumnoProfesorList().remove(alumnoProfesorListAlumnoProfesor);
                    oldIdMatriculaOfAlumnoProfesorListAlumnoProfesor = em.merge(oldIdMatriculaOfAlumnoProfesorListAlumnoProfesor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMatricula(matricula.getIdMatricula()) != null) {
                throw new PreexistingEntityException("Matricula " + matricula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Matricula matricula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula persistentMatricula = em.find(Matricula.class, matricula.getIdMatricula());
            Alumno idAlumnoOld = persistentMatricula.getIdAlumno();
            Alumno idAlumnoNew = matricula.getIdAlumno();
            Grado idGradoOld = persistentMatricula.getIdGrado();
            Grado idGradoNew = matricula.getIdGrado();
            Turno idTurnoOld = persistentMatricula.getIdTurno();
            Turno idTurnoNew = matricula.getIdTurno();
            List<AlumnoProfesor> alumnoProfesorListOld = persistentMatricula.getAlumnoProfesorList();
            List<AlumnoProfesor> alumnoProfesorListNew = matricula.getAlumnoProfesorList();
            List<String> illegalOrphanMessages = null;
            for (AlumnoProfesor alumnoProfesorListOldAlumnoProfesor : alumnoProfesorListOld) {
                if (!alumnoProfesorListNew.contains(alumnoProfesorListOldAlumnoProfesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AlumnoProfesor " + alumnoProfesorListOldAlumnoProfesor + " since its idMatricula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAlumnoNew != null) {
                idAlumnoNew = em.getReference(idAlumnoNew.getClass(), idAlumnoNew.getIdAlumno());
                matricula.setIdAlumno(idAlumnoNew);
            }
            if (idGradoNew != null) {
                idGradoNew = em.getReference(idGradoNew.getClass(), idGradoNew.getIdGrado());
                matricula.setIdGrado(idGradoNew);
            }
            if (idTurnoNew != null) {
                idTurnoNew = em.getReference(idTurnoNew.getClass(), idTurnoNew.getIdTurno());
                matricula.setIdTurno(idTurnoNew);
            }
            List<AlumnoProfesor> attachedAlumnoProfesorListNew = new ArrayList<AlumnoProfesor>();
            for (AlumnoProfesor alumnoProfesorListNewAlumnoProfesorToAttach : alumnoProfesorListNew) {
                alumnoProfesorListNewAlumnoProfesorToAttach = em.getReference(alumnoProfesorListNewAlumnoProfesorToAttach.getClass(), alumnoProfesorListNewAlumnoProfesorToAttach.getIdAlumnoProfesor());
                attachedAlumnoProfesorListNew.add(alumnoProfesorListNewAlumnoProfesorToAttach);
            }
            alumnoProfesorListNew = attachedAlumnoProfesorListNew;
            matricula.setAlumnoProfesorList(alumnoProfesorListNew);
            matricula = em.merge(matricula);
            if (idAlumnoOld != null && !idAlumnoOld.equals(idAlumnoNew)) {
                idAlumnoOld.getMatriculaList().remove(matricula);
                idAlumnoOld = em.merge(idAlumnoOld);
            }
            if (idAlumnoNew != null && !idAlumnoNew.equals(idAlumnoOld)) {
                idAlumnoNew.getMatriculaList().add(matricula);
                idAlumnoNew = em.merge(idAlumnoNew);
            }
            if (idGradoOld != null && !idGradoOld.equals(idGradoNew)) {
                idGradoOld.getMatriculaList().remove(matricula);
                idGradoOld = em.merge(idGradoOld);
            }
            if (idGradoNew != null && !idGradoNew.equals(idGradoOld)) {
                idGradoNew.getMatriculaList().add(matricula);
                idGradoNew = em.merge(idGradoNew);
            }
            if (idTurnoOld != null && !idTurnoOld.equals(idTurnoNew)) {
                idTurnoOld.getMatriculaList().remove(matricula);
                idTurnoOld = em.merge(idTurnoOld);
            }
            if (idTurnoNew != null && !idTurnoNew.equals(idTurnoOld)) {
                idTurnoNew.getMatriculaList().add(matricula);
                idTurnoNew = em.merge(idTurnoNew);
            }
            for (AlumnoProfesor alumnoProfesorListNewAlumnoProfesor : alumnoProfesorListNew) {
                if (!alumnoProfesorListOld.contains(alumnoProfesorListNewAlumnoProfesor)) {
                    Matricula oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor = alumnoProfesorListNewAlumnoProfesor.getIdMatricula();
                    alumnoProfesorListNewAlumnoProfesor.setIdMatricula(matricula);
                    alumnoProfesorListNewAlumnoProfesor = em.merge(alumnoProfesorListNewAlumnoProfesor);
                    if (oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor != null && !oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor.equals(matricula)) {
                        oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor.getAlumnoProfesorList().remove(alumnoProfesorListNewAlumnoProfesor);
                        oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor = em.merge(oldIdMatriculaOfAlumnoProfesorListNewAlumnoProfesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = matricula.getIdMatricula();
                if (findMatricula(id) == null) {
                    throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Matricula matricula;
            try {
                matricula = em.getReference(Matricula.class, id);
                matricula.getIdMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matricula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AlumnoProfesor> alumnoProfesorListOrphanCheck = matricula.getAlumnoProfesorList();
            for (AlumnoProfesor alumnoProfesorListOrphanCheckAlumnoProfesor : alumnoProfesorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Matricula (" + matricula + ") cannot be destroyed since the AlumnoProfesor " + alumnoProfesorListOrphanCheckAlumnoProfesor + " in its alumnoProfesorList field has a non-nullable idMatricula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Alumno idAlumno = matricula.getIdAlumno();
            if (idAlumno != null) {
                idAlumno.getMatriculaList().remove(matricula);
                idAlumno = em.merge(idAlumno);
            }
            Grado idGrado = matricula.getIdGrado();
            if (idGrado != null) {
                idGrado.getMatriculaList().remove(matricula);
                idGrado = em.merge(idGrado);
            }
            Turno idTurno = matricula.getIdTurno();
            if (idTurno != null) {
                idTurno.getMatriculaList().remove(matricula);
                idTurno = em.merge(idTurno);
            }
            em.remove(matricula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Matricula> findMatriculaEntities() {
        return findMatriculaEntities(true, -1, -1);
    }

    public List<Matricula> findMatriculaEntities(int maxResults, int firstResult) {
        return findMatriculaEntities(false, maxResults, firstResult);
    }

    private List<Matricula> findMatriculaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Matricula.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Matricula findMatricula(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Matricula.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Matricula> rt = cq.from(Matricula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
