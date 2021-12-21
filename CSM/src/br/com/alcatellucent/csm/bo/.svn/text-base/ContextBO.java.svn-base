package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class ContextBO {

	private static final Logger logger = Logger.getLogger(ContextBO.class);

	private HbCommonDAO<Context> contextDao;

	public ContextBO() {
		logger.debug("Carregando Context..");
		contextDao = new HbCommonDAO<Context>(Context.class);
	}

	public void delete(String id) throws ExceptionSys {
		try {
			Context context = (Context) contextDao.findById(id);
			if (context.getParentId().trim().equalsIgnoreCase("0")) {
				throw new ExceptionSys("Context ROOT cannot be deleted");
			}
			contextDao.delete(context);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	public void save(Context context) throws ExceptionSys {
		try {
			if (null == context.getId() || !context.getId().equalsIgnoreCase("")) {
				contextDao.update(context);
				logger.info("Modify information of Context .." + context.getName());
			} else {

				Integer idTree = contextMaxTree();
				context.setTreeOrder(idTree);

				contextDao.save(context);
				logger.info("Save information of Context .." + context.getName());

			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Context> list() throws ExceptionSys {
		Collection<Context> listContext = new ArrayList<Context>();
		listContext = contextDao.findAll();
		return listContext;
	}

	public Context edit(String id) throws ExceptionSys {
		Context context = (Context) contextDao.findById(id);
		return context;
	}

	public Integer contextMaxTree() {
		Collection collection = null;
		Session session = null;

		Integer idTree = 0;
		try {
			String hql = "select max(treeOrder)+1 from  Context";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			collection = query.list();

			Iterator iter = collection.iterator();
			while (iter.hasNext()) {
				Integer elem = (Integer) iter.next();
				idTree = elem;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return idTree;
	}

	/**
	 * Returns a list containing the children of a Context
	 * 
	 * @param initialId
	 *           Root Context to search in
	 * @param recursive
	 *           {@code boolean} indicating if this is a recursive search
	 * @return {@code List<Context>} of the children of a initial Context
	 * @throws ExceptionSys
	 *            Error accessing in the Data access
	 */
	public List<Context> getListContextChildren(String initialId, boolean recursive) throws ExceptionSys {
		List<Context> listContext = new ArrayList<Context>();
		// Get first context itself
		// //////////////////////////////////////////////////
		Context atualContext = (Context) contextDao.findById(initialId);
		listContext.add(atualContext);
		// //////////////////////////////////////////////////////////////////////////
		getListContextByHierarchy(initialId, listContext, recursive);
		return listContext;
	}

	public void getListContextByHierarchy(String initialId, List<Context> listContextsBag, boolean recursive) {
		Session session = null;
		List<Context> listContext = new ArrayList<Context>();
		List<Context> auxList = new ArrayList<Context>();

		String hql = "from Context where parentId ='" + initialId + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listContext = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		for (Context context : listContext) {
			listContextsBag.add(context);
			if (recursive) {
				getListContextByHierarchy(context.getId(), listContextsBag, recursive); // recursive
				// call
			}
		}
	}

	@SuppressWarnings( { "unchecked", "finally" })
	public Context findRoot() throws ExceptionSys {
		Context context = null;
		try {
			Properties criteria = new Properties();
			criteria.put("parentId", "0");

			Collection<Context> cContext = (Collection<Context>) contextDao.findByCriteria(Context.class, criteria);
			for (Context objCtx : cContext) {
				context = objCtx;
			}

		} catch (ExceptionSys e) {
			logger.error("Error finding ROOT CONTEXT ..." + e);
			throw new ExceptionSys(e);
		} finally {
			return null == context ? new Context() : context;
		}
	}
}
