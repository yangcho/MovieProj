package com.project.movieadmin.board.comments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle().....");
		
		String sPath = request.getServletPath();
		
		log.info("sPath:{}",sPath);
		log.info("user_id:{}",session.getAttribute("user_id"));
		
		if(sPath.equals("/m_selectAll.do") 
				|| sPath.equals("/m_update.do")
				|| sPath.equals("/m_updateOK.do")
				|| sPath.equals("/m_delete.do")
				|| sPath.equals("/m_deleteOK.do")
				|| sPath.equals("/m_selectOne.do")
				|| sPath.equals("/m_searchList.do")) {
			
			if(session.getAttribute("user_id")==null) {
				response.sendRedirect("login.do");
				return false;
			}
			
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle().....");
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
