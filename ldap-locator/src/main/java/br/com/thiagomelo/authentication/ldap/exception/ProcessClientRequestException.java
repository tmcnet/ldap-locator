package br.com.thiagomelo.authentication.ldap.exception;

public class ProcessClientRequestException extends LDAPException {

	private static final long serialVersionUID = 2925203275714866749L;

	public ProcessClientRequestException(String string, Throwable e) {
		super(string, e);
	}
}
