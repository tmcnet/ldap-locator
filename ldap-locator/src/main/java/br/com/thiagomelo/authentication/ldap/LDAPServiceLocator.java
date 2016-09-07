package br.com.thiagomelo.authentication.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import br.com.thiagomelo.authentication.ldap.constants.LDAPExceptionsConstants;
import br.com.thiagomelo.authentication.ldap.constants.LDAPParametersConfig;
import br.com.thiagomelo.authentication.ldap.exception.LDAPException;
import br.com.thiagomelo.authentication.ldap.exception.LDAPInvalidCredentialsException;
import br.com.thiagomelo.authentication.ldap.exception.ProcessClientRequestException;

/**
 * Class responsible for locating an LDAP service to connect to it.
 * 
 * @author Thiago Melo | Tech For TI
 * @version 1.0
 */

public class LDAPServiceLocator {

	protected static LDAPServiceLocator instance;

	private LDAPServiceLocator() {
		super();
	}

	/**
	 * Obtem a mesma instancia de <code>LDAPServiceLocator</code> para todas as
	 * chamadas (Classe singleton)
	 * 
	 * @return LDAPServiceLocator
	 */
	public static LDAPServiceLocator getInstance() {

		if (instance == null) {
			instance = new LDAPServiceLocator();
		}

		return instance;
	}

	public DirContext getDirContext(String principal, String credentials)
			throws LDAPException {

		DirContext dCtx = null;

		try {
			dCtx = new InitialDirContext(getParametros(principal, credentials));
		} catch (AuthenticationException e) {

			// ESPECIFIC LDAP ERRORS
			if (e.getMessage().contains(LDAPExceptionsConstants.lDAP_ERR_49)) {
				throw new LDAPInvalidCredentialsException(
						"INVALID USER OR PASSWORD", e);
			}

			if (e.getMessage().contains(LDAPExceptionsConstants.lDAP_ERR_51)
					|| e.getMessage().contains(
							LDAPExceptionsConstants.lDAP_ERR_52)
					|| e.getMessage().contains(
							LDAPExceptionsConstants.lDAP_ERR_53)) {
				throw new ProcessClientRequestException(
						"LDAP SERVER BUSY OR UNAVAILABLE", e);
			}

		} catch (NamingException ne) {
			throw new LDAPException(ne.getCause().toString(), ne);
		}

		return dCtx;
	}

	public DirContext getDirContext(String principal) throws LDAPException {

		DirContext dCtx = null;

		dCtx = getDirContext(principal, null);

		return dCtx;
	}

	public LdapContext getLdapContext(String principal, String credentials)
			throws LDAPException {
		LdapContext lCtx = null;

		Control[] connCtls = null;
		try {
			lCtx = new InitialLdapContext(
					getParametros(principal, credentials), connCtls);
		} catch (AuthenticationException e) {

			// ESPECIFIC LDAP ERRORS
			if (e.getMessage().contains(LDAPExceptionsConstants.lDAP_ERR_49)) {
				throw new LDAPInvalidCredentialsException(
						"INVALID USER OR PASSWORD", e);
			}

			if (e.getMessage().contains(LDAPExceptionsConstants.lDAP_ERR_51)
					|| e.getMessage().contains(
							LDAPExceptionsConstants.lDAP_ERR_52)
					|| e.getMessage().contains(
							LDAPExceptionsConstants.lDAP_ERR_53)) {
				throw new ProcessClientRequestException(
						"LDAP SERVER BUSY OR UNAVAILABLE", e);
			}
		} catch (NamingException ne) {
			throw new LDAPException(ne.getCause().toString(), ne);
		}
		return lCtx;
	}

	public LdapContext getLdapContext(String principal) throws LDAPException {
		return getLdapContext(principal, null);

	}

	private Hashtable<String, String> getParametros(String principal,
			String credentials) {
		Hashtable<String, String> env = new Hashtable<String, String>(2);

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");

		env.put(Context.PROVIDER_URL, LDAPParametersConfig.SERVIDOR);

		if (credentials != null) {
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, principal);
			env.put(Context.SECURITY_CREDENTIALS, credentials);
		} else {
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, LDAPParametersConfig.USER_ADM);
			env.put(Context.SECURITY_CREDENTIALS,
					LDAPParametersConfig.USER_ADM_Password);
		}

		return env;
	}
}