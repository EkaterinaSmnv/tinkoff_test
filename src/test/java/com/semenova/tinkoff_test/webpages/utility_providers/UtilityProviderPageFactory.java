/**
 * 
 */
package com.semenova.tinkoff_test.webpages.utility_providers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;


/**
 * @author semenova
 *
 */
public abstract class UtilityProviderPageFactory {
	
	private static final Map<String, UtilityProviderPageFactory> factoriesMap =
			new HashMap<String, UtilityProviderPageFactory>()
	{
		private static final long serialVersionUID = -7018763528122034136L;	

		{
			put("ЖКУ-Москва", new ZHKUMoscowPageFactory());
		}
	};
	
	public static class FactoryNotImplementedException extends Exception {
		public FactoryNotImplementedException() {
			super();
		}

		public FactoryNotImplementedException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public FactoryNotImplementedException(String message, Throwable cause) {
			super(message, cause);
		}

		public FactoryNotImplementedException(String message) {
			super(message);
		}

		public FactoryNotImplementedException(Throwable cause) {
			super(cause);
		}

		private static final long serialVersionUID = -1061388964328825115L;
		
	}
	
	public static UtilityProviderPageFactory getFactory(String title) throws FactoryNotImplementedException {
		UtilityProviderPageFactory factory = factoriesMap.get(title);
		if(factory == null) {
			String message = String.format("Factory for %s not implemented", title);
			throw new FactoryNotImplementedException(message);
		}
		return factory;
	}

	/**
	 * 
	 */
	public UtilityProviderPageFactory() {
		// TODO Auto-generated constructor stub
	}

	abstract public <T extends AbstractUtilityProviderPage> T createPage(WebDriver driver);
}
