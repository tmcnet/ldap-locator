package br.com.thiagomelo.authentication.ldap.constants;

import java.util.ResourceBundle;

/**
 * Class with constants that contains the configuration data from the LDAP server.
 * @author Thiago Melo | Tech For TI
 *
 */
public class LDAPParametersConfig {
	
	private static ResourceBundle adConfiguration;
	private static final String BUNDLE_AD_CONFIGURATION = "ad_configuration";

	/**
	 * Implementação do InitialContext para LDAP
	 */
	public static final String INITIAL_CTX = "com.sun.jndi.ldap.LdapCtxFactory";

	/**
	 * Servidor LDAP
	 */
	public static final String SERVIDOR = getAdConfiguration().getString("ad.server");

	/**
	 * Base de Busca
	 */
	public static final String SEARCHBASE = getAdConfiguration().getString("ad.searchbase");

	/**
	 * Top Level do Diretório (Base DN)
	 */
	public static final String BASE_DN = getAdConfiguration().getString("ad.basedn");
	
	/**
	 * User ADM
	 */
	public static final String USER_ADM = getAdConfiguration().getString("ad.useradm");
	
	/**
	 * User ADM Password
	 */
	public static final String USER_ADM_Password = getAdConfiguration().getString("ad.useradm.password");
	
	/**
	 * Obtém o ResourceBundle
	 * @return ResourceBundle
	 */
	private static ResourceBundle getAdConfiguration() {
		if (adConfiguration == null) {
			adConfiguration = ResourceBundle.getBundle(BUNDLE_AD_CONFIGURATION);
		}
		return adConfiguration;
	}
}
