package br.com.thiagomelo.authentication.ldap;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import br.com.thiagomelo.authentication.ldap.constants.LDAPAttributes;

public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirContext dctx;
		try {
			dctx = LDAPServiceLocator.getInstance().getDirContext(
					"thiago.carvalho@techforti");

			String base = "ou=usuarios,ou=fabrica,ou=techforti,dc=techforti,dc=local";

			SearchControls sc = new SearchControls();
			String[] attributeFilter = { LDAPAttributes.COMMON_NAME, LDAPAttributes.MAIL };
			sc.setReturningAttributes(attributeFilter);
			sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// String filter = "(&(sn=W*)(l=Criteria*))";
			String filter = "(" + LDAPAttributes.SAM_ACCOUNT_NAME + "=thiago.carvalho)";

			@SuppressWarnings("rawtypes")
			NamingEnumeration results = dctx.search(base, filter, sc);
			while (results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();
				Attributes attrs = sr.getAttributes();

				Attribute attr = attrs.get(LDAPAttributes.COMMON_NAME);
				System.out.print(attr.get() + ": ");
				attr = attrs.get(LDAPAttributes.MAIL);
				System.out.println(attr.get());
			}
			dctx.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
