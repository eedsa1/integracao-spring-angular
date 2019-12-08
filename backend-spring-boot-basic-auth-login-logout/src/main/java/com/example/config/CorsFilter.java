//package com.example.config;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//

//Filtro criado para tratar problemas com CORS
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CorsFilter implements Filter {
//
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//		final HttpServletResponse response = (HttpServletResponse) res;
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//	    if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
//	        response.setStatus(HttpServletResponse.SC_OK);
//	    } else {
//	        chain.doFilter(req, res);
//	    }
//    }
//
//    public void init(FilterConfig filterConfig) {
//        // not needed
//    }
//
//    public void destroy() {
//        // not needed
//    }
//
//}