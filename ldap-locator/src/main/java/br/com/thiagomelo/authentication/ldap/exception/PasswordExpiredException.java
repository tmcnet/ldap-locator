package br.com.thiagomelo.authentication.ldap.exception;

public class PasswordExpiredException extends LDAPException {

	private static final long serialVersionUID = 388839101425976425L;

	public PasswordExpiredException(String string, Throwable e) {
		super(string, e);
	}
}
