/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica_Negocio.TipoUsuario;
import Logica_Negocio.TelefonoUsuario;
import java.util.ArrayList;
import java.util.List;
import Logica_Negocio.UsuarioGrado;
import Logica_Negocio.MateriaUsuario;
import Logica_Negocio.Usuario;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JoseM
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController( ) {
         this.emf = Persistence.createEntityManagerFactory("esucelaProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getTelefonoUsuarioList() == null) {
            usuario.setTelefonoUsuarioList(new ArrayList<TelefonoUsuario>());
        }
        if (usuario.getUsuarioGradoList() == null) {
            usuario.setUsuarioGradoList(new ArrayList<UsuarioGrado>());
        }
        if (usuario.getMateriaUsuarioList() == null) {
            usuario.setMateriaUsuarioList(new ArrayList<MateriaUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
                usuario.setIdTipo(idTipo);
            }
            List<TelefonoUsuario> attachedTelefonoUsuarioList = new ArrayList<TelefonoUsuario>();
            for (TelefonoUsuario telefonoUsuarioListTelefonoUsuarioToAttach : usuario.getTelefonoUsuarioList()) {
                telefonoUsuarioListTelefonoUsuarioToAttach = em.getReference(telefonoUsuarioListTelefonoUsuarioToAttach.getClass(), telefonoUsuarioListTelefonoUsuarioToAttach.getIdTelefono());
                attachedTelefonoUsuarioList.add(telefonoUsuarioListTelefonoUsuarioToAttach);
            }
            usuario.setTelefonoUsuarioList(attachedTelefonoUsuarioList);
            List<UsuarioGrado> attachedUsuarioGradoList = new ArrayList<UsuarioGrado>();
            for (UsuarioGrado usuarioGradoListUsuarioGradoToAttach : usuario.getUsuarioGradoList()) {
                usuarioGradoListUsuarioGradoToAttach = em.getReference(usuarioGradoListUsuarioGradoToAttach.getClass(), usuarioGradoListUsuarioGradoToAttach.getIdUsuarioGrado());
                attachedUsuarioGradoList.add(usuarioGradoListUsuarioGradoToAttach);
            }
            usuario.setUsuarioGradoList(attachedUsuarioGradoList);
            List<MateriaUsuario> attachedMateriaUsuarioList = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuarioListMateriaUsuario1ToAttach : usuario.getMateriaUsuarioList()) {
                materiaUsuarioListMateriaUsuario1ToAttach = em.getReference(materiaUsuarioListMateriaUsuario1ToAttach.getClass(), materiaUsuarioListMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuarioList.add(materiaUsuarioListMateriaUsuario1ToAttach);
            }
            usuario.setMateriaUsuarioList(attachedMateriaUsuarioList);
            em.persist(usuario);
            if (idTipo != null) {
                idTipo.getUsuarioList().add(usuario);
                idTipo = em.merge(idTipo);
            }
            for (TelefonoUsuario telefonoUsuarioListTelefonoUsuario : usuario.getTelefonoUsuarioList()) {
                Usuario oldIdUsuarioOfTelefonoUsuarioListTelefonoUsuario = telefonoUsuarioListTelefonoUsuario.getIdUsuario();
                telefonoUsuarioListTelefonoUsuario.setIdUsuario(usuario);
                telefonoUsuarioListTelefonoUsuario = em.merge(telefonoUsuarioListTelefonoUsuario);
                if (oldIdUsuarioOfTelefonoUsuarioListTelefonoUsuario != null) {
                    oldIdUsuarioOfTelefonoUsuarioListTelefonoUsuario.getTelefonoUsuarioList().remove(telefonoUsuarioListTelefonoUsuario);
                    oldIdUsuarioOfTelefonoUsuarioListTelefonoUsuario = em.merge(oldIdUsuarioOfTelefonoUsuarioListTelefonoUsuario);
                }
            }
            for (UsuarioGrado usuarioGradoListUsuarioGrado : usuario.getUsuarioGradoList()) {
                Usuario oldIdUsuarioOfUsuarioGradoListUsuarioGrado = usuarioGradoListUsuarioGrado.getIdUsuario();
                usuarioGradoListUsuarioGrado.setIdUsuario(usuario);
                usuarioGradoListUsuarioGrado = em.merge(usuarioGradoListUsuarioGrado);
                if (oldIdUsuarioOfUsuarioGradoListUsuarioGrado != null) {
                    oldIdUsuarioOfUsuarioGradoListUsuarioGrado.getUsuarioGradoList().remove(usuarioGradoListUsuarioGrado);
                    oldIdUsuarioOfUsuarioGradoListUsuarioGrado = em.merge(oldIdUsuarioOfUsuarioGradoListUsuarioGrado);
                }
            }
            for (MateriaUsuario materiaUsuarioListMateriaUsuario1 : usuario.getMateriaUsuarioList()) {
                Usuario oldIdUsuarioOfMateriaUsuarioListMateriaUsuario1 = materiaUsuarioListMateriaUsuario1.getIdUsuario();
                materiaUsuarioListMateriaUsuario1.setIdUsuario(usuario);
                materiaUsuarioListMateriaUsuario1 = em.merge(materiaUsuarioListMateriaUsuario1);
                if (oldIdUsuarioOfMateriaUsuarioListMateriaUsuario1 != null) {
                    oldIdUsuarioOfMateriaUsuarioListMateriaUsuario1.getMateriaUsuarioList().remove(materiaUsuarioListMateriaUsuario1);
                    oldIdUsuarioOfMateriaUsuarioListMateriaUsuario1 = em.merge(oldIdUsuarioOfMateriaUsuarioListMateriaUsuario1);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            TipoUsuario idTipoOld = persistentUsuario.getIdTipo();
            TipoUsuario idTipoNew = usuario.getIdTipo();
            List<TelefonoUsuario> telefonoUsuarioListOld = persistentUsuario.getTelefonoUsuarioList();
            List<TelefonoUsuario> telefonoUsuarioListNew = usuario.getTelefonoUsuarioList();
            List<UsuarioGrado> usuarioGradoListOld = persistentUsuario.getUsuarioGradoList();
            List<UsuarioGrado> usuarioGradoListNew = usuario.getUsuarioGradoList();
            List<MateriaUsuario> materiaUsuarioListOld = persistentUsuario.getMateriaUsuarioList();
            List<MateriaUsuario> materiaUsuarioListNew = usuario.getMateriaUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (TelefonoUsuario telefonoUsuarioListOldTelefonoUsuario : telefonoUsuarioListOld) {
                if (!telefonoUsuarioListNew.contains(telefonoUsuarioListOldTelefonoUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TelefonoUsuario " + telefonoUsuarioListOldTelefonoUsuario + " since its idUsuario field is not nullable.");
                }
            }
            for (UsuarioGrado usuarioGradoListOldUsuarioGrado : usuarioGradoListOld) {
                if (!usuarioGradoListNew.contains(usuarioGradoListOldUsuarioGrado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioGrado " + usuarioGradoListOldUsuarioGrado + " since its idUsuario field is not nullable.");
                }
            }
            for (MateriaUsuario materiaUsuarioListOldMateriaUsuario1 : materiaUsuarioListOld) {
                if (!materiaUsuarioListNew.contains(materiaUsuarioListOldMateriaUsuario1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MateriaUsuario1 " + materiaUsuarioListOldMateriaUsuario1 + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
                usuario.setIdTipo(idTipoNew);
            }
            List<TelefonoUsuario> attachedTelefonoUsuarioListNew = new ArrayList<TelefonoUsuario>();
            for (TelefonoUsuario telefonoUsuarioListNewTelefonoUsuarioToAttach : telefonoUsuarioListNew) {
                telefonoUsuarioListNewTelefonoUsuarioToAttach = em.getReference(telefonoUsuarioListNewTelefonoUsuarioToAttach.getClass(), telefonoUsuarioListNewTelefonoUsuarioToAttach.getIdTelefono());
                attachedTelefonoUsuarioListNew.add(telefonoUsuarioListNewTelefonoUsuarioToAttach);
            }
            telefonoUsuarioListNew = attachedTelefonoUsuarioListNew;
            usuario.setTelefonoUsuarioList(telefonoUsuarioListNew);
            List<UsuarioGrado> attachedUsuarioGradoListNew = new ArrayList<UsuarioGrado>();
            for (UsuarioGrado usuarioGradoListNewUsuarioGradoToAttach : usuarioGradoListNew) {
                usuarioGradoListNewUsuarioGradoToAttach = em.getReference(usuarioGradoListNewUsuarioGradoToAttach.getClass(), usuarioGradoListNewUsuarioGradoToAttach.getIdUsuarioGrado());
                attachedUsuarioGradoListNew.add(usuarioGradoListNewUsuarioGradoToAttach);
            }
            usuarioGradoListNew = attachedUsuarioGradoListNew;
            usuario.setUsuarioGradoList(usuarioGradoListNew);
            List<MateriaUsuario> attachedMateriaUsuarioListNew = new ArrayList<MateriaUsuario>();
            for (MateriaUsuario materiaUsuarioListNewMateriaUsuario1ToAttach : materiaUsuarioListNew) {
                materiaUsuarioListNewMateriaUsuario1ToAttach = em.getReference(materiaUsuarioListNewMateriaUsuario1ToAttach.getClass(), materiaUsuarioListNewMateriaUsuario1ToAttach.getIdMatusu());
                attachedMateriaUsuarioListNew.add(materiaUsuarioListNewMateriaUsuario1ToAttach);
            }
            materiaUsuarioListNew = attachedMateriaUsuarioListNew;
            usuario.setMateriaUsuarioList(materiaUsuarioListNew);
            usuario = em.merge(usuario);
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getUsuarioList().remove(usuario);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getUsuarioList().add(usuario);
                idTipoNew = em.merge(idTipoNew);
            }
            for (TelefonoUsuario telefonoUsuarioListNewTelefonoUsuario : telefonoUsuarioListNew) {
                if (!telefonoUsuarioListOld.contains(telefonoUsuarioListNewTelefonoUsuario)) {
                    Usuario oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario = telefonoUsuarioListNewTelefonoUsuario.getIdUsuario();
                    telefonoUsuarioListNewTelefonoUsuario.setIdUsuario(usuario);
                    telefonoUsuarioListNewTelefonoUsuario = em.merge(telefonoUsuarioListNewTelefonoUsuario);
                    if (oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario != null && !oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario.equals(usuario)) {
                        oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario.getTelefonoUsuarioList().remove(telefonoUsuarioListNewTelefonoUsuario);
                        oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario = em.merge(oldIdUsuarioOfTelefonoUsuarioListNewTelefonoUsuario);
                    }
                }
            }
            for (UsuarioGrado usuarioGradoListNewUsuarioGrado : usuarioGradoListNew) {
                if (!usuarioGradoListOld.contains(usuarioGradoListNewUsuarioGrado)) {
                    Usuario oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado = usuarioGradoListNewUsuarioGrado.getIdUsuario();
                    usuarioGradoListNewUsuarioGrado.setIdUsuario(usuario);
                    usuarioGradoListNewUsuarioGrado = em.merge(usuarioGradoListNewUsuarioGrado);
                    if (oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado != null && !oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado.equals(usuario)) {
                        oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado.getUsuarioGradoList().remove(usuarioGradoListNewUsuarioGrado);
                        oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado = em.merge(oldIdUsuarioOfUsuarioGradoListNewUsuarioGrado);
                    }
                }
            }
            for (MateriaUsuario materiaUsuarioListNewMateriaUsuario1 : materiaUsuarioListNew) {
                if (!materiaUsuarioListOld.contains(materiaUsuarioListNewMateriaUsuario1)) {
                    Usuario oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1 = materiaUsuarioListNewMateriaUsuario1.getIdUsuario();
                    materiaUsuarioListNewMateriaUsuario1.setIdUsuario(usuario);
                    materiaUsuarioListNewMateriaUsuario1 = em.merge(materiaUsuarioListNewMateriaUsuario1);
                    if (oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1 != null && !oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1.equals(usuario)) {
                        oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1.getMateriaUsuarioList().remove(materiaUsuarioListNewMateriaUsuario1);
                        oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1 = em.merge(oldIdUsuarioOfMateriaUsuarioListNewMateriaUsuario1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<TelefonoUsuario> telefonoUsuarioListOrphanCheck = usuario.getTelefonoUsuarioList();
            for (TelefonoUsuario telefonoUsuarioListOrphanCheckTelefonoUsuario : telefonoUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the TelefonoUsuario " + telefonoUsuarioListOrphanCheckTelefonoUsuario + " in its telefonoUsuarioList field has a non-nullable idUsuario field.");
            }
            List<UsuarioGrado> usuarioGradoListOrphanCheck = usuario.getUsuarioGradoList();
            for (UsuarioGrado usuarioGradoListOrphanCheckUsuarioGrado : usuarioGradoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the UsuarioGrado " + usuarioGradoListOrphanCheckUsuarioGrado + " in its usuarioGradoList field has a non-nullable idUsuario field.");
            }
            List<MateriaUsuario> materiaUsuarioListOrphanCheck = usuario.getMateriaUsuarioList();
            for (MateriaUsuario materiaUsuarioListOrphanCheckMateriaUsuario1 : materiaUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the MateriaUsuario1 " + materiaUsuarioListOrphanCheckMateriaUsuario1 + " in its materiaUsuarioList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoUsuario idTipo = usuario.getIdTipo();
            if (idTipo != null) {
                idTipo.getUsuarioList().remove(usuario);
                idTipo = em.merge(idTipo);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
