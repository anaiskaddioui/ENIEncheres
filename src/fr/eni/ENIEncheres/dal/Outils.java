package fr.eni.ENIEncheres.dal;

import java.sql.Date;

public class Outils {

	public Outils() {
	};

	public Date dateSQLEnJavaUtil(java.sql.Date dateSQL) {
		Date date = (Date) new java.util.Date(dateSQL.getTime());
		return date;
	}

	public java.sql.Date dateJavaUtilEnDateSQL(Date date) {
		java.sql.Date dateSQL = new java.sql.Date(date.getTime());
		return dateSQL;
	}

}
