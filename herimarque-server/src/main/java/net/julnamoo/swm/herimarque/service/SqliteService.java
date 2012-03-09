package net.julnamoo.swm.herimarque.service;

import java.io.File;

public interface SqliteService {

	/**
	 * update the property of dbVersion to "herimarque.properties"
	 * @param version - updated version to override
	 * @return version - updated version value
	 */
	public abstract String saveNewVersion(String version);

	/**
	 * compare userLastVersion to LastVersion
	 * If the system db version is higher then user's then return false otherwise true 
	 * @param version - to be compared version
	 * @return false - If the system version is higher then the client need to download the new version 
	 */
	public abstract boolean itsNew(String version);

	/**
	 * check the user is authenticated then return the db file
	 * @param version
	 * @return
	 */
	public abstract File getDB(String version);

}