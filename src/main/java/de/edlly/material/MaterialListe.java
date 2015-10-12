package de.edlly.material;

import de.edlly.db.*;

/**
 * Abfrage der MaterialDaten für eine Tabelle
 * 
 * @author Edlly java@edlly.de
 *
 */

public class MaterialListe extends Material {

    private boolean ausgeblendetDatenAnzeigen = false;
    private Object[][] materialListeUnformatiert = null;
    private Object[][] materialListeFormatiert = null;

    public MaterialListe(SQLiteConnect sqlConnection) {
        super(sqlConnection);
    }

    public void setAusgeblendetDatenAnzeigen(boolean ausgeblendetDatenAnzeigen) {
        this.ausgeblendetDatenAnzeigen = ausgeblendetDatenAnzeigen;
    }

    public Object[][] getMaterialListe() throws SQLiteException, IllegalArgumentException {

        materialListeAusDatenbankAbrufen();
        return materialListeUnformatiert;
    }

    public Object[][] getMaterialListeFormatiert() throws SQLiteException, IllegalArgumentException {

        materialListeAusDatenbankAbrufen();
        materialListeFormatieren();
        return materialListeFormatiert;
    }

    private void materialListeFormatieren() throws SQLiteException {
        materialListeFormatiert = new Object[materialListeUnformatiert.length][5];
        MaterialSorte materialSorte = new MaterialSorte(getSqlConnection());
        for (int DatensatzCounter = 0; DatensatzCounter != materialListeUnformatiert.length; DatensatzCounter++) {
            materialListeFormatiert[DatensatzCounter][0] = materialListeUnformatiert[DatensatzCounter][0];

            materialListeFormatiert[DatensatzCounter][1] = (String) materialSorte
                    .getMaterialSorteName((Integer) materialListeUnformatiert[DatensatzCounter][1]);

            materialListeFormatiert[DatensatzCounter][2] = (String) ((Integer) materialListeUnformatiert[DatensatzCounter][2]
                    + "x" + (Integer) materialListeUnformatiert[DatensatzCounter][3]);

            materialListeFormatiert[DatensatzCounter][3] = materialListeUnformatiert[DatensatzCounter][4];

            materialListeFormatiert[DatensatzCounter][4] = SQLiteUtil
                    .integerToBoolean((Integer) materialListeUnformatiert[DatensatzCounter][5]);
        }
    }

    private void materialListeAusDatenbankAbrufen() throws SQLiteException, IllegalArgumentException {
        int[] materialIds = new int[0];

        MaterialIds abfrageMaterialIds = new MaterialIds(getSqlConnection());

        abfrageMaterialIds.setAusgeblendetDatenAnzeigen(ausgeblendetDatenAnzeigen);
        materialIds = abfrageMaterialIds.getIdListe();

        if (materialIds[0] == 0) {
            materialListeUnformatiert = new Object[0][0];
        }

        materialListeUnformatiert = new Object[materialIds.length][6];

        int zaehlerDesArrayIndexes = 0;
        for (int materialIdZumAbrufen : materialIds) {

            MaterialDatensatz abrufenDerWerte = new MaterialDatensatz(getSqlConnection());

            int ArrayPostionsZahler = 0;

            for (int werte : abrufenDerWerte.getMaterialDatensatzAusDatenbank(materialIdZumAbrufen)) {

                materialListeUnformatiert[zaehlerDesArrayIndexes][ArrayPostionsZahler] = (Integer) werte;
                ArrayPostionsZahler++;
            }
            zaehlerDesArrayIndexes++;
        }
    }
}
