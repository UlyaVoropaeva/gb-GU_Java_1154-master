package ru.gb.mall.configuration;


//@Component
public class CustomAccessDeniedHandler /* implements AccessDeniedHandler*/ {
/*
    private static Logger LOG = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warn("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
*/
}
