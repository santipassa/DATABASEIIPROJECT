package proyectobdii;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santiago
 */
class Procedure {

    private String name;
    private List<Param> paramList;

    public Procedure(DBConnection c, String name) throws SQLException {
        this.name = name;
        this.paramList = new ArrayList<>();
        //get the metadata from INFORMATION_SCHEMA database
        DBConnection dbc = new DBConnection(c.getHost(), "INFORMATION_SCHEMA", c.getUser(), c.getPwd());
        String sql = "SELECT PARAMETER_MODE,PARAMETER_NAME,DATA_TYPE FROM parameters WHERE SPECIFIC_SCHEMA=? AND SPECIFIC_NAME=?";
        PreparedStatement query = dbc.getConnection().prepareStatement(sql);
        query.setString(1, c.getBd());
        query.setString(2, this.name);
        ResultSet rs = query.executeQuery();
        while (rs.next()) {

            paramList.add(new Param(rs.getString("PARAMETER_NAME"), rs.getString("DATA_TYPE"), rs.getString("PARAMETER_MODE")));

        }
        dbc.closeConnection();

    }

    public boolean equals(Procedure p) {
        boolean result;
        result = this.name.equals(p.getName()) && (this.paramList.size() == p.getParamList().size());
        if (result) {
            if (this.paramList.size() == p.getParamList().size()) {
                for (int i = 0; i < this.paramList.size(); i++) {
                    result = this.paramList.get(i).equals(p.getParamList().get(i)) && result;
                }
            }
        }

        return result;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Param> getParamList() {
        return paramList;
    }

    public void setParamList(List<Param> paramList) {
        this.paramList = paramList;
    }

    @Override
    public String toString() {
        String params="";
        for(Param p : this.paramList){
            params=params+" "+p.toString();
        }
        return "Procedure{" + "name=" + name +" "+params+'}';
    }

}
