package br.com.thiagomelo.authentication.ldap.exception;

public class LDAPInvalidCredentialsException extends LDAPException {

	private static final long serialVersionUID = -3422728195965093714L;
	
	public LDAPInvalidCredentialsException(String string, Throwable e) {
		super(string, e);
	}
}
