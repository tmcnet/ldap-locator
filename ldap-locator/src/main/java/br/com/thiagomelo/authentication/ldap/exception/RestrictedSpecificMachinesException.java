package br.com.thiagomelo.authentication.ldap.exception;

public class RestrictedSpecificMachinesException extends LDAPException {

	private static final long serialVersionUID = 5389214061413076864L;

	public RestrictedSpecificMachinesException(String string, Throwable e) {
		super(string, e);
	}

}
