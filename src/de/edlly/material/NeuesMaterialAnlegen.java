package de.edlly.material;

import java.sql.*;


/**  material.DbHinzu
 * F�gt Material der DB Hinzu
 * 
 * Attribute:
 * int x: X wert des Material
 * int z: Z wer des Materials
 * int yMax: Maximale L�nge des Materials
 * int MaterialSorteId: Sorten Id des gew�hlten Materials
 * Connection SqlConn: Database Connection aus aufgerufenen Klasse
 * 
 * Methoden:
 * Add() F�gt das Material mit den Ausgew�hlten Attributen hinzu.
 * Check() Pr�ft die Aktuellen werden des Objekts.
 * updateVisibly() Ver�ndert die Sichtbarkeit des Materials in der DB
 * 
 * @author Edlly
 *
 */
public class NeuesMaterialAnlegen {
	
	public int x;
	public int z;
	public int yMax;
	public int MaterialSorteId;
	public Connection SqlConn;
	  
	// Konstruktor
	public NeuesMaterialAnlegen(){
		// SQL Connection herstellen
		if(this.SqlConn == null){
			SqlConn = de.edlly.db.SQLiteConnect.dbConnection();
		}
	}
	/**
	 *  F�gt ein neues Material der Datenbank hinzu.
	 * @param x Breite des Material als int
	 * @param z Tiefe der Materials als int
	 * @param yMax Maximale L�nge des Materials als int
	 * @param MaterialSorteId SortenId aus der DB
	 * @return Boolean true bei Erfolg
	 */
	public Boolean Add(int x, int z, int yMax, int MaterialSorteId){
		PreparedStatement pst=null;
		
		if(x == 0){ x = this.x ; }
		if(z == 0){	z = this.z ; }
		if(yMax == 0){ yMax = this.yMax; }
		if(MaterialSorteId == 0){ MaterialSorteId = this.MaterialSorteId ; }
		
		if(x <= 0 ){
			throw new IllegalArgumentException("Die Material gr��e X darf nicht 0 sein.");
		}		
		if(z <= 0 ){
			throw new IllegalArgumentException("Die Material Z gr��e darf nicht 0 sein.");
		}		
		if(yMax <= 0 ){
			throw new IllegalArgumentException("Die Maximale Material l�nge darf nicht 0 sein.");
		}
		
		
		try {
			String query="INSERT INTO Material (\"MaterialSorteId\",\"x\",\"z\",\"yMax\",\"visibly\") VALUES (?1,?2,?3,?4,?5)";
			pst= SqlConn.prepareStatement(query);
			pst.setInt( 1, MaterialSorteId );
			pst.setInt( 2, x );
			pst.setInt( 3, z );
			pst.setInt( 4, yMax );
			pst.setBoolean( 5, true);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException( e);
		}finally{
			try{
				pst.close();		
			}catch(Exception e){
				throw new IllegalArgumentException(e);
			}
		}

		return true;	
	}
	
	
	/**
	 * Ver�ndert die Sichtbarkeit des aktuellen Materials.
	 * @param id id des zu ver�ndernden Materials.
	 * @param visibly welche sichtbarkeit soll gesetzt werden.
	 */
	public void updateVisibly(int id, int visibly){
		
		PreparedStatement pst=null;
		
		if(id == 0){
			throw new IllegalArgumentException("Die Material id darf nicht 0 sein.");
		}		
		if(visibly != 0 & visibly != 1){
			throw new IllegalArgumentException("Die Sichtbarkeit kann nur auf 1 oder 0 gesetzt werden.");
		}	
		
		
		
		try {
		String query="UPDATE \"main\".\"Material\" SET \"visibly\" = ?1 WHERE  \"id\" = " + id;
		pst= SqlConn.prepareStatement(query);
		pst.setInt( 1, visibly);
		pst.executeUpdate();

		} catch (SQLException e) {
			
			throw new IllegalArgumentException( e);
		}finally{
			try{
				
				pst.close();		
			}catch(Exception e){
				throw new IllegalArgumentException(e);
			}
		}	
	}

	
	
}
