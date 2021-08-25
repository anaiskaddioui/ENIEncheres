package fr.eni.ENIEncheres.filtres;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class XSSFilter
 */
@WebFilter(filterName = "XSSFilter", urlPatterns = {"/*"}) //Filtre anti-injections html/javascript, dites "XSS" - Source : site KooR
public class XSSFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter( new XssRequestWrapper( (HttpServletRequest) request), response );   //Classe et méthodes de tri/remplacement développées ligne 38
    }

    @Override
    public void destroy() {
    }


    private static class XssRequestWrapper extends HttpServletRequestWrapper {


        private static final Pattern [] XSS_PATTERNS = { //Constante tableau de patterns html ou get
                Pattern.compile( "<.*?>" ), //"?" ==> pour éviter la suppression de toute la chaîne : juste les chevrons
                Pattern.compile( "&.*?;" ),
                Pattern.compile( "%[0-9a-fA-F]*" )
        };
        

        public XssRequestWrapper(HttpServletRequest servletRequest) {
            super(servletRequest);
        }

        @Override
        public String[] getParameterValues( String parameterName ) {
            
            String [] values = super.getParameterValues(parameterName);

            if (values == null) return null;

            int count = values.length;
            for ( int i = 0; i < count; i++ ) {
                // On remplace chaque chaîne de caractères
                values[i] = removeTags(values[i]);
            }

            return values;
        }

        @Override
        public String getParameter( String parameter ) {
            return removeTags( super.getParameter(parameter) );
        }

        @Override
        public String getHeader( String name ) {
            return removeTags( super.getHeader(name) );
        }

        
        private String removeTags( String value ) {
            if ( value != null ) {
                // On retire le code ASCII 0, au cas ou
                value = value.replaceAll( "\0", "" );

                // Supprime l'ensemble de tags et des entités existants
                for ( Pattern pattern : XSS_PATTERNS ) {
                    value = pattern.matcher( value ).replaceAll( "" );
                }
                
                // Au cas où les caractères < et > ne seraient pas en nombres pairs
                value = value.replaceAll( "<", "" );
                value = value.replaceAll( ">", "" );
            }
            return value;
        }
    }
}