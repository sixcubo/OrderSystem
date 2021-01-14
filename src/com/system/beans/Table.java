package com.system.beans;

/**
 * @author nanfang
 * @create 2021/01/14
 */
public class Table {
    private int id;
    private int tableID;
    private int maxPersonNum;
    private int personNum;
    private String state;

    public Table() {
    }

    public Table(int id, int tableID, int maxPersonNum, int personNum, String state) {
        this.id = id;
        this.tableID = tableID;
        this.maxPersonNum = maxPersonNum;
        this.personNum = personNum;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", tableID=" + tableID +
                ", maxPersonNum=" + maxPersonNum +
                ", personNum=" + personNum +
                ", state='" + state + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getMaxPersonNum() {
        return maxPersonNum;
    }

    public void setMaxPersonNum(int maxPersonNum) {
        this.maxPersonNum = maxPersonNum;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
