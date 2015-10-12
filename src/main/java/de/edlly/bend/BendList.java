package de.edlly.bend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.edlly.db.SQLiteConnect;
import de.edlly.db.SQLiteException;
import de.edlly.db.SQLitePreparedStatement;
import de.edlly.db.SQLiteStatement;
import de.edlly.part.IPart;
import de.edlly.part.IPartList;
import de.edlly.part.Part;
import de.edlly.part.PartException;
import de.edlly.part.PartList;

public class BendList implements IBendList {
    private List<IBend<?>> bendList;
    private List<Integer> idList;
    private IPart part;
    private SQLiteConnect connection = new SQLiteConnect();

    public BendList() throws SQLiteException, PartException {
        bendList = new ArrayList<IBend<?>>();
        part = new Part();
    }

    @Override
    public void setPart(IPart part) throws PartException {
        if (part == null) {
            throw new PartException("Part Id nicht angelegt");
        }

        this.part = part;
    }

    @Override
    public void addBendSort(IBend<?> bend) throws PartException {

        addBend(bend);
        sortList();
    }

    @Override
    public void addBend(IBend<?> bend) throws PartException {
        if (isBendKollision(bend)) {

            throw new PartException("Fehler: Der minimal Abstand ist nicht eingehalten worden +- : "
                    + IBend.ABSTAND_BEND.doubleValue());
        }

        bendList.add(bend);
    }

    @Override
    public List<IBend<?>> getBends() {

        return bendList;
    }

    @Override
    public List<IBend<?>> getBendAfterPartId(IBend<?> bend, int partId) throws PartException, SQLiteException {
        connection.dbConnect();
        IPartList partList = new PartList(connection);

        if (!partList.idVorhanden(partId)) {
            throw new PartException("Kein Part unter der Id vorhanden ID: " + partId);
        }

        SQLiteStatement sql = new SQLiteStatement(connection);
        List<Integer> idList = getIdList(sql, partId);

        bendList = new ArrayList<IBend<?>>();

        for (int idToGet : idList) {
            // Neues pseudo Objekt um das vorherige zwingend zu überschreiben.
            bend = new Bend<Double>(bend.getYMax().doubleValue());
            bend.getDB(idToGet, sql);
            bendList.add(bend);
        }

        connection.close();
        return bendList;

    }

    @Override
    public void sortList() {

        Collections.sort(bendList);
    }

    @Override
    public boolean addListToDB() throws PartException, SQLiteException {

        if (bendList == null || bendList.isEmpty()) {
            throw new PartException("Liste wurde nicht angelegt");
        }

        connection.dbConnect();

        for (int i = 0; i < bendList.size(); i++) {
            SQLitePreparedStatement sql = new SQLitePreparedStatement(connection);
            bendList.get(i).setPart(part);
            bendList.get(i).addDB(sql);
        }

        connection.close();
        return true;
    }

    @Override
    public boolean isBendKollision(IBend<?> bend) {
        for (int i = 0; i < bendList.size(); i++) {

            double isY = bendList.get(i).getY().doubleValue();
            double getY = bend.getY().doubleValue();

            if (isY == getY) {
                return true;

            } else if (isY > getY) {

                if (isY < getY + IBend.ABSTAND_BEND.doubleValue()) {
                    return true;
                }

            } else {
                if (isY > getY - IBend.ABSTAND_BEND.doubleValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Integer> getIdList(SQLiteStatement sql) throws SQLiteException, PartException {

        return getIdListFromDB(sql, -1);
    }

    @Override
    public List<Integer> getIdList(SQLiteStatement sql, int partId) throws SQLiteException, PartException {

        return getIdListFromDB(sql, partId);
    }

    private List<Integer> getIdListFromDB(SQLiteStatement sql, int partId) throws SQLiteException, PartException {
        SQLiteConnect.isClosedOrNull(sql);

        try {
            if (partId == -1) {
                sql.setQuery("SELECT id FROM Bend");
            } else {
                sql.setQuery("SELECT id FROM Bend WHERE partId = " + partId);
            }

            sql.statmentVorbereitenUndStarten(sql.getQuery());

            int anzahlDatensatze = sql.anzahlDatenseatze();

            if (anzahlDatensatze == 0) {
                throw new PartException("Keine Datensätze in der Datenbank vorhanden.");
            }

            idList = new ArrayList<Integer>();

            sql.statmentVorbereitenUndStarten(sql.getQuery());
            while (sql.getResult().next()) {
                idList.add(sql.getResult().getInt(1));
            }
            sql.closeStatmentAndResult();
            return idList;

        } catch (SQLException e) {
            throw new SQLiteException(e.getLocalizedMessage());
        } finally {
            try {
                sql.closeStatmentAndResult();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isIdVorhanden(int id) throws SQLiteException, PartException {
        connection.dbConnect();
        SQLiteStatement sql = new SQLiteStatement(connection);
        List<Integer> idList = getIdList(sql);
        for (int idActual : idList) {
            if (idActual == id) {
                connection.close();
                return true;
            }
        }
        connection.close();
        return false;
    }
}
