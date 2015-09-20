package werkstueck;

public interface IWerkstueckIds extends IWerkstueck {
    
    /**
     * Prüft ob die Id in der Datenbank vorhanden ist
     * @param id
     * @return false wenn diese nicht vorhanden ist.
     */
    boolean IdVorhanden(int id);

    int[] getIdList();

}
