package br.com.thiagomelo.authentication.ldap.service;

import java.util.ResourceBundle;

import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

import br.com.thiagomelo.authentication.ldap.exception.LDAPException;

/**
 * Performs authentications using LDAP and search for users.
 * @author Thiago Melo | Tech For TI
 *
 */
public interface LDAPAuthenticationService {
	
	/**
	 * Method performs a authentication using LDAP protocol in AD.
	 * @param psUsuario
	 * @param credentials
	 * @param resourceBundle
	 * @throws LDAPException
	 */
	public void authenticateLDAP(final String psUsuario,
			final String credentials, final ResourceBundle resourceBundle)
			throws LDAPException;
	
	/**
	 * Use this method to search a specific user that do not contains password.
	 * @param filter
	 * @param resourceBundle
	 * @param dctx
	 * @param lctx
	 * @return
	 * @throws LDAPException
	 */
	public boolean searchUser(String filter, ResourceBundle resourceBundle,
			DirContext dctx, LdapContext lctx)
			throws LDAPException;

}
