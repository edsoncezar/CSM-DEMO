package br.com.alcatellucent.csm.scheduler.frontend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.scheduler.SchedulerTrafficPropagation;

/**
 * Servlet implementation class for Servlet: SchedulerTrafficPropagationServlet
 */
public class SchedulerTrafficPropagationServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	static final long	serialVersionUID	= 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SchedulerTrafficPropagationServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);
	}

	private void doWork(HttpServletRequest req, HttpServletResponse res) throws ServletException,
			IOException {

		PrintWriter out = null;
		SchedulerTrafficPropagation scheduler = null;
		String schedulerId = null;

		schedulerId = (String) req.getParameter("schedulerId");
		scheduler = new SchedulerTrafficPropagation();
		out = res.getWriter();
		try {
			scheduler.executeScheduler(schedulerId);
			out.print("OK");
		} catch (ExceptionSys e) {
			out.print(e.getMessage());
		}
	}
}