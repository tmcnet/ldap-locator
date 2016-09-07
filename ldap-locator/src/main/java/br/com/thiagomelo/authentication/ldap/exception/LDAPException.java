package br.com.thiagomelo.authentication.ldap.exception;

public class LDAPException extends Exception {

	private static final long serialVersionUID = -4678633549232670605L;


	public LDAPException(String string) {
		super(string);
	}
	
	public LDAPException(String string, Throwable e) {
		super(string, e);
	}
}